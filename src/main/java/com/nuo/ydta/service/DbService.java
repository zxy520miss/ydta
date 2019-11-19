package com.nuo.ydta.service;

import com.nuo.ydta.domain.Db;

import java.util.List;

public interface DbService {

    void restDb();

    List<Db> getDbList();

    void restRoleNpc();
}
