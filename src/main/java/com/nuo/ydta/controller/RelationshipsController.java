package com.nuo.ydta.controller;

import com.nuo.ydta.domain.Relationships;
import com.nuo.ydta.service.RelationshipsSerivce;
import com.nuo.ydta.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Api(description = "人物关系管理" +
        "")
public class RelationshipsController {

    @Autowired
    private RelationshipsSerivce relationshipsSerivce;

    @ApiOperation("根据名字获取人物关系")
    @ResponseBody
    @GetMapping("/relationships/get")
    public Response getRelationships(@RequestParam("roleName") String roleName){
        return Response.create(relationshipsSerivce.findAllByRoleName(roleName));
    }
}
