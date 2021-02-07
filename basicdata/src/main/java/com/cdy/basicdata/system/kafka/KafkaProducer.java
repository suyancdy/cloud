package com.cdy.basicdata.system.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import javax.annotation.Resource;

/**
 * @Description: kafka 生产者
 * @Author: chendeyin
 * @Date: 2021/1/20 13:36
 */
@Slf4j
@Component
public class KafkaProducer {
    @Resource
    private KafkaTemplate<String, Object> kafkaTemplate;
    @Autowired
    private ObjectMapper objectMapper;
    public static final String TOPIC = "topiclogmsg";

    public void send(Object object) throws JsonProcessingException {
        String objectString = objectMapper.writeValueAsString(object);
        log.info("准备向: {}, 发送的消息为: {}", TOPIC, objectString.toString());
        // 发送
        ListenableFuture<SendResult<String, Object>> listenableFuture =
//                kafkaTemplate.send(TOPIC, objectString);
                kafkaTemplate.send(TOPIC,1,"0", objectString);
        listenableFuture.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable ex) {
                log.info("向: {},发送消息失败, 错误信息为: {}", TOPIC, ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, Object> result) {
                log.info("向: {},发送消息成功, 信息为: {}", TOPIC, result.toString());
            }
        });
    }
}
