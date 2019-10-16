package com.nuo.ydta.repository;

import com.nuo.ydta.domain.Stage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StageRepository extends JpaRepository<Stage, Integer>, JpaSpecificationExecutor<Stage> {

}
