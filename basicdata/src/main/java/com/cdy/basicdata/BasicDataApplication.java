package com.cdy.basicdata;

import com.cdy.basicdata.common.configuration.AppInfoConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Description:
 * @Author: chendeyin
 * @Date: 2020/9/9 12:07
 * @See: com.cestc.basicdata
 * @Modified:
 */

//@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.cdy.basicdata.system.mapper")

public class BasicDataApplication {
    public static void main(String[] args) {
        SpringApplication.run(BasicDataApplication.class,args);
    }
}
