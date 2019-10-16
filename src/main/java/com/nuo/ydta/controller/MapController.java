package com.nuo.ydta.controller;

import com.google.common.collect.Lists;
import com.nuo.ydta.domain.Map;
import com.nuo.ydta.service.MapService;
import com.nuo.ydta.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Arrays;
import java.util.List;

@Controller
@Api(description = "地图管理")
public class MapController {

    @Autowired
    private MapService mapService;

    @GetMapping("/map/main/get")
    @ResponseBody
    @ApiOperation("获取主地图")
   public Response findMapByParentId(){
       Map map = mapService.findMapByParentId(0);
        return Response.create(map);
   }

    /**
     * 获取房间信息
     */
    @GetMapping("/map/maps/get")
    @ResponseBody
    @ApiOperation("获取小地图")
    public Response findMapsByParentIds(){
        List<Map> lists = mapService.findMapsByParentId(1);
        return Response.create(lists);
    }
}
