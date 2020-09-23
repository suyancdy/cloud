package com.cdy.basicdata;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;

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


    public static void main(String[] args) {
        String packageName = BootTest.class.getPackage().getName();
        log.info("测试结果为： {}", packageName);
    }

}