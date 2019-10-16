package com.nuo.ydta.service.impl;

import com.nuo.ydta.domain.MessagePush;
import com.nuo.ydta.repository.MessageResipotory;
import com.nuo.ydta.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageResipotory messageResipotory;

    @Override
    public Page<MessagePush> pageQuery(int pageIndex, int pageSize) {
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        return messageResipotory.findAll(pageable);
    }
}
