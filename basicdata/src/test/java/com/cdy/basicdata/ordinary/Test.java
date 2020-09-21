package com.cdy.basicdata.ordinary;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;

/**
 * @Description:
 * @Author: chendeyin
 * @Date: 2020/9/21 14:13
 * @See: com.cestc.basicdata
 * @Modified:
 */
public class Test {
    public static void main(String[] args) {
        T t = new T();
        t.start();



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
