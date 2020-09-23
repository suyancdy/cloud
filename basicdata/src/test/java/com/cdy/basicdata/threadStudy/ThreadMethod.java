package com.cdy.basicdata.threadStudy;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 线程的一些方法
 * @Author: chendeyin
 * @Date: 2020/9/23 14:54
 * @See: com.cdy.basicdata.threadStudy
 * @Modified:
 */
@Slf4j
public class ThreadMethod {
    // 线程的礼让
    // public static native void yield();

    public static void main(String[] args) {

        Runnable runnable1 = () -> {
            int count = 0;
            for (; ; ) {
                log.info("-------runnable1-----" + count++);
            }
        };

        Runnable runnable2 = () -> {
            int count = 0;
            for (; ; ) {
              //  Thread.yield();
                log.info("runnable2-----" + count++);

            }
        };


        Thread thread1 = new Thread(runnable1, "one");
        Thread thread2 = new Thread(runnable2, "two");

        thread2.start();

        thread1.start();


    }

}
