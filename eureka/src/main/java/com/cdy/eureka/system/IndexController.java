package com.cdy.eureka.system;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * @Description:
 * @Author: chendeyin
 * @Date: 2021/1/21 9:49
 */
@Slf4j
@RestController
@RequestMapping("/index")
public class IndexController {

    @GetMapping(value = "/")
    public String index(){
        log.debug("这是一条dug日志,时间为:{}", LocalDateTime.now());
        log.info("  一条info日志,时间为:{}", LocalDateTime.now());
        log.warn("warn  日志,时间为:{}", LocalDateTime.now());
        log.error("error一条,  时间为:{}", LocalDateTime.now());
        return "success";
    }

    @GetMapping(value = "/test")
    public String indexTest(){
        log.debug("这是一条dug日志,时间为:{}", LocalDateTime.now());
        log.info("一条info日志,时间为:{}", LocalDateTime.now());
        log.warn("warn日志,时间为:{}", LocalDateTime.now());
        log.error("error一天,时间为:{}", LocalDateTime.now());

        int x = 1/0;
        log.info("x的值为: {}", x);

        return "success";
    }


}
