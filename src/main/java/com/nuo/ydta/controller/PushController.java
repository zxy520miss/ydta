package com.nuo.ydta.controller;

import com.nuo.ydta.contances.ProjectError;
import com.nuo.ydta.dto.PushDto;
import com.nuo.ydta.exception.BaseBusinessException;
import com.nuo.ydta.service.PushService;
import com.nuo.ydta.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@Api(description = "推送管理")
public class PushController {


    @Autowired
    private PushService pushService;


    /**
     * 群推，广播
     * @return
     */
    @PostMapping("/pushAll")
    @ResponseBody
    @ApiOperation("广播")
    public Response pushAll(@RequestBody PushDto pushDto){
        if(StringUtils.isBlank(pushDto.getTitle())){
            throw new BaseBusinessException(ProjectError.PARAM_IS_ERROR);
        }

        if(StringUtils.isBlank(pushDto.getContent())){
            throw new BaseBusinessException(ProjectError.PARAM_IS_ERROR);
        }

        if(StringUtils.isBlank(pushDto.getSender())){
            throw new BaseBusinessException(ProjectError.PARAM_IS_ERROR);
        }

        Boolean flag = pushService.pushAll(pushDto.getTitle(), pushDto.getContent(),pushDto.getSender());
        return Response.create(flag);
    }


    /**
     * 单独对regId进行推送
     * @return
     */
    @PostMapping("/push")
    @ResponseBody
    @ApiOperation("regId 单点")
    public Response push(@RequestBody PushDto pushDto){
        if(StringUtils.isBlank(pushDto.getTitle())){
            throw new BaseBusinessException(ProjectError.PARAM_IS_ERROR);
        }

        if(StringUtils.isBlank(pushDto.getContent())){
            throw new BaseBusinessException(ProjectError.PARAM_IS_ERROR);
        }

        if(pushDto.getRoleId() == -1){
            throw new BaseBusinessException(ProjectError.PARAM_IS_ERROR);
        }

        Boolean flag = pushService.push(pushDto.getTitle(), pushDto.getContent(), pushDto.getRoleId(),pushDto.getSender());
        return Response.create(flag);
    }

}
