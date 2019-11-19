package com.nuo.ydta.service;

import com.nuo.ydta.domain.Role;
import com.nuo.ydta.dto.RoleDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RoleService {

    String findRoleBySerialNoAndStatus(String serialNo, Integer status);

    boolean save(Role role);

    Page<Role> pageQuery(int pageIndex,int pageSize);

    RoleDto getRoleBySerialNo(String serialNo);

    /**
     * 检查该角色是否可以修改阵营
     * @param serialNo
     * @return
     */
    boolean campCheck(String serialNo);

    /**
     * 修改角色阵营
     * @param camp
     * @return
     */
    boolean updateRoleCamp(String serialNo,String camp);

    /**
     * 修改角色嫌疑值
     * @param suspicion
     * @return
     */
    boolean updateRoleSuspicion(String serialNo,int suspicion);

    /**
     * 修改角色嫌疑值
     * @param halo
     * @return
     */
    boolean updateRoleHalo(String serialNo, int halo);

    /**
     * 投票
     * @param roleName
     * @return
     */
    void vote(String roleName);

    List<Role> getRoles();

    RoleDto voteCheck(int roleId);

    /**
     * 根据ID获取角色
     */
    Role findRoleById(int id);

    /**
     * 根据ID获取角色
     */
    Role findRoleBySerialNo(String serialNo);

    void updateRoleVote(boolean isVote);

}
