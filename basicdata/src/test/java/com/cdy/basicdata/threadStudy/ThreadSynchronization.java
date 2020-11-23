package com.cdy.basicdata.threadStudy;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

/**
 * @Description: 线程的同步（重点与难点）
 * @Author: chendeyin
 * @Date: 2020/11/20 13:30
 */
@Slf4j
public class ThreadSynchronization {

    public static void main(String[] args) throws Exception {
//        for (int i = 0; i < 100; i++) {
//            ThreadSynchronization.withdrawMoney1();
//        }
        for (int i = 0; i < 100; i++) {
            withdrawMoney2();
        }


    }

    public static void withdrawMoney1() throws Exception {
        Account account = new Account("8a9dc28c-0233-4bbf-a0e1-da813056acf8", new BigDecimal(10000));

        DrawRunnable1 drawRunnable11 = new DrawRunnable1(account, new BigDecimal(6000));
        DrawRunnable1 drawRunnable21 = new DrawRunnable1(account, new BigDecimal(5000));

        Thread t1 = new Thread(drawRunnable11);
        Thread t2 = new Thread(drawRunnable21);


        t1.start();
        t2.start();

        t1.join();
        t2.join();
        log.info("===余额为: {}", account.getBalance().toString());
    }

    public static void withdrawMoney2() throws Exception {
        Account account = new Account("8a9dc28c-0233-4bbf-a0e1-da813056acf8", new BigDecimal(10000));

        DrawRunnable2 drawRunnable21 = new DrawRunnable2(account, new BigDecimal(6000));
        DrawRunnable2 drawRunnable22 = new DrawRunnable2(account, new BigDecimal(5000));

        Thread t1 = new Thread(drawRunnable21);
        Thread t2 = new Thread(drawRunnable22);

        t1.start();
        t2.start();

        t1.join();
        t2.join();
        log.info("===余额为: {}", account.getBalance().toString());
    }


}

/**
 * 账户类
 */
@Data
@Slf4j
class Account {
    private String accountNo;
    private BigDecimal balance;

    public Account() {
    }

    public Account(String accountNo, BigDecimal balance) {
        this.accountNo = accountNo;
        this.balance = balance;
    }

    /**
     * 提供一个线程安全的draw()方法来完成取钱操作, 这种方式可以更好地保证Account对象的完整性和一致性
     * 与同步代码块对应的是同步方法，同步方法就是使用synchronized关键字来修饰某个方法，该方法称为同步方法
     * 对于synchronized修饰的实例方法（非static方法）而言，无须显式地指定同步监视器，同步方法的同步监视器为this,也就是调用该方法的对象
     *
     * 通过使用同步方法可以非常方便地实现线程安全的类，线程安全的类具有如下特征:
     * 1、该类的对象可以被多个线程安全地访问
     * 2、每个线程调用该对象的任意方法之后都将得到正确的结果
     * 3、每个线程调用该对象的任意方法之后，该对象状态依然保持合理的状态
     * 可变的线程安全是以降低程序的运行效率为代价的，为了减少线程安全所带来的负面影响
     * 程序可以采取如下策略：
     * 1、不要对线程安全类的所有方法都进行同步，只对那些会改变竞争资源（共享资源）的方法进行同步。
     * 2、如果可变类有两种运行环境：单线程与多线程环境，则应该为可变类提供两种版本，
     *    在单线程环境中使用线程不安全版本以保证性能，在多线程环境中使用线程安全版本
     *
     * 不可变类总是线程安全的，因为他的对象状态不可改变；
     * 但是可变对象需要额外的方法来保证其线程安全
     * 只要把修改balance的方法变成同步方法即可
     */

    /**
     *  任何线程进入同步代码块、同步方法之前，必须先获得对同步监视器的锁定
     *  程序无法显式释放对同步监视器的锁定，线程会在如下几种情况下释放对同步监视器的锁定
     *  1、当前线程的同步方法、同步代码块执行结束，当前线程即释放同步监视器
     *  2、当前线程在同步代码块、同步方法中遇到break，return终止了该代码块、该方法继续执行，当前线程将会释放同步监视器
     *  3、当前线程在同步代码块、同步方法中出现了未处理的Error或者Exception，导致了该代码块，该方法异常结束时，当前线程将会释放同步监视器
     *  4（难点）、
     */

    /**
     * 该方法的同步监视器是this，因此对于同一个Account对象而言，
     * 任意时刻只能有一个线程获得对Account对象的锁定
     * 然后进入draw()方法执行取钱操作->这样也可以保证多个线程并发取钱的线程安全
     *
     */
    public  synchronized  void draw(BigDecimal drawAmount) {

        if (this.balance.compareTo(drawAmount) == 1) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.balance = this.balance.subtract(drawAmount);
        }
    }
}

/**
 * 取钱线程类1
 */
@Data
@Slf4j
class DrawRunnable1 implements Runnable {

    private Account account;

    private BigDecimal drawAmount;

    public DrawRunnable1() {
    }

    public DrawRunnable1(Account account, BigDecimal drawAmount) {
        this.account = account;
        this.drawAmount = drawAmount;
    }

    /**
     * 多次运行出现了如下结果
     * 14:33:15.314 [main] INFO com.cdy.basicdata.threadStudy.ThreadSynchronization - ===余额为: -1000
     * <p>
     * 为了解决这个问题，Java的多线程支持引入了同步监视器来解决这个问题，
     * 使用同步监视器的通用方法就是同步代码块。同步代码快块的语法风格为:
     * synchronized (object) {
     * // doing
     * }
     * 上面语法格式中synchronized后括号里的object就是同步监视器，
     * 其含义为：线程开始执行同步代码块之前，必须先获得对同步监视器的锁定
     * <p>
     * 注意任何时刻只能有一个线程可以获得对同步监视器的锁定，当同步代码块执行完成后，该线程会释放对该同步监视器的锁定.
     */
    @Override
    public void run() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /**
         * 未同步时的代码
         */
//        if (this.account.getBalance().compareTo(this.drawAmount) == 1) {
//            this.account.setBalance(this.account.getBalance().subtract(this.drawAmount));
//        }


        /**
         * 同步后的代码
         *
         * 该同步代码块的同步监视器是account对象，
         * 任何线程在修改指定资源，首先对该资源加锁，
         * 在加锁期间其他线程无法修改该资源，当线程修改完成后，该线程释放对该资源的锁定
         *
         * 通过这个方式就可以保证并发线程在任何时刻只有一个线程可以进入修改共享资源的代码区（临界区），
         * 所以同一时刻最多只有一个线程处于临界区，从而保证代码的安全性
         */
        synchronized (this.account) {
            if (this.account.getBalance().compareTo(this.drawAmount) == 1) {
                this.account.setBalance(this.account.getBalance().subtract(this.drawAmount));
            }
        }

    }
}


/**
 * 取钱线程类2
 */
@Data
@Slf4j
class DrawRunnable2 implements Runnable {

    private Account account;

    private BigDecimal drawAmount;
    public DrawRunnable2() {
    }
    public DrawRunnable2(Account account, BigDecimal drawAmount) {
        this.account = account;
        this.drawAmount = drawAmount;
    }

    /**
     * 直接调用account的draw()方法
     */
    @Override
    public void run() {
        account.draw(drawAmount);
    }
}









