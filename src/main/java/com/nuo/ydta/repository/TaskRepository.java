package com.nuo.ydta.repository;

import com.nuo.ydta.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer>, JpaSpecificationExecutor<Task> {

    List<Task> findAllByStatusAndRoleId(int status,int roleId);

    List<Task> findAllByStageId(int stageId);

}
