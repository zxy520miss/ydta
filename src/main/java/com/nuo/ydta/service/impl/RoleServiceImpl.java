package com.nuo.ydta.service.impl;

import com.nuo.ydta.contances.ProjectError;
import com.nuo.ydta.contances.Status;
import com.nuo.ydta.domain.Camp;
import com.nuo.ydta.domain.PushBean;
import com.nuo.ydta.domain.Role;
import com.nuo.ydta.dto.RoleDto;
import com.nuo.ydta.exception.BusinessException;
import com.nuo.ydta.repository.CampRepository;
import com.nuo.ydta.repository.RoleRepository;
import com.nuo.ydta.repository.StageRepository;
import com.nuo.ydta.service.JiGuangPushService;
import com.nuo.ydta.service.RoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private CampRepository campRepository;

    @Autowired
    private StageRepository stageRepository;

    @Autowired
    private JiGuangPushService jiGuangPushService;

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
            role.setStatus(Status.VISIBLE);
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
            Role role = roleRepository.findRoleBySerialNoAndStatus(serialNo, Status.VISIBLE);
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
       Role role =  roleRepository.findRoleBySerialNoAndStatus(serialNo, Status.VISIBLE);
        if(role == null){
            throw new BusinessException(ProjectError.ROLE_IS_NULL);
        }
        return role.getModifyCamp();
    }

    @Override
    @Transactional
    public boolean updateRoleCamp(String serialNo,String camp) {
        try {
            Role role =  roleRepository.findRoleBySerialNoAndStatus(serialNo, Status.VISIBLE);
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
            Role role =  roleRepository.findRoleBySerialNoAndStatus(serialNo, Status.VISIBLE);
            if(role == null){
               return false;
            }
            int susp = role.getSuspicion() + suspicion;
            if(susp > 100){
                susp = 100;
            }
            if(susp< 0 ){
                susp = 0;
            }

            if(susp>= 80 && susp != 100){

                PushBean pushBean = new PushBean();
                pushBean.setTitle("壹點探案-嫌疑值");
                pushBean.setAlert("嫌疑值已达80，若嫌疑值持续上升，将面临入狱风险！");
                jiGuangPushService.pushAndroid(pushBean,role.getId()+"");
            }

            if(susp== 100){

                PushBean pushBean = new PushBean();
                pushBean.setTitle("壹點探案-嫌疑值");
                pushBean.setAlert("嫌疑值已达100，狱卒即刻来押送你进入大牢！");
                jiGuangPushService.pushAndroid(pushBean,role.getId()+"");
            }
            role.setSuspicion(susp);
            roleRepository.save(role);
            return true;
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    @Override
    public boolean updateRoleHalo(String serialNo, int halo) {
        try {
            Role role =  roleRepository.findRoleBySerialNoAndStatus(serialNo, Status.VISIBLE);
            if(role == null){
                return false;
            }
            int h = role.getHalo() + halo;
           if(h > 100){
               h = 100;
           }

           if(h < 0 ){
               h = 0;
           }
            if(h >= 60 && h != 100){
                //todo:眩晕值推送
                PushBean pushBean = new PushBean();
                pushBean.setTitle("壹點探案-眩晕值");
                pushBean.setAlert("晕眩值已达60，若不及时进食补充营养或是减轻晕眩值可能会造成更严重的后果哦");
                jiGuangPushService.pushAndroid(pushBean,serialNo);
            }
            if(h == 100){
                //todo:眩晕值推送
                PushBean pushBean = new PushBean();
                pushBean.setTitle("壹點探案-眩晕值");
                pushBean.setAlert("晕眩值已达100，将会强制被带走补充营养或是减轻晕眩值");
                jiGuangPushService.pushAndroid(pushBean,serialNo);
            }
            role.setHalo(h);
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
            roleRepository.save(role);
        } catch (Exception e) {
            throw new BusinessException(ProjectError.SYSTEM_ERROR);
        }
    }

    @Override
    public List<Role> getRoles() {
        List<Role> all = roleRepository.findAll();
        return all;
    }

    @Override
    public RoleDto voteCheck(int roleId) {
        Optional<Role> optional = roleRepository.findById(roleId);
        Role role = optional.get();
        if(role == null){
            throw new BusinessException(ProjectError.ROLE_IS_NULL);
        }
        //todo:获取当前轮数
        return null;
    }

    @Override
    public Role findRoleById(int id) {
        return roleRepository.getOne(id);
    }

    @Override
    public Role findRoleBySerialNo(String serialNo) {
        return roleRepository.findRoleBySerialNo(serialNo);
    }

    @Override
    public void updateRoleVote(boolean isVote) {
        List<Role> all = roleRepository.findAll();
        for (Role role : all){
            role.setVote(isVote);
            roleRepository.save(role);
        }
    }
}
