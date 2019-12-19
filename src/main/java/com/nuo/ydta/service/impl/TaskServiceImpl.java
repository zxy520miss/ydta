package com.nuo.ydta.service.impl;

import com.nuo.ydta.contances.ProjectError;
import com.nuo.ydta.domain.Stage;
import com.nuo.ydta.domain.Task;
import com.nuo.ydta.exception.BaseBusinessException;
import com.nuo.ydta.exception.BusinessException;
import com.nuo.ydta.repository.StageRepository;
import com.nuo.ydta.repository.TaskRepository;
import com.nuo.ydta.service.StageService;
import com.nuo.ydta.service.TaskService;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {


    @Autowired
    private TaskRepository taskRepository;


    @Override
    public Page<Task> queryPage(int pageIndex, int pageSize) {
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        return taskRepository.findAll(pageable);
    }

    @Override
    public void add(Task task) {
        taskRepository.save(task);
    }

    @Override
    public void update(Task task) {
        boolean exists = taskRepository.existsById(task.getId());
        if (exists) {
            taskRepository.save(task);
        } else {
            throw new BusinessException(ProjectError.TASK_IS_NULL);
        }

    }

    @Override
    public void delete(int id) {
        taskRepository.deleteById(id);
    }

    @Override
    public List<Task> findAllByStatusAndRoleId(int status, int roleId) {


        List<Task> taskList = null;
        int[] roleIds = {0, roleId};
        taskList = taskRepository.findAllByStatusAndRoleIdIn(status, roleIds);

//        if(roleId == 3){
//            Task task = taskRepository.findByDescription("查出杀害冯律司的凶手");
//            taskList.remove(task);
//        }else if (roleId == 15){
//            Task task = taskRepository.findByDescription("查出杀害周疆主的凶手");
//            taskList.remove(task);
//        }
        return taskList;
    }

    @Override
    public List<Task> findAllByStage(int stage) {

        return taskRepository.findAllByStageId(stage);
    }


}
