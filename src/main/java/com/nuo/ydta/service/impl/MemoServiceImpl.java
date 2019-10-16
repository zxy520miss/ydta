package com.nuo.ydta.service.impl;

import com.nuo.ydta.contances.Version;
import com.nuo.ydta.domain.Memo;
import com.nuo.ydta.exception.BusinessException;
import com.nuo.ydta.repository.MemoRepository;
import com.nuo.ydta.service.MemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemoServiceImpl implements MemoService {

    @Autowired
    private MemoRepository memoRepository;

    @Override
    public List<Memo> getMemos() {
        try {
            List<Memo> list = memoRepository.findAll();
            return list;
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    @Override
    public void add(Memo memo) {
        try {
            memo.setVersion(Version.V1);
            memoRepository.save(memo);
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    @Override
    public void update(Memo memo) {
        try {
            memoRepository.save(memo);
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }
}
