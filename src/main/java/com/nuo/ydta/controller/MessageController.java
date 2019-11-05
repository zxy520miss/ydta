package com.nuo.ydta.controller;

import com.google.common.collect.Lists;
import com.nuo.ydta.domain.MessagePush;
import com.nuo.ydta.dto.RoleDto;
import com.nuo.ydta.service.MessageService;
import com.nuo.ydta.utils.NuoPage;
import com.nuo.ydta.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@Api(description = "消息管理")
public class MessageController {


    @Autowired
    private MessageService messageService;

    @GetMapping("/message/page")
    @ResponseBody
    @ApiOperation("消息列表")
    //todo:
    public Response getAllMessage(@RequestParam("pageIndex") int pageIndex) {
        NuoPage page = new NuoPage(pageIndex);
        Page<MessagePush> messagePushes = messageService.pageQuery(page.pageIndex(), page.pageSize());

        if (messagePushes != null && messagePushes.hasContent()) {
            List<MessagePush> data = Lists.newArrayList();
            List<MessagePush> content = messagePushes.getContent();
            for (MessagePush messagePush : content) {
                data.add(messagePush);
            }
            Response<List<MessagePush>> response = new Response<>();
            response.setTotal(messagePushes.getTotalElements());
            response.setData(data);
            return response;
        }
        return new Response<>(Lists.newArrayList());
    }


    @GetMapping("/message/get/role")
    @ResponseBody
    @ApiOperation("通过roleId获取消息")
    public Response findAllByRoleId(@RequestParam("roleId") int roleId){
        List<MessagePush> pushList = messageService.findAllByRoleId(roleId);
        return Response.create(pushList);
    }
}
