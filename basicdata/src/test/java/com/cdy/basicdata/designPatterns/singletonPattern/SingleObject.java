package com.cdy.basicdata.designPatterns.singletonPattern;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Description: 单例模式（(饿汉式）
 * @Author: chendeyin
 * @Date: 2020/11/23 14:29
 */
@Slf4j
public class SingleObject {
    // 
    private static SingleObject instance = new SingleObject();

    private SingleObject() {
    }

    public static SingleObject getInstance(){
        return instance;
    }

    public void  showMessage(){
      log.info("I am an instance method of a singleton pattern class");
    }
}
