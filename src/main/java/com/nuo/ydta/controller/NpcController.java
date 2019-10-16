package com.nuo.ydta.controller;

import com.nuo.ydta.contances.ProjectError;
import com.nuo.ydta.domain.Npc;
import com.nuo.ydta.service.NpcService;
import com.nuo.ydta.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@Api(description = "NPC管理")
public class NpcController {

    @Autowired
    private NpcService npcService;

    @GetMapping("/npc/list")
    @ResponseBody
    @ApiOperation("获取所有NPC")
    public Response getNpcs(){
        List<Npc> npcs = npcService.getNpcs();
        return Response.create(npcs);
    }

    @GetMapping("/npc/delete")
    @ResponseBody
    @ApiOperation("删除NPC")
    public Response delete(@RequestParam("id") int id){
        if(id <= 0){
            return Response.create(ProjectError.PARAM_NPC_ID_IS_ERROR);
        }
        npcService.delete(id);
        return Response.create();
    }

    @GetMapping("/npc/add")
    @ResponseBody
    @ApiOperation("新增NPC")
    public Response add(@RequestBody Npc npc){
        if(null == npc){
            return Response.create(ProjectError.NPC_IS_NULL);
        }
        if(StringUtils.isBlank(npc.getDescription())){
            return Response.create(ProjectError.PARAM_NPC_DESC_IS_ERROR);
        }
        if(StringUtils.isBlank(npc.getName())){
            return Response.create(ProjectError.PARAM_NPC_DESC_IS_ERROR);
        }
        if(StringUtils.isBlank(npc.getAddress())){
            return Response.create(ProjectError.PARAM_NPC_DESC_IS_ERROR);
        }
        npcService.add(npc);
        return Response.create();
    }

    @GetMapping("/npc/update")
    @ResponseBody
    @ApiOperation("修改NPC")
    public Response update(Npc npc){
        if(null == npc){
            return Response.create(ProjectError.NPC_IS_NULL);
        }
        if(StringUtils.isBlank(npc.getDescription())){
            return Response.create(ProjectError.PARAM_NPC_DESC_IS_ERROR);
        }
        if(StringUtils.isBlank(npc.getName())){
            return Response.create(ProjectError.PARAM_NPC_DESC_IS_ERROR);
        }
        if(StringUtils.isBlank(npc.getAddress())){
            return Response.create(ProjectError.PARAM_NPC_DESC_IS_ERROR);
        }
         npcService.update(npc);
        return Response.create();
    }
}
