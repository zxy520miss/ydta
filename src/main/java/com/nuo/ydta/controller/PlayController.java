package com.nuo.ydta.controller;

import com.google.common.collect.Lists;
import com.nuo.ydta.contances.ProjectError;
import com.nuo.ydta.contances.StageStatus;
import com.nuo.ydta.contances.Status;
import com.nuo.ydta.domain.Camp;
import com.nuo.ydta.domain.Play;
import com.nuo.ydta.domain.Role;
import com.nuo.ydta.domain.Stage;
import com.nuo.ydta.dto.PlayDto;
import com.nuo.ydta.dto.RoleDto;
import com.nuo.ydta.exception.BusinessException;
import com.nuo.ydta.service.PlayService;
import com.nuo.ydta.service.RoleService;
import com.nuo.ydta.service.StageService;
import com.nuo.ydta.utils.NuoPage;
import com.nuo.ydta.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Api(description = "剧本管理")
public class PlayController {


    @Autowired
    private PlayService playService;

    @Autowired
    private StageService stageService;

    @Autowired
    private RoleService roleService;


    /**
     * 查询所有
     */
    @PostMapping("/play/page")
    @ResponseBody
    @ApiOperation("分页查询所有剧本")
        public Response pageQuery(@RequestParam("pageIndex") int pageIndex) {
            NuoPage page = new NuoPage(pageIndex);
            Page<Play> plays = playService.pageQuery(page.pageIndex(), page.pageSize());

            if (plays != null && plays.hasContent()) {
                List<PlayDto> data = Lists.newArrayList();
                List<Play> content = plays.getContent();
                for (Play play : content) {
                    PlayDto playDto = new PlayDto();

                    BeanUtils.copyProperties(play, playDto);
                    Role role = roleService.findRoleById(play.getRoleId());
                    Stage stage = stageService.findStageById(play.getStage());
                    playDto.setRoleName(role.getName());
                    playDto.setStageDesc(stage.getName());
                    data.add(playDto);
                }

                Response<List<PlayDto>> response = new Response<>();
                response.setTotal(plays.getTotalElements());
                response.setData(data);
                return response;
            }
            return new Response<>(Lists.newArrayList());
    }

    /**
     * 添加
     */
    @PostMapping("/play/save")
    @ResponseBody
    @ApiOperation("添加剧本")
public Response add(@RequestBody Play play){

        if(play == null){
            return Response.create(ProjectError.PLAY_IS_NULL);
        }
        if(play.getId() <= 0){
            return Response.create(ProjectError.PARAM_PLAY_ID_IS_ERROR);
        }
        if(play.getStage() <= 0){
            return Response.create(ProjectError.PARAM_PLAY_STAGE_IS_ERROR);
        }
        if(StringUtils.isBlank(play.getContent())){
            return Response.create(ProjectError.PARAM_PLAY_CONTENT_IS_ERROR);
        }

    try {
        playService.add(play);
    } catch (Exception e) {
        throw e;
    }
    return Response.create();
}

    /**
     * 修改
     */
    @PostMapping("/play/update")
    @ResponseBody
    @ApiOperation("修改剧本")
    public Response update(@RequestBody Play play){
        if(play == null){
            return Response.create(ProjectError.PLAY_IS_NULL);
        }
        if(play.getId() <= 0){
            return Response.create(ProjectError.PARAM_PLAY_ID_IS_ERROR);
        }
        if(play.getStage() <= 0){
            return Response.create(ProjectError.PARAM_PLAY_STAGE_IS_ERROR);
        }
        if(StringUtils.isBlank(play.getContent())){
            return Response.create(ProjectError.PARAM_PLAY_CONTENT_IS_ERROR);
        }

        try {
            playService.update(play);
        } catch (Exception e) {
            throw e;
        }
        return Response.create();
    }

    /**
     * 删除剧本
     */
    @PostMapping("/play/delete")
    @ResponseBody
    @ApiOperation("删除剧本")
    public Response delete(@RequestParam("id") int id){

        try {
            playService.delete(id);
        } catch (Exception e) {
            throw e;
        }

        return Response.create();
    }

    /**
     * 根据状和角色查询剧本
     */
    @GetMapping("/play/get/role")
    @ResponseBody
    @ApiOperation("根据状和角色查询剧本")
    public Response findPlayByroleIdAndStatus(@RequestParam("roleId") int roleId){

        if(roleId <= 0){
            return Response.create(ProjectError.PARAM_ROLE_ID_IS_ERROR);
        }
        List<Play> playList = null;
        try {
            playList = playService.findAllByStatusAndRoleId(Status.VISIBLE, roleId);
        } catch (Exception e) {
            throw e;
        }

        return Response.create(playList);
    }

}
