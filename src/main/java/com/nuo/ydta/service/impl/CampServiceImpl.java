package com.nuo.ydta.service.impl;

import com.nuo.ydta.domain.Camp;
import com.nuo.ydta.exception.BusinessException;
import com.nuo.ydta.repository.CampRepository;
import com.nuo.ydta.service.CampService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampServiceImpl implements CampService {

    @Autowired
    private CampRepository campRepository;

    @Override
    public List<Camp> getCamps() {
        try {
            return campRepository.findAll();
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    @Override
    public void addCamp(Camp camp) {

        try {
            campRepository.save(camp);
        } catch (Exception e) {
            throw new BusinessException(e);
        }

    }

    @Override
    public void updateCamp(Camp camp) {
        try {
            campRepository.getOne(camp.getId());
            campRepository.save(camp);
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    @Override
    public void deleteCamp(Camp camp) {
        try {
            campRepository.delete(camp);
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }
}
