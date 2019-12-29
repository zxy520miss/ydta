package com.nuo.ydta.service.impl;

import com.nuo.ydta.contances.Status;
import com.nuo.ydta.domain.MessagePush;
import com.nuo.ydta.domain.PushBean;
import com.nuo.ydta.domain.Role;
import com.nuo.ydta.dto.RoleDto;
import com.nuo.ydta.service.JiGuangPushService;
import com.nuo.ydta.service.MessageService;
import com.nuo.ydta.service.PushService;
import com.nuo.ydta.service.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PushServiceImpl implements PushService {

    @Autowired
    private MessageService messageService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private JiGuangPushService jiGuangPushService;

    @Override
    public Boolean push(String title, String content, int roleId,String sender) {

        Role role = roleService.getRoleById(roleId);

        MessagePush messagePush = new MessagePush();
        messagePush.setContent(content);
        messagePush.setSender(StringUtils.isBlank(sender)?"系统":sender);
        messagePush.setRecipient(role.getName());
        messagePush.setRoleId(roleId);
        messagePush.setTitle(title);

        messageService.save(messagePush);

        PushBean pushBean = new PushBean();
        pushBean.setTitle(title);
        pushBean.setAlert(content);
        boolean flag = jiGuangPushService.pushAndroid(pushBean,String.valueOf(roleId));
        return flag;
    }

    @Override
    public Boolean pushAll(String title, String content,String sender) {

        MessagePush messagePush = new MessagePush();
        messagePush.setContent(content);
        messagePush.setSender(StringUtils.isBlank(sender)? "系统": sender);
        messagePush.setRecipient("ALL");
        messagePush.setTitle(title);
        messagePush.setRoleId(-1);
        messageService.save(messagePush);
        PushBean pushBean = new PushBean();
        pushBean.setTitle(title);
        pushBean.setAlert(content);
        boolean flag = jiGuangPushService.pushAndroid(pushBean);
        return flag;
    }
}
