package com.nuo.ydta.repository;

import com.nuo.ydta.domain.Play;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface PlayRepository extends JpaRepository<Play, Integer>, JpaSpecificationExecutor<Play> {

    List<Play> findAllByStage(int stage);

    List<Play> findAllByStatusAndRoleId(int status ,int roleId);

}
