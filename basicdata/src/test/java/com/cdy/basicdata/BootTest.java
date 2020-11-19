package com.cdy.basicdata;

import com.cdy.basicdata.common.utils.RedisUtils;
import com.cdy.basicdata.system.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;
import javax.jws.soap.SOAPBinding;

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
    private RedisUtils redisUtils;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void test() throws Exception {
//         redisUtils.set("name", "xiahua");

        // redisTemplate.setValueSerializer(new StringRedisSerializer());

//        User user = (User) redisUtils.get("my");
//        log.info("user: {}", user.toString());
//
        User user = new User(1, "123", "哈哈哈哈哈哈", 12);

        redisUtils.set("my", user);


//        String o = (String) redisUtils.get("my");
        User u = (User) redisUtils.get("my");
        log.info("===: {}", u.toString());

    }


}
