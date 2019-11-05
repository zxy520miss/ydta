package com.nuo.ydta.repository;

import com.nuo.ydta.domain.Clew;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface DbRepository extends JpaRepository<Clew,Integer>, JpaSpecificationExecutor<Clew> {

    @Query(value = "truncate table nuo_statistics;", nativeQuery = true)
    void resetStatistics();

    @Query(value = "update nuo_stage set status = 0;", nativeQuery = true)
    void resetStage();

    @Query(value = "truncate table nuo_rolenpc;", nativeQuery = true)
    void resetRoelNpc();

    @Query(value = "truncate table nuo_messagepush;", nativeQuery = true)
    void resetMessagePush();

}
