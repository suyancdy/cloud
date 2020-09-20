package com.cestc.basicdata.system.controller;


import com.cestc.basicdata.system.entity.People;
import com.cestc.basicdata.system.service.IndexService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.plugins.jpeg.JPEGImageReadParam;
import java.util.HashMap;
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


    @ResponseBody
    @RequestMapping("/port")
    public People getPort(@RequestParam("ip") String ip, @RequestParam("sex") String sex){


        String[] stringArr = {"ll", "jj"};

        Map<String, String> map = new HashMap<>();
        map.put("2", "以哦");
        People people = new People();
        people.setId(1);
        people.setName("喜喜");

       indexService.fun("hehe", people);



        return  people;
    }


}
