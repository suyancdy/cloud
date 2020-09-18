package com.cestc.peoman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Description:
 * @Author: chendeyin
 * @Date: 2020/9/9 14:26
 * @See: com.cestc.peoman
 * @Modified:
 */
@EnableHystrix
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class PeopleManageApplication {
    public static void main(String[] args) {
        SpringApplication.run(PeopleManageApplication.class, args);
    }
}
