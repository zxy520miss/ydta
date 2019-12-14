package com.nuo.ydta.controller;


import com.nuo.ydta.contances.ProjectError;
import com.nuo.ydta.contances.Status;
import com.nuo.ydta.domain.Stage;
import com.nuo.ydta.domain.Statistics;
import com.nuo.ydta.dto.StatisticsDto;
import com.nuo.ydta.service.StageService;
import com.nuo.ydta.service.VoteService;
import com.nuo.ydta.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Controller
@Api(description = "投票管理")
@Slf4j
public class VoteController {

    @Autowired
    private VoteService voteService;

    @Autowired
    private StageService stageService;

    /**
     * 投票
     * @param statistics
     */
    @PostMapping("/role/vote")
    @ResponseBody
    @ApiOperation("投票")
    Response vote(@RequestBody Statistics statistics){

        if(statistics == null){
            Response.create(ProjectError.STATISTICS_IS_NULL);
        }
        if(StringUtils.isBlank(statistics.getRoleName())){
            Response.create(ProjectError.PARAM_ROLENAME_IS_ERROR);
        }
        if(StringUtils.isBlank(statistics.getSerialNo())){
            Response.create(ProjectError.PARAM_SERIALNO_IS_ERROR);
        }

        Stage stage = stageService.findLastStageByStatus(Status.VISIBLE);
        log.debug("round -> {}",stage.getId());
        statistics.setRound(stage.getId());

        try {
            voteService.vote(statistics);
        } catch (Exception e) {
            Response.create(ProjectError.VOTE_ERROR);
        }
        return Response.create();
    }


    /**
     * 统计每轮票数
     * @param round
     * @return
     */
    @PostMapping("/role/vital")
    @ResponseBody
    @ApiOperation("统计")
    Response vital(@RequestParam("round") int round){

        if(round <= 0){
            Response.create(ProjectError.PARAM_VOTE_ROUND_IS_ERROR);
        }

        List<StatisticsDto> lists = null;
        try {
            lists = voteService.vital(round);
        } catch (Exception e) {
            Response.create(ProjectError.VITAL_IS_ERROR);
        }
        return Response.create(lists);

    }
}
