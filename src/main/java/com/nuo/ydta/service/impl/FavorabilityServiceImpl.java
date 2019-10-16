package com.nuo.ydta.service.impl;

import com.nuo.ydta.domain.RoleNpc;
import com.nuo.ydta.exception.BusinessException;
import com.nuo.ydta.repository.FavorabilityRepository;
import com.nuo.ydta.service.FavorabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavorabilityServiceImpl implements FavorabilityService {

    @Autowired
    private FavorabilityRepository favorabilityRepository;
    @Override
    public List<RoleNpc> getFavorabilitys() {
        try {
            List<RoleNpc> list = favorabilityRepository.findAll();
            return list;
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    @Override
    public void updategetFavorabilitys(String roleName, String npcName,int favorability) {
        try {
            RoleNpc roleNpc = favorabilityRepository.findByRoleNameAndNpcName(roleName, npcName);
            roleNpc.setFavorability(roleNpc.getFavorability()+favorability);
            favorabilityRepository.save(roleNpc);
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }
}
