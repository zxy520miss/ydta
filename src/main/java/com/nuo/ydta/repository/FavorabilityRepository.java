package com.nuo.ydta.repository;

import com.nuo.ydta.domain.RoleNpc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavorabilityRepository extends JpaRepository<RoleNpc, Integer>, JpaSpecificationExecutor<RoleNpc> {

    RoleNpc findByRoleNameAndNpcName(String roleName,String npcName);

    List<RoleNpc> findByRoleName(String roleName);

}
