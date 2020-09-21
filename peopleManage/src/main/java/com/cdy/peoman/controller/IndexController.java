package com.cdy.peoman.controller;

import com.cdy.peoman.service.IndexService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description:
 * @Author: chendeyin
 * @Date: 2020/9/9 14:44
 * @See: com.cestc.peoman.controller
 * @Modified:
 */
@Slf4j
@Controller
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private IndexService indexService;

    @RequestMapping("/port")
    @ResponseBody
    public String post(){

        return  indexService.getPost();
    }

}
