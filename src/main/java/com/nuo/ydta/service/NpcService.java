package com.nuo.ydta.service;

import com.nuo.ydta.domain.Npc;

import java.util.List;

public interface NpcService {

    List<Npc> getNpcs();

    void delete(int id);

    void add(Npc npc);

    void update(Npc npc);

}
