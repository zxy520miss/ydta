package com.nuo.ydta.service.impl;

import com.nuo.ydta.domain.Relationships;
import com.nuo.ydta.repository.RelationshipsRepository;
import com.nuo.ydta.service.RelationshipsSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelationshipsSerivceImpl implements RelationshipsSerivce {

    @Autowired
    private RelationshipsRepository relationshipsRepository;

    @Override
    public List<Relationships> findAllByRoleName(String roleName) {
        return relationshipsRepository.findAllByRoleName(roleName);
    }
}
