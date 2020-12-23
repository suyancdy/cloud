package com.cdy.basicdata;

import com.cdy.basicdata.common.utils.RedisUtils;
import com.cdy.basicdata.system.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.util.Random;

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
    public  void test() throws Exception {
        User user = new User(1, "123", "哈哈哈哈哈哈");
        log.info("===: {}", user.toString());
        log.info("参数为：{}", objectMapper.writeValueAsString(user));

    }
}
