package com.nuo.ydta.service.impl;

import com.google.common.collect.Lists;
import com.nuo.ydta.domain.Statistics;
import com.nuo.ydta.dto.StatisticsDto;
import com.nuo.ydta.repository.VoteRepository;
import com.nuo.ydta.service.VoteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    private VoteRepository voteRepository;

    @Override
    public void vote(Statistics statistics) {

        Statistics s = voteRepository.findBySerialNo(statistics.getSerialNo());

        if(s != null){
            s.setPoll(s.getPoll()+1);
            voteRepository.save(s);
        }else {
            statistics.setPoll(1);
            voteRepository.save(statistics);
        }


    }

    @Override
    public List<StatisticsDto> vital(int round) {

        List<StatisticsDto> dtoList = Lists.newArrayList();

        List<Statistics> lists  = voteRepository.findAllByRound(round);

        for (Statistics s : lists){

            StatisticsDto dto = new StatisticsDto();
            dto.setName(s.getRoleName());
            dto.setValue(s.getPoll());
            dtoList.add(dto);
        }



        return dtoList;
    }
}
