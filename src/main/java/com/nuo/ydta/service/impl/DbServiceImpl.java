package com.nuo.ydta.service.impl;

import com.google.common.collect.Lists;
import com.nuo.ydta.domain.Db;
import com.nuo.ydta.exception.BusinessException;
import com.nuo.ydta.repository.DbRepository;
import com.nuo.ydta.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
            dbRepository.insertRoleNpc();
            dbRepository.resetStage();
            dbRepository.updateStage();
            dbRepository.resetStatistics();
            dbRepository.resetRole();
            dbRepository.insertRole();
//            dbRepository.resetClew();
//            dbRepository.insertClew();
            dbRepository.resetPlay();
            dbRepository.resetTask();
            dbRepository.insertTask();
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    @Override
    public List<Db> getDbList() {

        int[] ids = {1,2,3,4,5,6,7,8};
        String[] names = {"消息库","好感度库","角色库","阶段库","票数统计库","任务库","线索库","剧本库"};
        ArrayList<Db> list = Lists.newArrayList();
        for (int i = 0; i< ids.length ;i++){
            Db db = new Db();
            db.setId(ids[i]);
            db.setName(names[i]);
            list.add(db);
        }

        return list;
    }

    @Override
    public void restRoleNpc() {
        dbRepository.resetRoelNpc();
        dbRepository.insertRoleNpc();
    }
}
