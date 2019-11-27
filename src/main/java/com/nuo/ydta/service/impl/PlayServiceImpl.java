package com.nuo.ydta.service.impl;

import com.nuo.ydta.contances.Status;
import com.nuo.ydta.domain.Play;
import com.nuo.ydta.repository.PlayRepository;
import com.nuo.ydta.service.PlayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayServiceImpl implements PlayService {


    @Autowired
    private PlayRepository playRepository;

    @Override
    public Page<Play> pageQuery(int pageIndex, int pageSize) {
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        return playRepository.findAll(pageable);
    }

    @Override
    public void update(Play play) {

        playRepository.getOne(play.getId());
        playRepository.save(play);

    }

    @Override
    public void add(Play play) {
        playRepository.save(play);
    }

    @Override
    public void delete(int id) {
        playRepository.deleteById(id);
    }

    @Override
    public List<Play> findAllByStage(int stage) {
        List<Play> lists = playRepository.findAllByStage(stage);
        return lists;
    }

    @Override
    public List<Play> findAllByStatusAndRoleId(int status, int roleId) {
        List<Play> playList = playRepository.findAllByStatusAndRoleId(status, roleId);
        return playList;
    }

}
