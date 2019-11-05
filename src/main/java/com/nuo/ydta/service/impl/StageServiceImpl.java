package com.nuo.ydta.service.impl;

import com.nuo.ydta.contances.ProjectError;
import com.nuo.ydta.contances.StageStatus;
import com.nuo.ydta.contances.Status;
import com.nuo.ydta.domain.Clew;
import com.nuo.ydta.domain.Play;
import com.nuo.ydta.domain.Stage;
import com.nuo.ydta.domain.Task;
import com.nuo.ydta.dto.StageDto;
import com.nuo.ydta.exception.BusinessException;
import com.nuo.ydta.repository.PlayRepository;
import com.nuo.ydta.repository.StageRepository;
import com.nuo.ydta.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.ProjectedPayload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StageServiceImpl implements StageService {

    @Autowired
    private StageRepository stageRepository;
    @Autowired
    private PlayService playService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private ClewService clewService;

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
    public  List<Stage> findAllStage() {
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
        Stage stage = stageRepository.getOne(id);
        stage.setStatus(StageStatus.VISIBLE);
        stageRepository.save(stage);

             //修改剧本的status为1（可获取状态）
            List<Play> plays = playService.findAllByStage(id);
            if(plays == null || plays.size() == 0){
                throw new BusinessException(ProjectError.PLAY_IS_NULL);
            }
            for (Play play: plays){
                play.setStatus(StageStatus.VISIBLE);
                playService.add(play);
            }
            //	修改任务的status为1（可获取状态）
            List<Task> tasks = taskService.findAllByStage(id);
            if(tasks == null || tasks.size() == 0){
                throw new BusinessException(ProjectError.TASK_IS_NULL);
            }

            for (Task task: tasks){
                task.setStatus(StageStatus.VISIBLE);
                taskService.add(task);
            }

            //	todo:修改线索的status为1（可获取状态）
            List<Clew> clews = clewService.findAllByStage(id);
            if(clews == null || clews.size() == 0){
                throw new BusinessException(ProjectError.CLEW_IS_NULL);
            }

            for (Clew clew: clews){
                clew.setStatus(StageStatus.VISIBLE);
                clewService.add(clew);
            }
            //	todo:修改通知的status为1（可获取状态）

        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    @Override
    public Stage findStageById(int id) {

        return stageRepository.getOne(id);
    }

    @Override
    public List<Stage> getStages() {

        int[] ids = {3,5,7};
        List<Stage> list = stageRepository.findAllByIdIn(ids);
        return list;
    }
}
