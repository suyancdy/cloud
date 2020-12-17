package com.cdy.basicdata.designPatterns.singletonPattern;

import lombok.extern.slf4j.Slf4j;

/**
 * 单例模式
 * @Description: 主类
 * 一般情况下不建议使用懒汉式方式（同步与不同步的lazy加载），建议使用饿汉式（类加载模式）。
 * 只有在要明确实现lazy加载效果时，才会使用登记式/静态内部类方式。
 * 如果涉及到反序列化创建对象时，可以尝试使用枚举方式。
 * 如果有其他特殊需求，可以考虑使用双检索方式。
 * @Author: chendeyin
 * @Date: 2020/11/23 14:35
 */
@Slf4j
public class App {

    public static void main(String[] args)  throws Exception{
        // 下面的声明语句错误
        // SingleObject singleObject = new SingleObject();
//        SingleObject singleObject = SingleObject.getInstance();
//
//        singleObject.showMessage();

//        Singleton1 singleton1 = new Singleton1();
//
//        Singleton1 singleton1 = Singleton1.getInstance();
//        singleton1.showMessage();
//
//        Singleton2 singleton2 = Singleton2.getInstance();
//        singleton2.showMessage();

        Singleton singleton = Singleton.getInstance();
        singleton.show();

    }
}

@Slf4j
class Singleton {
    public volatile  static Singleton  instance;

    private Singleton(){}

    public static Singleton getInstance(){
        if(instance == null){
            log.info("===开始锁定该类对象");
           synchronized (Singleton.class){
               if (instance == null){
                   log.info("===实例化对象");
                   instance = new Singleton();
               }
           }
        }
        return instance;
    }

    public void show(){
        log.info("===DCL单例模式");
    }

}
