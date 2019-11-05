package com.nuo.ydta.dto;

import com.nuo.ydta.domain.Notice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NoticeDto extends Notice {

    private String roleName;
    private String stageDesc;
}
