package com.cestc.basicdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description:
 * @Author: chendeyin
 * @Date: 2020/9/9 12:07
 * @See: com.cestc.basicdata
 * @Modified:
 */
@EnableDiscoveryClient
@SpringBootApplication
public class BasicDataApplication {
    public static void main(String[] args) {
        SpringApplication.run(BasicDataApplication.class,args);
    }
}
