package com.nuo.ydta.repository;

import com.nuo.ydta.domain.MessagePush;
import com.nuo.ydta.domain.Npc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface MessageResipotory extends JpaRepository<MessagePush, Integer>, JpaSpecificationExecutor<MessagePush> {

//    List<MessagePush> findAllBySerialNoOrderByCreatedTime(String serialNo);

    List<MessagePush> findAllByRoleIdInOrderByCreatedTime(int[] roleIds);
}
