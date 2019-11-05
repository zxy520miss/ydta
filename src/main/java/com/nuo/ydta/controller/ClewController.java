package com.nuo.ydta.controller;

import com.google.common.collect.Lists;
import com.nuo.ydta.contances.ProjectError;
import com.nuo.ydta.contances.StageStatus;
import com.nuo.ydta.domain.Clew;
import com.nuo.ydta.domain.Play;
import com.nuo.ydta.domain.Role;
import com.nuo.ydta.domain.Stage;
import com.nuo.ydta.dto.ClewDto;
import com.nuo.ydta.dto.TaskDto;
import com.nuo.ydta.service.ClewService;
import com.nuo.ydta.service.RoleService;
import com.nuo.ydta.service.StageService;
import com.nuo.ydta.utils.NuoPage;
import com.nuo.ydta.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@Api(description = "线索管理")
public class ClewController {

    @Autowired
    private ClewService clewService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private StageService stageService;

    /**
     * 任务列表
     */
    @PostMapping("/clew/page")
    @ResponseBody
    @ApiOperation("分页查询线索")
    public Response taskList(@RequestParam("pageIndex") int pageIndex,
                             @RequestParam("pageSize") int pageSize){
        NuoPage page = new NuoPage(pageIndex,pageSize);
        Page<Clew> clews = clewService.queryPage(page.pageIndex(), page.pageSize());

        if (clews != null && clews.hasContent()) {
            List<ClewDto> data = Lists.newArrayList();
            List<Clew> content = clews.getContent();
            for (Clew clew : content) {
                ClewDto clewDto = new ClewDto();

                BeanUtils.copyProperties(clew, clewDto);
                Role role = roleService.findRoleById(clew.getRoleId());
                Stage stage = stageService.findStageById(clew.getStageId());
                clewDto.setRoleName(role.getName());
                clewDto.setStageDesc(stage.getName());
                data.add(clewDto);
            }

            Response<List<ClewDto>> response = new Response<>();
            response.setTotal(clews.getTotalElements());
            response.setData(data);
            return response;
        }
        return new Response<>(Lists.newArrayList());
    }

    /**
     * 添加新任务
     */
    @PostMapping("/clew/add")
    @ResponseBody
    @ApiOperation("添加任务")
    public Response add(@RequestBody Clew clew){
        clewService.add(clew);
        return Response.create();
    }

    /**
     * 删除任务
     */
    @PostMapping("/clew/delete")
    @ResponseBody
    @ApiOperation("删除任务")
    public Response delete(@RequestParam("id") int id){
        clewService.delete(id);
        return Response.create();
    }

    /**
     * 修改任务：只能修改内容
     */
    @PostMapping("/clew/update")
    @ResponseBody
    @ApiOperation("修改任务")
    public Response update(@RequestBody  Clew clew){
        clewService.update(clew);
        return Response.create();
    }

    /**
     * 根据状和角色查询剧本
     */
    @PostMapping("/clew/get/role")
    @ResponseBody
    @ApiOperation("根据状和角色查询剧本")
    public Response findPlayByroleIdAndStatus(@RequestParam("status") int status,
                                              @RequestParam("roleId") int roleId){
        if(status <= 0){
            return Response.create(ProjectError.PARAM_STATUS_IS_EXCEPTION);
        }

        if(roleId <= 0){
            return Response.create(ProjectError.PARAM_ROLE_ID_IS_ERROR);
        }
        List<Clew> clewList = null;
        try {
            clewList = clewService.findAllByStatusAndRoleId(status, roleId);
        } catch (Exception e) {
            throw e;
        }
        return Response.create(clewList);
    }

}
