package com.nuo.ydta.repository;

import com.nuo.ydta.domain.Camp;
import com.nuo.ydta.domain.Relationships;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface RelationshipsRepository extends JpaRepository<Relationships, Integer>, JpaSpecificationExecutor<Relationships> {


    List<Relationships> findAllByRoleName(String roleName);

}
