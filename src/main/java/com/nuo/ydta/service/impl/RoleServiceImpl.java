package com.nuo.ydta.service.impl;

import com.google.common.collect.Lists;
import com.nuo.ydta.contances.ProjectError;
import com.nuo.ydta.contances.Status;
import com.nuo.ydta.domain.Camp;
import com.nuo.ydta.domain.Role;
import com.nuo.ydta.dto.RoleDto;
import com.nuo.ydta.exception.BusinessException;
import com.nuo.ydta.repository.CampRepository;
import com.nuo.ydta.repository.RoleRepository;
import com.nuo.ydta.service.RoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private CampRepository campRepository;

    @Override
    public String findRoleBySerialNoAndStatus(String serialNo, Integer status) {

        Role role;
        try {
            role = roleRepository.findRoleBySerialNoAndStatus(serialNo, status);
            if(role == null){
                throw new BusinessException(ProjectError.ROLE_IS_NULL);
            }
            return role.getPlay() ;
        } catch (Exception e) {
        }
        return null ;
    }

    @Override
    public boolean save(Role role) {
        try {
            role.setModifyCamp(Boolean.TRUE);
            role.setSuspicion(0);
            role.setHalo(0);
            role.setPoll(0);
            role.setStatus(Status.UNDELETE);
            roleRepository.save(role);
            return true;
        }catch (Exception e){
            return false;
        }
    }


    @Override
    public Page<Role> pageQuery(int pageIndex,int pageSize) {
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        return roleRepository.findAll(pageable);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RoleDto getRoleBySerialNo(String serialNo) {
        try {
            RoleDto roleDto = new RoleDto();
            Role role = roleRepository.findRoleBySerialNoAndStatus(serialNo, Status.UNDELETE);
            Camp one = campRepository.getOne(role.getCamp());
            BeanUtils.copyProperties(role,roleDto,"camp");
            roleDto.setCampDesc(one.getDescription());
            return roleDto;
        } catch (Exception e) {
            throw new BusinessException(ProjectError.GET_ROLE_ERROR);
        }
    }

    @Override
    public boolean campCheck(String serialNo) {
       Role role =  roleRepository.findRoleBySerialNoAndStatus(serialNo, Status.UNDELETE);
        if(role == null){
            throw new BusinessException(ProjectError.ROLE_IS_NULL);
        }
        return role.getModifyCamp();
    }

    @Override
    @Transactional
    public boolean updateRoleCamp(String serialNo,String camp) {
        try {
            Role role =  roleRepository.findRoleBySerialNoAndStatus(serialNo, Status.UNDELETE);
            if(role == null){
                return false;
            }
            Camp c = campRepository.findByDescription(camp);
            role.setCamp(c.getId());
            role.setModifyCamp(false);
            roleRepository.save(role);
            return true;
        } catch (Exception e) {
           throw new BusinessException(e);
        }
    }

    @Override
    public boolean  updateRoleSuspicion(String serialNo,int suspicion) {
        try {
            Role role =  roleRepository.findRoleBySerialNoAndStatus(serialNo, Status.UNDELETE);
            if(role == null){
               return false;
            }
            role.setSuspicion(role.getSuspicion()+ suspicion);
            roleRepository.save(role);
            return true;
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    @Override
    public boolean updateRoleHalo(String serialNo, int halo) {
        try {
            Role role =  roleRepository.findRoleBySerialNoAndStatus(serialNo, Status.UNDELETE);
            if(role == null){
                return false;
            }
            role.setHalo(role.getSuspicion()+ halo);
            roleRepository.save(role);
            return true;
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    @Override
    public synchronized void vote(String roleName) {
        try {
            Role role = roleRepository.getRoleByName(roleName);
            role.setPoll(role.getPoll()+1);
            roleRepository.save(role);
        } catch (Exception e) {
            throw new BusinessException(ProjectError.SYSTEM_ERROR);
        }
    }
}
