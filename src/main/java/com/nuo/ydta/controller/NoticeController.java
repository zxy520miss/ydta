package com.nuo.ydta.controller;

import com.google.common.collect.Lists;
import com.nuo.ydta.contances.ProjectError;
import com.nuo.ydta.domain.Clew;
import com.nuo.ydta.domain.Notice;
import com.nuo.ydta.domain.Role;
import com.nuo.ydta.domain.Stage;
import com.nuo.ydta.dto.ClewDto;
import com.nuo.ydta.dto.NoticeDto;
import com.nuo.ydta.service.NoticeService;
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
@Api(description = "通知管理")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private StageService stageService;

    /**
     * 任务列表
     */
    @PostMapping("/notice/page")
    @ResponseBody
    @ApiOperation("分页查询线索")
    public Response taskList(@RequestParam("pageIndex") int pageIndex,
                             @RequestParam("pageSize") int pageSize){
        NuoPage page = new NuoPage(pageIndex,pageSize);
        Page<Notice> notices = noticeService.queryPage(page.pageIndex(), page.pageSize());

        if (notices != null && notices.hasContent()) {
            List<NoticeDto> data = Lists.newArrayList();
            List<Notice> content = notices.getContent();
            for (Notice notice : content) {
                NoticeDto noticeDto = new NoticeDto();

                BeanUtils.copyProperties(notice, noticeDto);
                Role role = roleService.findRoleById(notice.getRoleId());
                Stage stage = stageService.findStageById(notice.getStageId());
                noticeDto.setRoleName(role.getName());
                noticeDto.setStageDesc(stage.getName());
                data.add(noticeDto);
            }

            Response<List<NoticeDto>> response = new Response<>();
            response.setTotal(notices.getTotalElements());
            response.setData(data);
            return response;
        }
        return new Response<>(Lists.newArrayList());
    }

    /**
     * 添加新任务
     */
    @PostMapping("/notice/add")
    @ResponseBody
    @ApiOperation("添加任务")
    public Response add(@RequestBody Notice notice){

        try {
            noticeService.add(notice);
        } catch (Exception e) {
            throw e;
        }
        return Response.create();
    }

    /**
     * 删除任务
     */
    @PostMapping("/notice/delete")
    @ResponseBody
    @ApiOperation("删除任务")
    public Response delete(@RequestParam("id") int id){
        try {
            noticeService.delete(id);
        } catch (Exception e) {
            throw e;
        }
        return Response.create();
    }

    /**
     * 修改任务：只能修改内容
     */
    @PostMapping("/notice/update")
    @ResponseBody
    @ApiOperation("修改任务")
    public Response update(@RequestBody  Notice notice){
        try {
            noticeService.update(notice);
        } catch (Exception e) {
            throw e;
        }
        return Response.create();
    }

    /**
     * 根据状和角色查询消息
     */
    @PostMapping("/notice/get/role")
    @ResponseBody
    @ApiOperation("根据状和角色查询通知")
    public Response findPlayByroleIdAndStatus(@RequestParam("status") int status,
                                              @RequestParam("roleId") int roleId){
        if(status <= 0){
            return Response.create(ProjectError.PARAM_STATUS_IS_EXCEPTION);
        }

        if(roleId <= 0){
            return Response.create(ProjectError.PARAM_ROLE_ID_IS_ERROR);
        }
        List<Notice> noticeList = null;
        try {
            noticeList = noticeService.findAllByStatusAndRoleId(status, roleId);
        } catch (Exception e) {
            throw e;
        }
        return Response.create(noticeList);
    }
}
