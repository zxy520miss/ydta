package com.nuo.ydta.repository;

import com.nuo.ydta.domain.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface VoteRepository extends JpaRepository<Statistics, Integer>, JpaSpecificationExecutor<Statistics> {

    List<Statistics> findAllByRound(Integer round);

    Statistics findBySerialNo(String serialNo);

}
