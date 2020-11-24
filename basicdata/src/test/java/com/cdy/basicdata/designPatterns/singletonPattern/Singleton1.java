package com.cdy.basicdata.designPatterns.singletonPattern;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 懒汉式，线程不安全
 * 是否lazy初始化：是   延迟加载
 * 是否多线程：否
 * 实现难度：易
 * 描述：
 * 这种方式是最基本的实现方式，这种实现最大的问题就是不支持多线程。因为没有加锁synchronized，所以严格意义上它并不算单例模式
 * @Author: chendeyin
 * @Date: 2020/11/23 16:35
 */
@Slf4j
public class Singleton1 {
    private static Singleton1 instance;

    private Singleton1() {

    }

    /**
     *
     * @return
     */
    public static Singleton1 getInstance() {
        if (instance == null) {
            instance = new Singleton1();
        }
        return instance;
    }

    public void showMessage(){
      log.info("I am an instance method of a singleton pattern class(Singleton1)");
    }


}

