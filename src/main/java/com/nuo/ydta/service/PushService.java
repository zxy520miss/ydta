package com.nuo.ydta.service;

public interface PushService {

    Boolean push(String title, String content,String alias,String sender);

    Boolean pushAll(String title, String content);

}
