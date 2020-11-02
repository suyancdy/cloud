package com.cdy.basicdata.threadStudy;

import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
/**
 * @Description
 * @Author: chendeyin
 * @Date: 2020/9/21 14:13
 * @See: com.cdy.basicdata
 * @Modified:
 */
@Slf4j
public class ThreadCreate {
    public static void main(String[] args) throws Exception {
        log.info("主线程的名字为： {}", Thread.currentThread().getName());
        // 启动继承Thread类的任务
        T t = new T();
        t.start();
        // 启动继承Thread匿名内部类的任务，可以用lambda优化
        new Thread() {
            @Override
            public void run() {
                log.info("我是Thread匿名内部类的任务");
            }
        }.start();
        // 启动实现Runnable接口的任务
        new Thread(new R()).start();
        // 启动实现Runnable匿名实现类的任务
        new Thread(new Runnable() {
            @Override
            public void run() {
                log.info("我是Runnable匿名内部类的任务");
            }
        }).start();
        // 启动实现Runnable的lambda简化后的任务
        new Thread(() -> {
            log.info("我是Runnable的lambda简化后的任务");
        }).start();
        // 启动实现了Callable接口的任务，结合FutureTask可以获取线程的结果
        FutureTask<String> stringFutureTask = new FutureTask<>(new C());
        new Thread(stringFutureTask).start();
        log.info("Callable的任务的返回值是：{}", stringFutureTask.get());
    }
}
@Slf4j
class T extends Thread {
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
        log.info("我开始进入休眠");
        Thread.sleep(10*1000);
        log.info("休眠5秒结束");
        return "success";
    }
}
