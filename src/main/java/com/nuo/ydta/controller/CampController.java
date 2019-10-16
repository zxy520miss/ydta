package com.nuo.ydta.controller;

import com.nuo.ydta.contances.ProjectError;
import com.nuo.ydta.domain.Camp;
import com.nuo.ydta.repository.CampRepository;
import com.nuo.ydta.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Controller
@Api(description = "阵营管理")
public class CampController {

    @Autowired
    private CampRepository campRepository;

    /**
     * 获取阵营列表
     */
    @GetMapping("/camp/list")
    @ResponseBody
    @ApiOperation("获取所有阵营")
    public Response getCamps(){
        try {
            List<Camp> list = campRepository.findAll();
            return Response.create(list);
        } catch (Exception e) {
            return Response.create(ProjectError.SYSTEM_ERROR);
        }
    }
}
