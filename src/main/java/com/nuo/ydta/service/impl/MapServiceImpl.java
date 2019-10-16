package com.nuo.ydta.service.impl;


import com.nuo.ydta.domain.Map;
import com.nuo.ydta.exception.BusinessException;
import com.nuo.ydta.repository.MapRepository;
import com.nuo.ydta.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MapServiceImpl implements MapService {

    @Autowired
    private MapRepository mapRepository;

    @Override
    public Map findMapByParentId(Integer parentId) {
        try {
            Map map = mapRepository.findByParentId(parentId);
            return map;
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    @Override
    public List<Map> findMapsByParentId(Integer parentId) {
        try {
            List<Map> lists = mapRepository.findAllByParentId(parentId);
            return lists;
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }
}
