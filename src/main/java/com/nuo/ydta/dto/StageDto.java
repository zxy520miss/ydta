package com.nuo.ydta.dto;

import com.nuo.ydta.domain.Stage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StageDto implements Serializable {

    List<Stage> lists;
    int oldId;

}
