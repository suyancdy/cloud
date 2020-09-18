package com.cestc.basicdata.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description:
 * @Author: chendeyin
 * @Date: 2020/9/9 14:14
 * @See: com.cestc.basicdata.controller
 * @Modified:
 */
@Slf4j
@Controller
@RequestMapping("/index")
public class IndexController {

    @Value("${server.port}")
    private String port;

    @ResponseBody
    @RequestMapping("/port")
    public String getPort(){
        return  port;
    }


}
