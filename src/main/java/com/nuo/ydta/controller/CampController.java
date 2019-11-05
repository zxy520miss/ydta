package com.nuo.ydta.controller;

import com.nuo.ydta.contances.ProjectError;
import com.nuo.ydta.domain.Camp;
import com.nuo.ydta.repository.CampRepository;
import com.nuo.ydta.service.CampService;
import com.nuo.ydta.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Controller
@Api(description = "阵营管理")
public class CampController {

    @Autowired
    private CampService campService;

    /**
     * 获取阵营列表
     */
    @PostMapping("/camp/list")
    @ResponseBody
    @ApiOperation("获取所有阵营")
    public Response getCamps(){
        try {
            List<Camp> list = campService.getCamps();
            return Response.create(list);
        } catch (Exception e) {
            return Response.create(ProjectError.SYSTEM_ERROR);
        }
    }

    /**
     * 新增阵营
     */
    @PostMapping("/camp/add")
    @ResponseBody
    @ApiOperation("新增阵营")
    public Response save(@RequestBody Camp camp){
        if(camp == null){
            return Response.create(ProjectError.CAMP_IS_NULL);
        }
        if(StringUtils.isBlank(camp.getDescription())){
            return Response.create(ProjectError.PARAM_CAMP_DESC_IS_ERROR);
        }
        campService.addCamp(camp);
        return Response.create();
    }

    /**
     * 修改阵营
     */
    @PostMapping("/camp/update")
    @ResponseBody
    @ApiOperation("修改阵营")
    public Response update(@RequestBody Camp camp){
        if(camp == null){
            return Response.create(ProjectError.CAMP_IS_NULL);
        }
        if(camp.getId() <= 0){
            return Response.create(ProjectError.PARAM_CAMP_ID_IS_ERROR);
        }
        if(StringUtils.isBlank(camp.getDescription())){
            return Response.create(ProjectError.PARAM_CAMP_DESC_IS_ERROR);
        }
        campService.updateCamp(camp);
        return Response.create();
    }

    /**
     * 删除阵营
     */
    @PostMapping("/camp/delete")
    @ResponseBody
    @ApiOperation("删除阵营")
    public Response delete(@RequestBody Camp camp){
        if(camp == null){
            return Response.create(ProjectError.CAMP_IS_NULL);
        }
        if(camp.getId() <= 0){
            return Response.create(ProjectError.PARAM_CAMP_ID_IS_ERROR);
        }

        campService.deleteCamp(camp);
        return Response.create();
    }

}
