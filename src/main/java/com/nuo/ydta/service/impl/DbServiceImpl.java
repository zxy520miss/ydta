package com.nuo.ydta.service.impl;

import com.nuo.ydta.exception.BusinessException;
import com.nuo.ydta.repository.DbRepository;
import com.nuo.ydta.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DbServiceImpl implements DbService {

    @Autowired
    private DbRepository dbRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void restDb() {
        try {
            dbRepository.resetMessagePush();
            dbRepository.resetRoelNpc();
            dbRepository.resetStage();
            dbRepository.resetStatistics();
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }
}
