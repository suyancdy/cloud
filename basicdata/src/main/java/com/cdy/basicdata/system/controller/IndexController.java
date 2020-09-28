package com.cdy.basicdata.system.controller;


import com.cdy.basicdata.system.entity.People;
import com.cdy.basicdata.system.mapper.PeopleMapper;
import com.cdy.basicdata.system.service.IndexService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: chendeyin
 * @Date: 2020/9/9 14:14
 * @See: com.cestc.basicdata.system.controller
 * @Modified:
 */
@Slf4j
@Controller
@RequestMapping("/index")
public class IndexController {


    @Value("${server.port}")
    private String port;

    @Autowired
    private IndexService indexService;


    @Autowired
    private PeopleMapper peopleMapper;


    @ResponseBody
    @RequestMapping("/")
    public String getPort(){
        return  "123";






    }


}
