package com.nuo.ydta.dto;

import com.nuo.ydta.domain.Clew;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClewDto extends Clew {

    private String roleName;
    private String stageDesc;
}
