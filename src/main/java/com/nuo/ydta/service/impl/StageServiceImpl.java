package com.nuo.ydta.service.impl;

import com.nuo.ydta.contances.StageStatus;
import com.nuo.ydta.domain.*;
import com.nuo.ydta.dto.StageDto;
import com.nuo.ydta.exception.BusinessException;
import com.nuo.ydta.repository.StageRepository;
import com.nuo.ydta.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@Service
@Slf4j
public class StageServiceImpl implements StageService {

    @Autowired
    private StageRepository stageRepository;
    @Autowired
    private PlayService playService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private ClewService clewService;
    @Autowired
    private PushService pushService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private MessageService messageService;

    @Override
    public StageDto findAllStageOldId() {
        try {
            Stage oldStage = stageRepository.findTopByStatusOrderByIdDesc(StageStatus.VISIBLE);
            List<Stage> all = stageRepository.findAll();
            StageDto stageDto = new StageDto();
            stageDto.setLists(all);
            stageDto.setOldId(oldStage.getId());
            return stageDto;
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    @Override
    public List<Stage> findAllStage() {
        return stageRepository.findAll();
    }

    @Override
    public void add(Stage stage) {
        try {
            stage.setStatus(StageStatus.INVISIBLE);
            stageRepository.save(stage);
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    @Override
    public void update(Stage stage) {
        try {
            stageRepository.getOne(stage.getId());
            stageRepository.save(stage);
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateStatus(int id) {
        try {
            String title = "壹點探案";
            Stage stage = stageRepository.getOne(id);
            stage.setStatus(StageStatus.VISIBLE);
            stageRepository.save(stage);

            //todo:修改剧本的status为1（可获取状态）
            List<Play> plays = playService.findAllByStage(id);
            if (plays != null) {
                for (Play play : plays) {
                    play.setStatus(StageStatus.VISIBLE);
                    playService.add(play);
                }
            }

            //	todo:根据阶段 修改任务的status为1（可获取状态）
            List<Task> tasks = taskService.findAllByStage(id);
            if (tasks != null) {
                for (Task task : tasks) {
                    task.setStatus(StageStatus.VISIBLE);
                    taskService.add(task);
                }
            }


            //	todo:根据阶段 修改线索的status为1（可获取状态）
            List<Clew> clews = clewService.findAllByStage(id);
            if (clews != null) {
                for (Clew clew : clews) {
                    clew.setStatus(StageStatus.VISIBLE);
                    clewService.add(clew);
                }
            }

            String msg = "";
            switch (id) {
                case 2:
                    msg = "找出杀害冯律司的凶手";
                    pushService.pushAll(title, msg);
                    break;
                case 3:
                    msg = "投出杀冯律司的凶手，2分钟之内请完成投票，否则视为弃票";
                    pushService.pushAll(title, msg);
                    updateRoleVote(true);
                    voteTask();
                    break;
                case 4:
                    msg = "您回忆起了某些事！请查出杀害小白的凶手";
                    pushService.pushAll(title, msg);
                    break;
                case 5:
                    msg = "您回忆起了某些事！请查出杀害周疆主的凶手";
                    pushService.pushAll(title, msg);
                    break;
                case 6:
                    msg = "您回忆起了某些事！获得一次进入阵营的机会";
                    pushService.pushAll(title, msg);
                    break;
                case 7:
                    msg = "投出杀害小白的凶手，2分钟之内请完成投票，否则视为弃票";
                    pushService.pushAll(title, msg);
                    updateRoleVote(true);
                    voteTask();
                    break;
                case 8:
                    msg = "投出杀害周疆主的凶手，2分钟之内请完成投票，否则视为弃票";
                    pushService.pushAll(title, msg);
                    updateRoleVote(true);
                    voteTask();
                    break;
                case 9:
                    msg = "您回忆起了某些事!";
                    pushService.pushAll(title, msg);
                    break;
                case 10:
                    msg = "您回忆起了某些事!";
                    pushService.pushAll(title, msg);
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    private void updateRoleVote(boolean isVote) {
        roleService.updateRoleVote(isVote);
    }

    private void voteTask() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                log.debug("开始计时 -> {}", System.currentTimeMillis());
                try {
                    updateRoleVote(false);
                    pushService.pushAll("壹點探案", "如果你投票成功，请忽略这条消息;如果您没有进行投票，视为弃票");
                } catch (Exception e) {
                    throw e;
                } finally {
                    timer.cancel();
                    log.debug("结束");
                }
            }
        };
        timer.schedule(task, 120000);
    }

    @Override
    public Stage findStageById(int id) {

        return stageRepository.getOne(id);
    }

    @Override
    public List<Stage> getStages() {

        int[] ids = {4, 5, 6, 9, 10};
        List<Stage> list = stageRepository.findAllByIdIn(ids);
        return list;
    }

    @Override
    public Stage findLastStageByStatus(int status) {
        Stage stage = stageRepository.findTopByStatusOrderByIdDesc(status);
        return stage;
    }
}
