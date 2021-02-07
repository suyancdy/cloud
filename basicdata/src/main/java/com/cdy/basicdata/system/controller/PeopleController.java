package com.cdy.basicdata.system.controller;


import com.alibaba.fastjson.JSON;
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
import java.util.List;

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


    @ApiOperation("列表查询")
    @GetMapping("/selectListByParams")
    public List<People> selectListByParams(@ModelAttribute PageParam pageParam){
        log.debug("条件列表查询的参数为: {}", pageParam.toString());
        return iPeopleService.listByParams(pageParam);
    }

    @ApiOperation("详情查询")
    @GetMapping("/getById")
    public People getById(@RequestParam Integer id){
        return iPeopleService.getById(id);
    }




    @ApiOperation("修改编辑")
    @PutMapping(value = "/deleteById")
    public String deleteById(@RequestBody People people) throws InterruptedException {
        log.debug("人员修改编辑的参数为: {}", JSON.toJSONString(people));
         int affectedRows =  iPeopleService.updateById(people);
        return  String.valueOf(affectedRows);
    }

}
