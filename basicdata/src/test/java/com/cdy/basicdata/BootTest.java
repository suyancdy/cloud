package com.cdy.basicdata;

import com.cdy.basicdata.system.entity.People;
import com.cdy.basicdata.system.mapper.PeopleMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Date;
import java.util.UUID;

/**
 * @Description:
 * @Author: chendeyin
 * @Date: 2020/9/20 14:41
 * @See: com.cestc.basicdata
 * @Modified:
 */
@Slf4j
@SpringBootTest
public class BootTest {

    @Autowired
    private PeopleMapper peopleMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void fun(){

        log.info("开始");
        for (int i = 0; i < 10000; i++) {
            People people = new People();
            people.setPeopleNo(UUID.randomUUID().toString().replaceAll("-", ""));
            people.setName("我是" + i);
            people.setSex(1);
            people.setAge(i);
            people.setBirthday(new Date());
            peopleMapper.insert(people);
        }
        log.info("结束");
    }

    @Test
    public  void main() {
        redisTemplate.setKeySerializer( new StringRedisSerializer());
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
        People people = new People();
        people.setPeopleNo(UUID.randomUUID().toString().replaceAll("-", ""));
        people.setName("我是" + 1);
        people.setSex(1);
        people.setAge(1);
        people.setBirthday(new Date());
        redisTemplate.opsForValue().set("my", people);
        log.info(UUID.randomUUID().toString().replaceAll("-", ""));
    }
}
