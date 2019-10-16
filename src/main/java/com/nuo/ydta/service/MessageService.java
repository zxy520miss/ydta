package com.nuo.ydta.service;

import com.nuo.ydta.domain.MessagePush;
import org.springframework.data.domain.Page;

public interface MessageService {

    Page<MessagePush> pageQuery(int pageIndex, int pageSize);
}
