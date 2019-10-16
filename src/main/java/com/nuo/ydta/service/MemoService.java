package com.nuo.ydta.service;

import com.nuo.ydta.domain.Memo;

import java.util.List;

public interface MemoService {

    List<Memo> getMemos();

    void add(Memo memo);

    void update(Memo memo);

}
