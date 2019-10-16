package com.nuo.ydta.service;

import com.nuo.ydta.domain.Stage;
import java.util.List;

public interface StageService {

    /**
     * 查询所有阶段
     * @return
     */
    List<Stage> findAllStage();

    /**
     * 新增
     * @param stage
     * @return
     */
    void add(Stage stage);


    /**
     * 修改阶段
     * @param stage
     */
    void update(Stage stage);

    /**
     * 修改阶段状态
     * @param index  记录修改的位置
     */
    void  updateStatus(int index );

}
