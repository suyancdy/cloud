package com.cdy.basicdata.ordinary;

import lombok.extern.slf4j.Slf4j;
import sun.plugin2.jvm.CircularByteBuffer;

import java.util.concurrent.Callable;

/**
 * @Description:
 * @Author: chendeyin
 * @Date: 2020/9/21 14:13
 * @See: com.cestc.basicdata
 * @Modified:
 */
@Slf4j
public class Test {
    public static void main(String[] args) {

        log.info("主线程的名字为： {}", Thread.currentThread().getName());

        // 启动继承Thread类的任务
        T t = new T();
        t.start();


        // 启动继承Thread匿名内部类的任务，可以用lambda优化
        new Thread(){
            @Override
            public void run() {
                log.info("我是Thread匿名内部类的任务");
            }
        }.start();
    }

}

@Slf4j
class T extends  Thread{
    @Override
    public void run() {
        log.info("我是继承Thread的任务");
    }
}


@Slf4j
class R implements Runnable {
    @Override
    public void run() {
        log.info("我是实现Runnable的任务");
    }
}

@Slf4j
class C implements Callable<String> {
    @Override
    public String call() throws Exception {
        log.info("我是实现Callable的任务");
        return "success";
    }
}
