package com.cdy.basicdata.designPatterns.singletonPattern;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Description: 单例模式（饿汉式），
 * 是否lazy初始化：否
 * 是否多线程安全：是
 * 实现难度：易
 * 这种方法比较常用，但是容易产生垃圾对象
 * 优点：没有加锁，执行效率会提高
 * 缺点：类加载就初始化，浪费内存
 * 该方式基于类加载机制避免了多线程的同步问题，不过instance在类加载时就实例化
 * 虽然导致类加载的原因有很多种，在单例模式中大多都是调用getInstance方法，
 * 但是也不能确定有其他的（或者其他静态方法）导致类加载，这个时候实例化instance
 * 显然没有达到lazy loading的效果
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
@Slf4j
class SingleObject1{
    private static final SingleObject1 INSTANCE;
    // 将初始化放入静态代码块中
    static {
        INSTANCE = new SingleObject1();
    }

    public SingleObject1 getInstance(){
        return INSTANCE;
    }

    public void show(){
        log.info("饿汉式");
    }

}
