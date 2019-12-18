package com.nuo.ydta.service;

import com.nuo.ydta.domain.Statistics;
import com.nuo.ydta.dto.StatisticsDto;

import java.util.List;

public interface VoteService {

    /**
     * 投票
     * @param statistics
     */
    void vote(Statistics statistics);


    /**
     * 统计每轮票数
     * @param round
     * @return
     */
    List<StatisticsDto> vital(int round);

}
