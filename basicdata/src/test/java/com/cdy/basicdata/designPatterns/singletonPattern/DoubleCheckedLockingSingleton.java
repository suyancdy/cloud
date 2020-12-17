package com.cdy.basicdata.designPatterns.singletonPattern;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 双重校验锁单例模式
 * JDK版本：JDK1.5起
 * 是否lazy初始化：是
 * 是否多线程安全：是
 * 实现难度：较复杂
 * 描述：这种方式采用双锁机制，安全且在多线程情况下能保持高性能，getInstance()的性能对应用程序很关键
 * @Author: chendeyin
 * @Date: 2020/12/8 14:20
 */
@Slf4j
public class DoubleCheckedLockingSingleton {

    private volatile static DoubleCheckedLockingSingleton instance;

    // 将构造器私有
    private DoubleCheckedLockingSingleton() {
    }

    // 静态方法提供单例
    private static DoubleCheckedLockingSingleton getInstance(){
        if (instance == null){
            synchronized (DoubleCheckedLockingSingleton.class){
                if (instance == null){
                    instance = new DoubleCheckedLockingSingleton();
                }
            }
        }

        return  instance;
    }

    public void show(){
      log.info("====");
    }


    public static void main(String[] args) {
        DoubleCheckedLockingSingleton instance = DoubleCheckedLockingSingleton.getInstance();
        instance.show();
    }

}
