package com.nuo.ydta.service.impl;

import com.nuo.ydta.contances.Status;
import com.nuo.ydta.contances.Version;
import com.nuo.ydta.domain.Npc;
import com.nuo.ydta.exception.BusinessException;
import com.nuo.ydta.repository.NpcRepository;
import com.nuo.ydta.service.NpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NpcServiceImpl implements NpcService {

    @Autowired
    private NpcRepository npcRepository;

    @Override
    public List<Npc> getNpcs() {
        try {
            List<Npc> list = npcRepository.findAll();
            return list;
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    @Override
    public void delete(int id) {
        try {
            Npc one = npcRepository.getOne(id);
            one.setStatus(Status.DELETE);
            npcRepository.save(one);
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    @Override
    public void add(Npc npc) {
        try {
            npc.setStatus(Status.UNDELETE);
            npc.setVersion(Version.V1);
            npcRepository.save(npc);
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    @Override
    public void update(Npc npc) {
        try {
            npcRepository.save(npc);
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }
}
