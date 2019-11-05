package com.nuo.ydta.controller;

import com.google.common.collect.Lists;
import com.nuo.ydta.contances.ProjectError;
import com.nuo.ydta.domain.RoleNpc;
import com.nuo.ydta.dto.RoleNpcDto;
import com.nuo.ydta.service.FavorabilityService;
import com.nuo.ydta.utils.NuoPage;
import com.nuo.ydta.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Api(description = "好感度管理")
public class FavorabilityController {

    @Autowired
    private FavorabilityService favorabilityService;

    @GetMapping("/favorabilitys/get")
    @ResponseBody
    public Response getFavorability(){
        List<RoleNpc> list = favorabilityService.getFavorabilitys();
        return Response.create(list);
    }



    @PostMapping("/favorability/update")
    @ResponseBody
    public Response updateFavorability(@RequestBody RoleNpc roleNpc){

        if(StringUtils.isBlank(roleNpc.getRoleName())){
            return Response.create(ProjectError.PARAM_NAME_IS_ERROR);
        }
        if(StringUtils.isBlank(roleNpc.getNpcName())){
            return Response.create(ProjectError.PARAM_NPC_NAME_IS_ERROR);
        }
        if(roleNpc.getFavorability() == null){
            return Response.create(ProjectError.PARAM_FAVORABILITY_IS_NULL);
        }
        favorabilityService.updategetFavorabilitys(roleNpc.getRoleName(),roleNpc.getNpcName(),roleNpc.getFavorability());

        return Response.create();
    }


    /**
     * 分页查询好感度
     *
     * @param pageIndex
     * @return
     */
    @PostMapping("/favorabilitys/page")
    @ResponseBody
    @ApiOperation("分页查询好感度")
    public Response pageQuery(@RequestParam("pageIndex") int pageIndex) {
       int pageSize = 10;
        NuoPage page = new NuoPage(pageIndex,pageSize);
        Page<RoleNpc> roleNpcs = favorabilityService.pageQuery(page.pageIndex(), page.pageSize());

        if (roleNpcs != null && roleNpcs.hasContent()) {
            List<RoleNpc> content = roleNpcs.getContent();
            Response response = new Response<>();
            response.setTotal(roleNpcs.getTotalElements());
            response.setData(content);
            return response;
        }
        return new Response<>(Lists.newArrayList());
    }

    @GetMapping("/favorability/get")
    @ResponseBody
    @ApiOperation("根据角色ID和NpcId查询好感度")
    public Response findRoleNpc(@RequestParam("roleName") String roleName){
        List<RoleNpcDto> roleNpcList = favorabilityService.findByRoleName(roleName);
        return Response.create(roleNpcList);

    }
}
