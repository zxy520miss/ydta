package com.nuo.ydta.repository;

import com.nuo.ydta.domain.Clew;
import com.nuo.ydta.domain.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface NoticeRepository  extends JpaRepository<Notice, Integer>, JpaSpecificationExecutor<Notice> {

    List<Notice> findAllByStageId(int stage);

    List<Notice> findAllByStatusAndRoleId(int status,int roleId);

}
