package com.nuo.ydta.service;


import cn.jpush.api.push.model.PushPayload;
import com.nuo.ydta.domain.PushBean;

/**
 * 极光推送
 * 封装第三方api相关
 */
public interface MyJiGuangPushService {
    boolean pushAll(PushBean pushBean);

    boolean pushIos(PushBean pushBean);

    boolean pushIos(PushBean pushBean, String... registids);

    boolean pushAndroid(PushBean pushBean);

    boolean pushAndroid(PushBean pushBean, String... registids);

    boolean sendPush(PushPayload pushPayload);
}
