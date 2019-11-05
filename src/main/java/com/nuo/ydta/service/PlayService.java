package com.nuo.ydta.service;

import com.nuo.ydta.domain.Play;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PlayService {

    Page<Play> pageQuery(int pageIndex, int pageSize);


    void update(Play play);

    void add(Play play);

    void delete(int id);

    List<Play> findAllByStage(int stage);

    List<Play> findAllByStatusAndRoleId(int status,int roleId);

}
