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

    public static void main(String[] args) throws Exception{

       // ThreadLifecycle.newAndRunnable();
//        ThreadLifecycle.runningAndBlocked();
        ThreadLifecycle.controlThread();
        log.info("=== 主线程结束");
    }


    /**
     * 新建和就绪状态
     * @throws InterruptedException
     */
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
        /**  当线程对象调用了start()方法之后，该线程处于就绪状态，
        Java虚拟机会为其创建方法调用栈和程序计数器，处于这个状态中的线程并没有开始运行，
        只是表示该线程可以运行了。至于线程何时开始运行，取决于JVM里线程调度器的调度。
            调用线程的对象的start()方法之后，该线程立即进入就绪状态->就绪状态相当于“等待执行”，
        线程并未真正进入运行状态
            如果希望调用子线程立即开始执行，程序可以使用Thread.sleep(0)或Thread.sleep(1)来让当前的线程（主线程）让出或休眠1毫秒。
         */
//        t.start();

        /**只能对新建状态的线程调用start()方法，否则将触发IllegalThreadStateException异常
         */
//         t.start();

        for (int i = 0; i < 100; i++) {
            if (50 == i) {
                Thread.sleep(1000*1);
                t.start();
            }
            log.info("当前的i的值为： {}", i);
        }
    }

    /**
     * 运行和阻塞状态
     * @throws Exception
     */
    public static void runningAndBlocked() throws Exception{
        /** 如果处于就绪的状态的线程获得了CPU，开始执行run()方法的线程执行体，则该线程处于运行状态。
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
            4.线程正在等待某个通知时，其他线程发出了一个通知
            5.处于挂起状态的线程被调用了resume()恢复方法
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

        /**
         * 线程从阻塞状态只能进入就绪状态，无法直接进入运行状态。
         * 而就绪和运行状态之间的转换通常不受程序控制，而是由系统线程调度所确定，
         * 当处于就绪状态的线程获得处理器资源时，该线程进入运行状态；
         * 当处于运行状态的线程使其处理器资源时，该线程进入就绪状态。
         * 但由一个方法例外，调用yield()方法可以让运行状态的线程进入就绪状态
         */

        /** 线程死亡*/
        /**
         * 线程会以如下三种方式结束，结束后就进入死亡状态
         * 1、run()或call()方法执行结束，线程正常结束
         * 2、线程抛出一个未捕获的Exception或Error
         * 3、直接调用该线程的stop()来结束该线程 ->该方法容易导致出现死锁，不建议使用
         */

        t.start();

        log.info("线程t的当前状态是否死亡: {}", !t.isAlive());
        // 让主线程睡眠10秒，此时10秒后线程t已经死亡
        Thread.sleep(10*1000);
        log.info("线程t的当前状态是否死亡: {}", !t.isAlive());
        /**
         * 当主线程结束时，其他线程不受任何影响，并不会随之结束。
         * 一旦子线程启动起来，它就拥有和主线程相同的地位，它不受主线程的影响
         */
        /**
         * 不要试图对一个已经死亡的线程调用start()方法使其重新启动，死亡就是死亡，该线程将不可再次作为线程执行
         * 对新建状态的线程两次调用start()方法也是错误的
         */
    }

    /**
     * 控制线程
     */
    public static void controlThread() throws InterruptedException {

        Thread thread = new Thread(() ->{
            log.info("我是Runnable的lambda简化后的任务");
            // 让线程睡眠5秒
            try {
//                for (int i = 0; i < 100; i++) {
//                    log.info("当前线程: {}, i的值为: {}", Thread.currentThread().getName(), i);
//                }
                Thread.sleep(1000*5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("我是Runnable的lambda简化后的任务结束");
        });


        /**
         * 线程的插队
         */
        for (int i = 0; i < 100; i++) {
            if (i == 20){
                thread.start();
                /**
                 * main线程调用了thread线程的join方法
                 * main线程必须等待thread线程执行结束才向下执行
                 *
                 * join()方法的主要作用就是同步，它可以使得线程之间的并行执行变成串行执行。
                 * 在A线程调用了B线程的join()方法时，表示只有当B线程执行完毕时，A线程才能继续执行。
                 *
                 * join()方法中如果传入参数，则表示这样的含义：
                 * 如果A线程中调用了B线程的join(10)方法，则表示A线程会等待B线程执行10毫秒，10毫秒后，A，B线程并行执行。
                 * jdk规定，join(0)表示A线程无限等待至B线程执行结束，即join()等价于join(0)
                 *
                 * join()方法必须在线程start方法调用之后才有意义
                 */
                thread.join();
            }
            log.info("当前线程: {}, i的值为: {}", Thread.currentThread().getName(), i);
        }

        /**
         * 守护线程
         */
        /**
         * 守护线程在后台运行，它的任务是为其他线程提供服务，这种线程被称为Daemon thread, JVM的垃圾回收就是典型的守护线程
         * 守护线程有个特征：如果所有的前台线程都死亡，守护线程会自动死亡
         *
         * 调用Thread的setDaemon(true)可以可将指定线程设置为守护线程
         * 调用线程的isDaemon()用来判断是否是守护线程
         *
         * 前台线程死亡时，JVM会通知后台线程死亡，但从它接收到指令做出响应，需要一定的时间。
         * 而且要将某个线程设置为后台线程，必须在该线程启动之前设置，即setDaemon(true)必须在start()方法之前调用
         */


        /**
         * 线程睡眠： sleep
         */

        /**
         * 线程让步： yield
         */
        /**
         * 可以让当前正在执行的线程暂停，但它不会阻塞该线程，他只是将该线程转入就绪状态
         * yield()只是让线程暂停一下，让系统的线程调度器重新调度一次
         */

        /**
         * 改变线程的优先级
         */

    }






}



