package com.nuo.ydta.service.impl;

import com.nuo.ydta.contances.Status;
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
            Npc npc = new Npc();
            npc.setName("系统");
            list.add(npc);
            return list;
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    @Override
    public void delete(int id) {
        try {
            npcRepository.deleteById(id);
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    @Override
    public void add(Npc npc) {
        try {
            npc.setStatus(Status.VISIBLE);
            npc.setVersion("v1");
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
