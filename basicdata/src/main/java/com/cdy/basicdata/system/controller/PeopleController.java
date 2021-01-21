package com.cdy.basicdata.system.controller;


import com.cdy.basicdata.system.domain.param.PageParam;
import com.cdy.basicdata.system.entity.People;
import com.cdy.basicdata.system.kafka.KafkaProducer;
import com.cdy.basicdata.system.service.IPeopleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author chendeyin
 * @since 2020-12-29
 */
@Api(tags = "人员")
@Slf4j
@RestController
@RequestMapping("/people")
public class PeopleController {

    @Autowired
    private IPeopleService iPeopleService;

    @Autowired
    private KafkaProducer kafkaProducer;


    @ApiOperation("kafka测试")
    @PostMapping(value = "/insertOne")
    public String insertOne(@RequestBody People people) throws JsonProcessingException {
        log.info("insertOne的入参为: {}", people.toString());
        people.setCreateTime(LocalDateTime.now());
        kafkaProducer.send(people);

        return "success";
    }


    @ApiOperation("条件列表查询")
    @GetMapping("/selectListByParams")
    public String selectListByParams(@ModelAttribute PageParam pageParam){
        log.info("条件列表查询的参数为: {}", pageParam.toString());
        log.debug("这是一个debug级别");
        log.info("这是一个info级别");
        log.warn("这是一个warn级别");
        log.error("这是一个error级别");
        return "success";
    }

}
