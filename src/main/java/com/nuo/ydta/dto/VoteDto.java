package com.nuo.ydta.dto;

import lombok.Data;

@Data
public class VoteDto {

    /**
     * true: 可以投票
     * false: 不可以投票
     */
    private boolean isVote;
    private int round;
}
