package com.nuo.ydta.service.impl;

import com.nuo.ydta.contances.Status;
import com.nuo.ydta.domain.MessagePush;
import com.nuo.ydta.domain.PushBean;
import com.nuo.ydta.domain.Role;
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
    public Boolean push(String title, String content, String alias,String sender) {

        Role role = roleService.findRoleBySerialNo(alias);

        MessagePush messagePush = new MessagePush();
        messagePush.setContent(content);
        messagePush.setSender(StringUtils.isBlank(sender)?"系统":sender);
        messagePush.setRecipient(role.getName());
        messagePush.setSerialNo(alias);
//        messagePush.setType(StringUtils.isBlank(type)?"SYSTEM":type);
        messagePush.setTitle(title);

        messageService.save(messagePush);

        PushBean pushBean = new PushBean();
        pushBean.setTitle(title);
        pushBean.setAlert(content);
        boolean flag = jiGuangPushService.pushAndroid(pushBean);
        return flag;
    }

    @Override
    public Boolean pushAll(String title, String content) {

        MessagePush messagePush = new MessagePush();
        messagePush.setContent(content);
        messagePush.setSender("系统");
        messagePush.setRecipient("ALL");
//        messagePush.setType(StringUtils.isBlank(type)?"SYSTEM":type);
        messagePush.setTitle(title);
        messagePush.setSerialNo("-1");
        messageService.save(messagePush);
        PushBean pushBean = new PushBean();
        pushBean.setTitle(title);
        pushBean.setAlert(content);
        boolean flag = jiGuangPushService.pushAndroid(pushBean);
        return flag;
    }
}
