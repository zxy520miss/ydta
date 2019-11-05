package com.nuo.ydta.service;

import com.nuo.ydta.domain.Clew;
import com.nuo.ydta.domain.Play;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ClewService {


    Page<Clew> queryPage(int pageIndex,int pageSize);

    void add(Clew clew);

    void delete(int id);

    void update(Clew clew);

    List<Clew> findAllByStage(int stage);

    List<Clew> findAllByStatusAndRoleId(int status,int roleId);


}
