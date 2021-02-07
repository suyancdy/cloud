package com.cdy.basicdata.system.kafka;

import com.cdy.basicdata.system.entity.People;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.cdy.basicdata.system.kafka.KafkaProducer.TOPIC;

/**
 * @Description: kafka消费者
 * @Author: chendeyin
 * @Date: 2021/1/20 13:54
 */
@Slf4j
@Component
public class KafkaConsumer {
    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topicPartitions = {@TopicPartition(topic = TOPIC, partitions = {"1"})})
    public void topicTest(ConsumerRecord<?, ?> consumerRecord,
                          Acknowledgment acknowledgment,
                          @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) throws JsonProcessingException {
        Optional messageOptional = Optional.ofNullable(consumerRecord.value());
        if (messageOptional.isPresent()) {
            String msg = (String) messageOptional.get();
            People people = objectMapper.readValue(msg, People.class);
            log.info("====: {}", people.toString());
            log.info("topicTest消费了: 主题为: {}, 消息为: {}", topic, msg);
            acknowledgment.acknowledge();
        }
    }
}
