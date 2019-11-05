package com.nuo.ydta.dto;

import com.nuo.ydta.domain.RoleNpc;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleNpcDto extends RoleNpc {

    private String desc;

}
