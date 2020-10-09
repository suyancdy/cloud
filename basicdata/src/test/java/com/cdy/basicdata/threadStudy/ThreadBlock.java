package com.cdy.basicdata.threadStudy;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * 线程的阻塞
 * 线程的阻塞可以分为多种，从操作系统和Java层面阻塞的定义可能不通，
 * 但是广义上使得线程阻塞得方式有下面几种
 * 1、BIO阻塞，即采用了阻塞式IO流
 * 2、sleep(long time)让线程休眠进入阻塞状态
 * 3、a.join()调用该方法的线程进入阻塞，等待a线程执行完恢复运行
 * <p>
 * 4、synchronized或ReentrantLock造成线程未获得锁进入阻塞状态
 * 5、获得锁之后调用wait()方法也会让线程进入阻塞状态
 * 6、LockSupport.park()让线程进入阻塞状态
 *
 * @Author: chendeyin
 */
@Slf4j
public class ThreadBlock {
    public static void main(String[] args)  {
        threadSleep();
    }

    public static void threadSleep()  {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                log.info("我是Runnable匿名内部类的任务");
                log.info("进入休眠5秒");
                try {
                    Thread.sleep(1000 * 5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("休眠5秒结束");
            }
        });
        thread.start();


    }

}
