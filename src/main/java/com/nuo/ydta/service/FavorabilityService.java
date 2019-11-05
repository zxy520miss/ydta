package com.nuo.ydta.service;

import com.nuo.ydta.domain.RoleNpc;
import com.nuo.ydta.dto.RoleNpcDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface FavorabilityService {


    List<RoleNpc> getFavorabilitys();

    void updategetFavorabilitys(String roleName,String npcName,int favorability);

    Page<RoleNpc> pageQuery(int pageIndex, int pageSize);

    List<RoleNpcDto> findByRoleName(String roleName);
}
