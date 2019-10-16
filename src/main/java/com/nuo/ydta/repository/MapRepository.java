package com.nuo.ydta.repository;

import com.nuo.ydta.domain.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface MapRepository extends JpaRepository<Map, Integer>, JpaSpecificationExecutor<Map> {

    Map findByParentId(Integer parentId);

    List<Map> findAllByParentId(Integer parentId);
}
