package com.nuo.ydta.controller;

import com.nuo.ydta.domain.PushBean;
import com.nuo.ydta.service.JiGuangPushService;
import com.nuo.ydta.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Api(description = "推送管理")
public class PushController {

    @Autowired
    private JiGuangPushService jiGuangPushService;

    /**
     * 群推，广播
     * @param title 推送标题
     * @param content 推送内容
     * @return
     */
    @PostMapping("/pushAll")
    @ResponseBody
    @ApiOperation("广播")
    public Response pushAll(@RequestParam("title") String title, @RequestParam("content") String content){
        PushBean pushBean = new PushBean();
        pushBean.setTitle(title);
        pushBean.setAlert(content);
        boolean flag = jiGuangPushService.pushAndroid(pushBean);
        return Response.create(flag);
    }

    /**
     * 单独对regId进行推送
     * @param title 推送标题
     * @param regId 设备对应的唯一极光ID
     * @param content 推送内容
     * @return
     */
    @PostMapping("/push")
    @ResponseBody
    @ApiOperation("regId 单点")
    public Response push(@RequestParam("title") String title,@RequestParam("regId") String regId,@RequestParam("content") String content){
        PushBean pushBean = new PushBean();
        pushBean.setTitle(title);
        pushBean.setAlert(content);
        boolean flag = jiGuangPushService.pushAndroid(pushBean, regId);
        return Response.create(flag);
    }

}
