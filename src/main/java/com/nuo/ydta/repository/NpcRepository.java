package com.nuo.ydta.repository;

import com.nuo.ydta.domain.Npc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface NpcRepository extends JpaRepository<Npc, Integer>, JpaSpecificationExecutor<Npc> {

}
