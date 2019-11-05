package com.nuo.ydta.dto;

import com.nuo.ydta.domain.Task;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskDto extends Task {

    private String roleName;
    private String stageDesc;
}
