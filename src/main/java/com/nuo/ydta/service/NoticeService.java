package com.nuo.ydta.service;

import com.nuo.ydta.domain.Clew;
import com.nuo.ydta.domain.Notice;
import org.springframework.data.domain.Page;

import java.util.List;

public interface NoticeService {


    Page<Notice> queryPage(int pageIndex, int pageSize);


    void add(Notice notice);

    void delete(int id);

    void update(Notice notice);

    List<Notice> findAllByStage(int stage);

    List<Notice> findAllByStatusAndRoleId(int status,int roleId);

}
