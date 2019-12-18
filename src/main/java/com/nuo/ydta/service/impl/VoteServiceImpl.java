package com.nuo.ydta.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.nuo.ydta.domain.Role;
import com.nuo.ydta.domain.RoleNpc;
import com.nuo.ydta.domain.Statistics;
import com.nuo.ydta.dto.StatisticsDto;
import com.nuo.ydta.repository.RoleRepository;
import com.nuo.ydta.repository.VoteRepository;
import com.nuo.ydta.service.VoteService;
import io.swagger.models.auth.In;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void vote(Statistics statistics) {

        Statistics s = voteRepository.findByRoleNameAndRound(statistics.getRoleName(),statistics.getRound());
        Role role = roleRepository.findRoleBySerialNo(statistics.getSerialNo());

        if(s != null){
            s.setPoll(s.getPoll()+1);
            s.setSerialNo(s.getSerialNo()+","+role.getName());
            voteRepository.save(s);
        }else {
            statistics.setPoll(1);
            statistics.setSerialNo(role.getName());
            voteRepository.save(statistics);
        }

        //投票成功修改 可投票属性
        role.setVote(false);
        roleRepository.save(role);
    }

    @Override
    public List<StatisticsDto> vital(int round) {

        List<StatisticsDto> dtoList = Lists.newArrayList();

        List<Statistics> lists  = voteRepository.findAllByRound(round);

        for (Statistics s : lists){

            StatisticsDto dto = new StatisticsDto();
            dto.setName(s.getRoleName());
            dto.setValue(s.getPoll());
            dto.setUsers(s.getSerialNo());
            dtoList.add(dto);
        }
        return dtoList;
    }

}
