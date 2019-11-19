package com.nuo.ydta.repository;

import com.nuo.ydta.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>, JpaSpecificationExecutor<Role> {

    /**
     * 通过 serialNo 和 status 查询Role
     * @param serialNo
     * @param status
     * @return
     */
    Role findRoleBySerialNoAndStatus(String serialNo,Integer status);

    /**
     * 通过名字获取Role角色
     * @param name
     * @return
     */
    Role getRoleByName(String name);


    Role findRoleBySerialNo(String serialNo);
}
