package com.nuo.ydta.service;

import com.nuo.ydta.domain.Relationships;

import java.util.List;

public interface RelationshipsSerivce {

    List<Relationships> findAllByRoleName(String roleName);

}
