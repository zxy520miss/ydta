package com.nuo.ydta.service;

import com.nuo.ydta.domain.Map;

import java.util.List;

public interface MapService {

    /**
     * 获取总地图信息
     */
     Map findMapByParentId(Integer parentId);

    /**
     * 获取房间信息
     */
    List<Map> findMapsByParentId(Integer parentId);
}
