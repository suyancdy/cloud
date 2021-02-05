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


    @ApiOperation("条件列表查询")
    @GetMapping("/selectListByParams")
    public List<People> selectListByParams(@ModelAttribute PageParam pageParam){
        log.info("条件列表查询的参数为: {}", pageParam.toString());
        return iPeopleService.listByParams(pageParam);
    }


    @ApiOperation("事务测试")
    @PutMapping(value = "/deleteById")
    public String deleteById(@RequestParam Integer id) throws InterruptedException {
        log.debug("事务测试的参数为: {}", id);
        People people =  new People();

        people.setId(id);
        people.setAge(33);
       // people.setIsDelete(1);
         int affectedRows =  iPeopleService.updateById(people);


        return  String.valueOf(affectedRows);
    }

}
