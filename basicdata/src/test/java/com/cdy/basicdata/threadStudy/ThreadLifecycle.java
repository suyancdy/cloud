package com.cdy.basicdata.threadStudy;

import lombok.extern.slf4j.Slf4j;

/**
 * 线程的生命周期
 * 当线程被创建并启动后，他并不是一启动就进入了执行状态，也不是一直处于执行状态，
 * 在线程的生命周期中，他要经历新建（New）、就绪（Runnable）、运行（Running）、
 * 阻塞（Blocked）和死亡（Dead）5种状态。
 * 当线程启动以后，他不可能一直霸占着CPU独自运行，所以CPU需要在多条线程之间切换，
 * 于是线程状态也会多次在运行，阻塞之间切换
 *
 * @Author: chendeyin
 * @Modified:
 */
@Slf4j
public class ThreadLifecycle {

    public static void main(String[] args) throws InterruptedException{

        ThreadLifecycle.newAndRunnable();
        log.info("---");
    }


    // 新建和就绪状态
    public static void newAndRunnable() throws InterruptedException {
        /**当程序使用new关键字创建一个线程之后，
        该线程就处于新建状态，此时他和其他的Java对象一样，
        仅仅由Java虚拟机为其分配内存，并初始化成员变量的值。
        此时的线程对象没有表现出任何线程的动态特征，程序也不会执行线程执行体。
         */
        Thread t = new Thread(() -> {
            log.info("我是Runnable的lambda简化后的任务,即将休眠5秒");
            try {
                Thread.sleep(5 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("休眠5秒结束");
        });
        /*  当线程对象调用了start()方法之后，该线程处于就绪状态，
        Java虚拟机会为其创建方法调用栈和程序技术器，处于这个状态中的线程并没有开始运行，
        只是表示该线程可以运行了。至于线程何时开始运行，取决于JVM里线程调度器的调度。
            调用线程的对象的start()方法之后，该线程立即进入就绪状态->就绪状态相当于“等待执行”，
        线程并未真正进入运行状态
            如果希望调用子线程立即开始执行，程序可以使用Thread.sleep(0)或Thread.sleep(1)来让当前的线程（主线程）让出或休眠1毫秒。
         */
//        t.start();

        /*只能对新建状态的线程调用start()方法，否则将触发IllegalThreadStateException异常
         */
//         t.start();

        for (int i = 0; i < 100; i++) {
            if (50 == i) {
                Thread.sleep(0);
                t.start();
            }
            log.info("当前的i的值为： {}", i);
        }
    }

    // 运行和死亡状态
    public static void runningAndBlocked(){
        /*  如果处于就绪的状态的线程获得了CPU，开始执行run()方法的线程执行体，则该线程处于运行状态。
         如果计算机只有一个CPU，那么在任何时刻只有一个线程处于运行状态。当然在一个多核处理器的机器上，
         将会有多个线程并行，当线程数大于处理数时，依然会存在多个线程在同一CPU上轮换的现象。
            当一个线程开始运行后，它不可能一直处于运行状态，线程的运行过程中需要被中断，目的是使其它线程获得
         执行的机会，线程调度的细节取决于底层平台所采用的策略；当该段时间用完后，系统就会剥夺该线程所占用的资源，
         让其他线程获得执行的机会。在选择下一个线程，系统会考虑线程的优先级。
            几乎所有的操作系统都采用抢占式调度策略，但是一些小型设备可能采用协作式调度策略，在这样的系统中
         只有当一个线程调用了他的sleep()和yield()方法后才会放弃所占用的资源->也就是必须由线程主动放弃所占用的资源
            当发生如下情况时，线程将会进入阻塞状态
            1.线程调用sleep()方法主动放弃所占用的处理器资源
            2.线程调用了一个阻塞式IO方法，在该方法返回之前，该线程被阻塞
            3.线程试图获取一个同步监视器，但该同步监视器正被其他线程所持有
            4.线程在等待某个通知（notify）
            5.程序调用了线程的suspend()方法将该线程挂起。但该方法容易导致死锁，应尽力避免使用
            当前正在执行的线程被阻塞后，其他的线程就可以获得执行的机会。被阻塞的线程会在合适的时候重新进入就绪状态，
         注意是就绪状态而不是运行状态，也就是说，被阻塞的线程解除后，必须重新等待线程调度器再次调度它。
            针对上面几种情况，当发生如下特定的情况时可以解除上面的阻塞，让该线程重新进入就绪状态。
            1.调用sleep()方法的线程经过了指定的时间
            2.线程调用的阻塞式IO方法已经返回。
            3.线程成功地获得了试图取得的同步监视器。
            4.线程正在等待某个
         */

        Thread t = new Thread(() -> {
            log.info("我是Runnable的lambda简化后的任务,即将休眠5秒");
            try {
                Thread.sleep(5 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("休眠5秒结束");
        });


    }



}



