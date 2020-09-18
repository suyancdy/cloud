package com.cestc.peoman.service.impl;

import com.cestc.peoman.service.IndexService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @Description:
 * @Author: chendeyin
 * @Date: 2020/9/9 14:42
 * @See: com.cestc.peoman.service.impl
 * @Modified:
 */
@Slf4j
@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String errorString() {
        return "服务不可用！";
    }

    @HystrixCommand(fallbackMethod = "errorString")
    @Override
    public String getPost() {
        log.info("开始调用第三方basicData的服务");
        String post =  restTemplate.getForObject("http://BASICDATA/index/port", String.class);
        return post;
    }


    



}
