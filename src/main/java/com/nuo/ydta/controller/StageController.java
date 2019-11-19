package com.nuo.ydta.controller;

import com.nuo.ydta.contances.ProjectError;
import com.nuo.ydta.domain.Stage;
import com.nuo.ydta.dto.StageDto;
import com.nuo.ydta.service.StageService;
import com.nuo.ydta.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Api(description = "阶段管理")
public class StageController {

    @Autowired
    private StageService stageService;

    @GetMapping("/stage/all/get/oldId")
    @ResponseBody
    @ApiOperation("查询所有阶段+OldId")
    public Response findAllStageOldId() {
        StageDto allStage = stageService.findAllStageOldId();
        return Response.create(allStage);
    }

    @GetMapping("/stage/all/get")
    @ResponseBody
    @ApiOperation("查询所有阶段")
    public Response findAllStage() {
        List<Stage> allStage = stageService.findAllStage();
        return Response.create(allStage);
    }

    @PostMapping("/stage/add")
    @ResponseBody
    @ApiOperation("新增剧情阶段")
    public Response save(@RequestBody Stage stage) {
        if (null == stage) {
            return Response.create(ProjectError.STAGE_IS_NULL);
        }
        if (StringUtils.isBlank(stage.getDescription())) {
            return Response.create(ProjectError.PARAM_DESC_IS_ERROR);
        }
        if (StringUtils.isBlank(stage.getName())) {
            return Response.create(ProjectError.PARAM_STAGE_NAME_IS_ERROR);
        }
        stageService.add(stage);
        return Response.create();
    }

    /**
     * 修改剧情
     */
    @PostMapping("/stage/update")
    @ResponseBody
    @ApiOperation("修改剧情阶段")
    public Response update(@RequestBody Stage stage) {
        if (null == stage) {
            return Response.create(ProjectError.STAGE_IS_NULL);
        }
        if (stage.getId() <= 0) {
            return Response.create(ProjectError.PARAM_STAGE_ID_IS_ERROR);
        }
        if (StringUtils.isBlank(stage.getDescription())) {
            return Response.create(ProjectError.PARAM_DESC_IS_ERROR);
        }
        if (StringUtils.isBlank(stage.getName())) {
            return Response.create(ProjectError.PARAM_STAGE_NAME_IS_ERROR);
        }
        stageService.update(stage);
        return Response.create();
    }


    /**
     * 触发剧情
     */
    @PostMapping("/stage/status/update")
    @ResponseBody
    @ApiOperation("触发剧情")
    public Response updateStatus(@RequestParam int id) {
        if (id <= 0) {
            return Response.create(ProjectError.PARAM_STAGE_ID_IS_ERROR);
        }
        stageService.updateStatus(id);
        return Response.create();
    }

    /**
     * 获取剧情阶段
     *
     * @return
     */
    @GetMapping("/stage/get/ids")
    @ResponseBody
    @ApiOperation("获取阶段")
    public Response getStages() {
        List<Stage> stages = stageService.getStages();
        return Response.create(stages);
    }


}
