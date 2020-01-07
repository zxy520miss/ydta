package com.nuo.ydta.controller;

import com.google.common.collect.Lists;
import com.nuo.ydta.contances.ProjectError;
import com.nuo.ydta.contances.RoleGender;
import com.nuo.ydta.contances.StageStatus;
import com.nuo.ydta.contances.Status;
import com.nuo.ydta.domain.Camp;
import com.nuo.ydta.domain.Role;
import com.nuo.ydta.domain.Stage;
import com.nuo.ydta.dto.RoleDto;
import com.nuo.ydta.dto.VoteDto;
import com.nuo.ydta.exception.BaseBusinessException;
import com.nuo.ydta.repository.CampRepository;
import com.nuo.ydta.service.RoleService;
import com.nuo.ydta.service.StageService;
import com.nuo.ydta.utils.NuoPage;
import com.nuo.ydta.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Api(description = "角色管理")
@Slf4j
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private CampRepository campRepository;

    @Autowired
    private StageService stageService;

    /**
     * 通过手机串号获取该手机角色的剧本
     *
     * @param serialNo
     * @return
     */
    @GetMapping("/role/play/get")
    @ResponseBody
    @ApiOperation("通过手机串号获取该手机角色的剧本")
    public Response getRolePlay(@RequestParam("serialNo") String serialNo) {
        if (StringUtils.isBlank(serialNo)) {
            return Response.create(ProjectError.PARAM_SERIALNO_IS_ERROR);
        }
        String play = roleService.findRoleBySerialNoAndStatus(serialNo, Status.VISIBLE);
        return Response.create(play);
    }

    @GetMapping("/role/get")
    @ResponseBody
    @ApiOperation("获取单个角色")
    public Response getRoleById(@RequestParam("serialNo") String serialNo) {
        log.info("serialNo -> {}", serialNo);
        if (StringUtils.isBlank(serialNo)) {
            return Response.create(ProjectError.PARAM_SERIALNO_IS_ERROR);
        }
        RoleDto roleDto = roleService.getRoleBySerialNo(serialNo);
        if (null == roleDto) {
            return Response.create(ProjectError.ROLE_IS_NULL);
        }
        return Response.create(roleDto);
    }

    /**
     * 新增/修改角色
     * *
     *
     * @param role
     * @return
     */
    @PostMapping("/role/save")
    @ResponseBody
    @ApiOperation("新增/修改角色")
    public Response save(@RequestBody Role role) {
        if (null == role) {
            return Response.create(ProjectError.PARAM_ROLE_IS_ERROR);
        }
        if (StringUtils.isBlank(role.getPlay())) {
            return Response.create(ProjectError.PARAM_PLAY_IS_ERROR);
        }
        if (role.getCamp() < 0) {
            return Response.create(ProjectError.PARAM_CAMP_IS_ERROR);
        }
        if (StringUtils.isBlank(role.getName())) {
            return Response.create(ProjectError.PARAM_NAME_IS_ERROR);
        }
        if (StringUtils.isBlank(role.getSerialNo())) {
            return Response.create(ProjectError.PARAM_SERIALNO_IS_ERROR);
        }
        if (null == role.getGender() || (role.getGender() != RoleGender.GIRL && role.getGender() != RoleGender.BOY)) {
            return Response.create(ProjectError.PARAM_GENDER_IS_ERROR);
        }

        RoleDto roleDto = roleService.findRoleById(role.getId());
        role.setRoleDesc(roleDto.getRoleDesc());
        role.setPoll(roleDto.getPoll());
        role.setVote(roleDto.getVote());
        role.setUrl(roleDto.getUrl());
        boolean flag = roleService.save(role);
        return Response.create(flag);
    }


    /**
     * 分页查询角色
     *
     * @param pageIndex
     * @return
     */
    @PostMapping("/role/page")
    @ResponseBody
    @ApiOperation("分页查询角色")
    public Response pageQuery(@RequestParam("pageIndex") int pageIndex) {
        NuoPage page = new NuoPage(pageIndex);
        Page<Role> roles = roleService.pageQuery(page.pageIndex(), page.pageSize());

        if (roles != null && roles.hasContent()) {
            List<RoleDto> data = Lists.newArrayList();
            List<Role> content = roles.getContent();
            for (Role role : content) {
                RoleDto roleDto = new RoleDto();
                Camp one = campRepository.getOne(role.getCamp());
                BeanUtils.copyProperties(role, roleDto);
                roleDto.setCampDesc(one.getDescription());
                data.add(roleDto);
            }
            Response<List<RoleDto>> response = new Response<>();
            response.setTotal(roles.getTotalElements());
            response.setData(data);
            return response;
        }
        return new Response<>(Lists.newArrayList());
    }


    /**
     * 查询全部角色
     *
     * @return
     */
    @GetMapping("/role/list")
    @ResponseBody
    @ApiOperation("查询全部角色")
    public Response roles() {

        List<Role> roles = roleService.getRoles();
        return new Response<>(Lists.newArrayList(roles));
    }

    @PostMapping("/role/camp/update")
    @ResponseBody
    @ApiOperation("修改角色阵营")
    public Response updateRoleCamp(@RequestParam("serialNo") String serialNo, @RequestParam("camp") String camp) {
        if (StringUtils.isBlank(serialNo)) {
            return Response.create(ProjectError.PARAM_SERIALNO_IS_ERROR);
        }


        boolean flag = roleService.campCheck(serialNo);
        if (flag) {
            boolean b = roleService.updateRoleCamp(serialNo, camp);
            if (b) {
                return Response.create();
            }
            return Response.create(ProjectError.ROLE_IS_NULL);
        } else {
            return Response.create(ProjectError.ROLE_CAMP_MODIFY_ERROR);
        }
    }

    @PostMapping("/role/suspicion/update")
    @ResponseBody
    @ApiOperation("修改角色嫌疑值")
    public Response updateRoleSuspicion(@RequestBody Role role) {
        if (StringUtils.isBlank(role.getSerialNo())) {
            return Response.create(ProjectError.PARAM_SERIALNO_IS_ERROR);
        }
        if (role.getSuspicion() > 100 || role.getSuspicion() < -100) {
            return Response.create(ProjectError.PARAM_ROLE_SUSPICION_IS_ERROR);
        }
        boolean flag = roleService.updateRoleSuspicion(role.getSerialNo(), role.getSuspicion());
        if (flag) {
            return Response.create();
        }
        return Response.create(ProjectError.ROLE_IS_NULL);
    }

    @PostMapping("/role/halo/update")
    @ResponseBody
    @ApiOperation("修改角色眩晕值")
    public Response updateRoleHalo(@RequestBody Role role) {
        if (StringUtils.isBlank(role.getSerialNo())) {
            return Response.create(ProjectError.PARAM_SERIALNO_IS_ERROR);
        }
        if (role.getHalo() >= 100 || role.getHalo() <= -100) {
            return Response.create(ProjectError.PARAM_ROLE_SUSPICION_IS_ERROR);
        }
        boolean flag = roleService.updateRoleHalo(role.getSerialNo(), role.getHalo());
        if (flag) {
            return Response.create();
        }
        return Response.create(ProjectError.ROLE_IS_NULL);
    }

    @GetMapping("/role/camp/check")
    @ResponseBody
    @ApiOperation("检查角色是否可以修改阵营")
    public Response campChecck(@RequestParam("serialNo") String serialNo) {
        RoleDto roleDto = roleService.getRoleBySerialNo(serialNo);
        return Response.create(roleDto.getModifyCamp());
    }


    /**
     * 获取是否可以投票
     */

    @GetMapping("/role/vote/check")
    @ResponseBody
    @ApiOperation("检查角色是否可以投票")
    public Response voteChecck(@RequestParam("serialNo") String serialNo) {

        if (StringUtils.isBlank(serialNo)) {
            throw new BaseBusinessException(ProjectError.PARAM_IS_ERROR);
        }

        Role role = roleService.findRoleBySerialNo(serialNo);
        if (role.getVote()) {
            Stage stage = stageService.findLastStageByStatus(StageStatus.VISIBLE);
            return Response.create(stage.getId());
        } else {
            return Response.create(-1);
        }
    }
}
