package com.cdy.basicdata.threadStudy;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * @Description: 线程的一些方法
 * @Author: chendeyin
 * @Date: 2020/9/23 14:54
 * @See: com.cdy.basicdata.threadStudy
 * @Modified:
 */
@Slf4j
public class ThreadMethod {


    public static void main(String[] args) throws InterruptedException {
        int cpuCore =  Runtime.getRuntime().availableProcessors();
        log.info("当前cpu的核心数为： {}", cpuCore);
        daemonThreads();
        Thread.sleep(8*1000);
    }
    /**
     * @description:  线程的礼让与线程的优先级
     *
     */
    public static void threadYield(){
        // 线程的礼让
        // public static native void yield();
        Runnable runnable1 = () -> {
            int count = 0;
            for (; ; ) {
                log.info("-------runnable1-----------" + count++);
            }
        };
        Runnable runnable2 = () -> {
            int count = 0;
            for (; ; ) {
                Thread.yield();
                log.info("-------runnable2--" + count++);

            }
        };
        Thread thread1 = new Thread(runnable1, "one");
        Thread thread2 = new Thread(runnable2, "two");
        thread2.start();
        thread1.start();
    }

    /**
     * @description:  守护线程
     * 默认情况下，Java的进程需要等待所有线程都运行结束才会结束，
     * 有一种特殊的线程叫做守护线程（daemon threads），
     * 当所有的非守护线程都结束后，即使它没有执行完，也会强制结束。
     *
     * 默认的线程都是非守护线程。
     * 垃圾回收线程就是典型的守护线程。
     */
    public static void daemonThreads() {
        // 设置为守护线程
        //public final void setDaemon(boolean b)
        Thread thread = new Thread( () ->{
            log.info("启动线程");
            while (true){
                log.info("当前时间为： {}", new Date(System.currentTimeMillis()));
                try {
                    Thread.sleep(1*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        // 设置线程为守护线程
        thread.setDaemon(true);
        // 启动线程
        thread.start();
    }






}
