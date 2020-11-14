package com.cdy.basicdata.common.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @Description:
 * @Author: chendeyin
 * @Date: 2020/11/6 11:27
 */
@PropertySource(value = {"classpath:config/custom.properties"}, encoding = "utf-8")
@Configuration
public class AppInfoConfig {

    @Value("${app.name}")
    private String name;

    @Value("${app.version}")
    private String version;


    @Override
    public String toString() {
        return "AppInfoConfig{" +
                "name='" + name + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
