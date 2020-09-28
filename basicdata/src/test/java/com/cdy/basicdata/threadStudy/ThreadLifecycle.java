package com.cdy.basicdata.threadStudy;

/**
 * 线程的生命周期
 *    当线程被创建并启动后，他并不是一启动就进入了执行状态，也不是一直处于执行状态，
 * 在线程的生命周期中，他要经历新建（New）、就绪（Runnable）、运行（Running）、
 * 阻塞（Blocked）和死亡（Dead）5种状态。
 *    当线程启动以后，他不可能一直霸占着CPU独自运行，所以CPU需要在多条线程之间切换，
 * 于是线程状态也会多次在运行，阻塞之间切换
 * @Author: chendeyin
 * @Modified:
 */
public class ThreadLifecycle {
    /*
     当程序使用new关键字创建了一个线程之后，该线程就处于新建状态
     */



}
