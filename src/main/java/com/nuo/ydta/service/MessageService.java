package com.nuo.ydta.service;

import com.nuo.ydta.domain.MessagePush;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MessageService {

    Page<MessagePush> pageQuery(int pageIndex, int pageSize);

    List<MessagePush> findAllByRoleId(int roleId);
}
