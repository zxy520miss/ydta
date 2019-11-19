package com.nuo.ydta.controller;

import com.nuo.ydta.domain.Db;
import com.nuo.ydta.repository.DbRepository;
import com.nuo.ydta.service.DbService;
import com.nuo.ydta.utils.Response;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@Api(description = "重置数据库")
public class DBController {

    @Autowired
    private DbService dbService;

    @PostMapping("/db/rest")
    @ResponseBody
    public Response restDB() {
         dbService.restDb();
        return Response.create();
    }

    @PostMapping("/db/list")
    @ResponseBody
    public Response dbList(){
        List<Db> dbList = dbService.getDbList();
        return Response.create(dbList);
    }

}
