package com.nuo.ydta.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StatisticsDto  implements Serializable {

    private String name;
    private int value;
    private String users;


}
