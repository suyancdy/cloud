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
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

import static com.cdy.basicdata.system.kafka.KafkaProducer.TOPIC;

/**
 * <p>
 * 前端控制器
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


    @ApiOperation("kafka读取指定offset的消息")
    @GetMapping("/offset")
    public String offset() {
        //Kafka consumer configuration settings
        String topicName = TOPIC;
        Properties props = new Properties();

        props.put("bootstrap.servers", "47.108.154.182:9092");
        props.put("group.id", "test");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");

        //要发送自定义对象，需要指定对象的反序列化类
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, Object> consumer = new KafkaConsumer<String, Object>(props);
        Map<TopicPartition, OffsetAndMetadata> hashMaps = new HashMap<TopicPartition, OffsetAndMetadata>();
        hashMaps.put(new TopicPartition(topicName, 1), new OffsetAndMetadata(1));
        consumer.commitSync(hashMaps);
        consumer.subscribe(Arrays.asList(topicName));

        ConsumerRecords<String, Object> records = consumer.poll(Duration.ofHours(1));
        for (ConsumerRecord<String, Object> record : records) {
            System.out.println(record.toString());
        }
        return "123";
    }


    @ApiOperation("列表查询")
    @GetMapping("/selectListByParams")
    public List<People> selectListByParams(@ModelAttribute PageParam pageParam) {
        log.debug("条件列表查询的参数为: {}", pageParam.toString());
        return iPeopleService.listByParams(pageParam);
    }

    @ApiOperation("详情查询")
    @GetMapping("/getById")
    public People getById(@RequestParam Integer id) {
        return iPeopleService.getById(id);
    }


    @ApiOperation("修改编辑")
    @PutMapping(value = "/deleteById")
    public String deleteById(@RequestBody People people) throws InterruptedException {
        log.debug("人员修改编辑的参数为: {}", JSON.toJSONString(people));
        int affectedRows = iPeopleService.updateById(people);
        return String.valueOf(affectedRows);
    }

}
