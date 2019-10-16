package com.nuo.ydta.service;

import com.nuo.ydta.domain.RoleNpc;

import java.util.List;

public interface FavorabilityService {


    List<RoleNpc> getFavorabilitys();

    void updategetFavorabilitys(String roleName,String npcName,int favorability);

}
