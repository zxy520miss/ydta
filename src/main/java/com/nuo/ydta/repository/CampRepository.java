package com.nuo.ydta.repository;

import com.nuo.ydta.domain.Camp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CampRepository extends JpaRepository<Camp, Integer>, JpaSpecificationExecutor<Camp> {

    Camp findByDescription(String desc);

}
