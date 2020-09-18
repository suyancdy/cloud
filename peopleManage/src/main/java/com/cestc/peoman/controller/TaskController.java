package com.cestc.peoman.controller;

import com.cestc.peoman.service.feign.BasicDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description:
 * @Author: chendeyin
 * @Date: 2020/9/9 16:40
 * @See: com.cestc.peoman.controller
 * @Modified:
 */
@Slf4j
@Controller
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private BasicDataService basicDataService;


    @RequestMapping("/port")
    @ResponseBody
    public String port(){
        return  basicDataService.feginPort();
    }


}
