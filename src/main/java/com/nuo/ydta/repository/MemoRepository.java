package com.nuo.ydta.repository;

import com.nuo.ydta.domain.Memo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MemoRepository extends JpaRepository<Memo, Integer>, JpaSpecificationExecutor<Memo> {


}
