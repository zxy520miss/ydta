package com.nuo.ydta.service;

import com.nuo.ydta.domain.Task;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TaskService {

    /**
     * 分页查询任务列表
     * @param pageIndex
     * @param pageSize
     * @return
     */
    Page<Task> queryPage(int pageIndex,int pageSize);

    void add(Task task);

    void update(Task task);

    void delete(int id);

    List<Task> findAllByStatusAndRoleId(int status,int roleId);

    List<Task> findAllByStage(int stage);




}
