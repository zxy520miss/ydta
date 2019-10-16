package com.nuo.ydta.service.impl;

import com.nuo.ydta.contances.StageStatus;
import com.nuo.ydta.domain.Stage;
import com.nuo.ydta.exception.BusinessException;
import com.nuo.ydta.repository.StageRepository;
import com.nuo.ydta.service.StageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StageServiceImpl implements StageService {

    @Autowired
    private StageRepository stageRepository;

    @Override
    public List<Stage> findAllStage() {
        try {
            return stageRepository.findAll();
        } catch (Exception e) {
            throw new BusinessException(e);
        }
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
            stageRepository.save(stage);
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    @Override
    public void updateStatus(int index) {
        try {

        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }
}
