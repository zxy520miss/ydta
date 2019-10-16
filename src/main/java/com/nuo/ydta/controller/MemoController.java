package com.nuo.ydta.controller;

import com.nuo.ydta.domain.Memo;
import com.nuo.ydta.service.MemoService;
import com.nuo.ydta.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

/**
 * 备忘录，最好本地存储
 */
@Controller
@Api(description = "备忘录管理")
public class MemoController {

    @Autowired
    private MemoService memoService;

    @GetMapping("/memo/list")
    @ResponseBody
    @ApiOperation("获取备忘录列表")
   public Response getMemos(){
        List<Memo> memos = memoService.getMemos();
        return Response.create(memos);
    }

    @GetMapping("/memo/add")
    @ResponseBody
    @ApiOperation("添加备忘录")
   public Response add(@RequestBody Memo memo){
        memoService.add(memo);
        return Response.create();
   }
    @GetMapping("/memo/update")
    @ResponseBody
    @ApiOperation("修改备忘录")
   public Response update(@RequestBody Memo memo){
        memoService.update(memo);
        return Response.create();
   }

}
