package com.cdy.basicdata.designPatterns.singletonPattern;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 枚举单例模式
 * JDK版本：1.5起
 * 是否lazy初始化：否
 * 是否多线程安全：是
 * 实现难度：易
 * 描述：这种实现方式还没有广泛使用，但这是实现单例模式的最佳方法。他更简洁，自动支持序列化机制，绝对防止多次实例化
 *
 * 他不仅避免多线程同步问题，而且还自动支持序列化机制，防止反序列化重新创建新的对象，绝对防止多次实例化。
 * 不过，由于JDK1.5之后才加入enum特性，用这种方式不免让人感觉生疏，在实际工作中也很少使用。
 *
 * 不能通过reflection attack来调用私有构造方法
 * @Author: chendeyin
 * @Date: 2020/12/9 10:46
 */
@Slf4j
public enum EnumerationSingleton {
    INSTANCE;
    public void  show(){
      log.info("枚举式单例模式");
    }

    public static void main(String[] args) {
        EnumerationSingleton.INSTANCE.show();
    }
}
