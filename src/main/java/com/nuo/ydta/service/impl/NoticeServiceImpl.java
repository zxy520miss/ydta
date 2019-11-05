package com.nuo.ydta.service.impl;

import com.nuo.ydta.contances.ProjectError;
import com.nuo.ydta.domain.Notice;
import com.nuo.ydta.exception.BusinessException;
import com.nuo.ydta.repository.NoticeRepository;
import com.nuo.ydta.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {


    @Autowired
    private NoticeRepository noticeRepository;

    @Override
    public Page<Notice> queryPage(int pageIndex, int pageSize) {
        Pageable pageable = PageRequest.of(pageIndex,pageSize);
        return noticeRepository.findAll(pageable);
    }

    @Override
    public void add(Notice notice) {
        noticeRepository.save(notice);
    }

    @Override
    public void delete(int id) {
        noticeRepository.deleteById(id);
    }

    @Override
    public void update(Notice notice) {
        noticeRepository.getOne(notice.getId());
        noticeRepository.save(notice);
    }

    @Override
    public List<Notice> findAllByStage(int stage) {
        List<Notice> noticeList = noticeRepository.findAllByStageId(stage);
        if(noticeList == null || noticeList.size() == 0){
            throw new BusinessException(ProjectError.NOTICE_IS_NULL);
        }
        return noticeList;
    }

    @Override
    public List<Notice> findAllByStatusAndRoleId(int status, int roleId) {
        List<Notice> noticeList = noticeRepository.findAllByStatusAndRoleId(status, roleId);
        if(noticeList == null || noticeList.size() == 0){
            throw new BusinessException(ProjectError.NOTICE_IS_NULL);
        }
        return noticeList;
    }
}
