package com.nuo.ydta.service;

public interface PushService {

    Boolean push(String title, String content,int roleId,String sender);

    Boolean pushAll(String title, String content);

}
