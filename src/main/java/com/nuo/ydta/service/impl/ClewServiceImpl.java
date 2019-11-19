package com.nuo.ydta.service.impl;

import com.nuo.ydta.contances.ProjectError;
import com.nuo.ydta.domain.Clew;
import com.nuo.ydta.exception.BusinessException;
import com.nuo.ydta.repository.ClewRepository;
import com.nuo.ydta.service.ClewService;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClewServiceImpl implements ClewService {


    @Autowired
    private ClewRepository clewRepository;


    @Override
    public Page<Clew> queryPage(int pageIndex, int pageSize) {
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        return clewRepository.findAll(pageable);
    }

    @Override
    public void add(Clew clew) {
        clewRepository.save(clew);
    }

    @Override
    public void delete(int id) {
        clewRepository.deleteById(id);
    }

    @Override
    public void update(Clew clew) {
        clewRepository.getOne(clew.getId());
        clewRepository.save(clew);
    }

    @Override
    public List<Clew> findAllByStage(int stage) {
        List<Clew> clewList = clewRepository.findAllByStageId(stage);
        if(clewList == null ){
            throw new BusinessException(ProjectError.CLEW_IS_NULL);
        }
        return clewList;
    }

    @Override
    public List<Clew> findAllByStatusAndRoleId(int status, int roleId) {

        List<Clew> clewList = clewRepository.findAllByStatusAndRoleId(status, roleId);
        if(clewList == null || clewList.size() == 0){
            throw new BusinessException(ProjectError.CLEW_IS_NULL);
        }
        return clewList;
    }
}
