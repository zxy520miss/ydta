package com.nuo.ydta.dto;

import com.nuo.ydta.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleDto extends Role implements Serializable {

    private String campDesc;

}
