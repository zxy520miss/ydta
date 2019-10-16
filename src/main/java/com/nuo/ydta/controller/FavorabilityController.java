package com.nuo.ydta.controller;

import com.nuo.ydta.contances.ProjectError;
import com.nuo.ydta.domain.RoleNpc;
import com.nuo.ydta.service.FavorabilityService;
import com.nuo.ydta.utils.Response;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.info.ProjectInfoAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class FavorabilityController {

    @Autowired
    private FavorabilityService favorabilityService;

    @GetMapping("/role/npc/favorabilitys/get")
    @ResponseBody
    public Response getFavorability(){
        List<RoleNpc> list = favorabilityService.getFavorabilitys();
        return Response.create(list);
    }


    @GetMapping("/role/npc/favorability/update")
    @ResponseBody
    public Response updateFavorability(@RequestParam("roleName") String roleName,
                                       @RequestParam("npcName") String npcName,
                                       @RequestParam("favorability") Integer favorability){
        if(StringUtils.isBlank(roleName)){
            return Response.create(ProjectError.PARAM_NAME_IS_ERROR);
        }
        if(StringUtils.isBlank(npcName)){
            return Response.create(ProjectError.PARAM_NPC_NAME_IS_ERROR);
        }
        if(favorability != null){
            return Response.create(ProjectError.PARAM_FAVORABILITY_IS_NULL);
        }
        favorabilityService.updategetFavorabilitys(roleName,npcName,favorability);
        return Response.create();
    }
}
