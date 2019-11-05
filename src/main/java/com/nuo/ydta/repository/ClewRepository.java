package com.nuo.ydta.repository;

import com.nuo.ydta.domain.Clew;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ClewRepository extends JpaRepository<Clew, Integer>, JpaSpecificationExecutor<Clew> {

    List<Clew> findAllByStageId(int stageId);


    List<Clew> findAllByStatusAndRoleId(int status,int roleId);

}
