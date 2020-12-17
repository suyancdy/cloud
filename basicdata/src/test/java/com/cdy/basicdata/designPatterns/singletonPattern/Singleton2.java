package com.cdy.basicdata.designPatterns.singletonPattern;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 懒汉式 线程安全
 * 是否lazy初始化：是
 * 是否多线程安全：是
 * 实现难度：易
 * 描述：这种方式具备很好的lazy loading，能够在多线程中很好的工作，但是效率低下，99%的情况下不需要同步
 * 优点：第一次调用才初始化，避免内存浪费
 * 缺点：必须加锁synchronized才能保证单例，但是加锁会影响效率
 * getInstance()的性能对应用程序不是很关键（该方法使用不太频繁）
 * @Author: chendeyin
 * @Date: 2020/11/23 17:08
 */
@Slf4j
public class Singleton2 {

    private static Singleton2 instance;

    private Singleton2() {

    }

    public static synchronized Singleton2 getInstance() {
        if (instance == null) {
            instance = new Singleton2();
        }
        return instance;
    }

    public void showMessage(){
        log.info("===: Singleton2");

    }

}
