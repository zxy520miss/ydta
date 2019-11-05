package com.nuo.ydta.controller;

import com.google.common.collect.Lists;
import com.nuo.ydta.contances.StageStatus;
import com.nuo.ydta.domain.Role;
import com.nuo.ydta.domain.Stage;
import com.nuo.ydta.domain.Task;
import com.nuo.ydta.dto.TaskDto;
import com.nuo.ydta.service.RoleService;
import com.nuo.ydta.service.StageService;
import com.nuo.ydta.service.TaskService;
import com.nuo.ydta.utils.NuoPage;
import com.nuo.ydta.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Api(description = "任务管理")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private StageService stageService;

    /**
     * 任务列表
     */
    @PostMapping("/task/page")
    @ResponseBody
    @ApiOperation("分页查询任务")
    public Response taskList(@RequestParam("pageIndex") int pageIndex){
        int pageSize = 10;
        NuoPage page = new NuoPage(pageIndex,pageSize);
        Page<Task> tasks = taskService.queryPage(page.pageIndex(), page.pageSize());

        if (tasks != null && tasks.hasContent()) {
            List<TaskDto> data = Lists.newArrayList();
            List<Task> content = tasks.getContent();
            for (Task task : content) {
                TaskDto taskDto = new TaskDto();

                BeanUtils.copyProperties(task, taskDto);
                Role role = roleService.findRoleById(task.getRoleId());
                Stage stage = stageService.findStageById(task.getStageId());
                taskDto.setRoleName(role.getName());
                taskDto.setStageDesc(stage.getName());
                data.add(taskDto);
            }

            Response<List<TaskDto>> response = new Response<>();
            response.setTotal(tasks.getTotalElements());
            response.setData(data);
            return response;
        }
        return new Response<>(Lists.newArrayList());
    }

    /**
     * 添加新任务
     */
    @PostMapping("/task/add")
    @ResponseBody
    @ApiOperation("添加任务")
    public Response add(@RequestBody Task task){
        task.setStatus(StageStatus.INVISIBLE);
        try {
            taskService.add(task);
        } catch (Exception e) {
            throw e;
        }
        return Response.create();
    }

    /**
     * 删除任务
     */
    @PostMapping("/task/delete")
    @ResponseBody
    @ApiOperation("删除任务")
    public Response delete(@RequestParam("id") int id){
        try {
            taskService.delete(id);
        } catch (Exception e) {
            throw e;
        }
        return Response.create();
    }

    /**
     * 修改任务：只能修改内容
     */
    @PostMapping("/task/update")
    @ResponseBody
    @ApiOperation("修改任务")
    public Response update(@RequestBody Task task){
        try {
            taskService.update(task);
        } catch (Exception e) {
            throw e;
        }
        return Response.create();
    }

    /**
     * 根据角色ID和状态查询任务
     */
    @GetMapping("/task/roleId/status/get")
    @ResponseBody
    @ApiOperation("条件查询任务")
    public  Response findAllByRoleIdAndStatus(@RequestParam("roleId") int roleId,
                                              @RequestParam("status") int status){
        List<Task> taskList = taskService.findAllByStatusAndRoleId(status, roleId);
        return Response.create(taskList);

    }

}
