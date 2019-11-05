package com.nuo.ydta.repository;

import com.nuo.ydta.domain.Stage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface StageRepository extends JpaRepository<Stage, Integer>, JpaSpecificationExecutor<Stage> {


    Stage findTopByStatusOrderByIdDesc(int status);

    List<Stage> findAllByIdIn(int[] ids);

}
