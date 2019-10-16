package com.nuo.ydta.controller;

import com.nuo.ydta.contances.ProjectError;
import com.nuo.ydta.domain.Stage;
import com.nuo.ydta.service.StageService;
import com.nuo.ydta.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Controller
@Api(description = "阶段管理")
public class StageController {

    @Autowired
    private StageService stageService;

    @GetMapping("/stage/all/get")
    @ResponseBody
    @ApiOperation("查询所有阶段")
    public Response findAllStage(){
        List<Stage> lists = stageService.findAllStage();
        return Response.create(lists);
}

    @GetMapping("/stage/add")
    @ResponseBody
    @ApiOperation("新增")
    public Response save(@RequestBody Stage stage){
        if(null == stage){
            return Response.create(ProjectError.STAGE_IS_NULL);
        }
        if(StringUtils.isBlank(stage.getDescription())){
            return Response.create(ProjectError.PARAM_DESC_IS_ERROR);
        }
         stageService.add(stage);
            return Response.create();
    }

    //todo:修改剧情
    /**
     * 修改剧情
     */

    //todo:触发剧情
    /**
     * 触发剧情
     */


}
