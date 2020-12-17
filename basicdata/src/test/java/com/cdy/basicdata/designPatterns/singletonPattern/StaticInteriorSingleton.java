package com.cdy.basicdata.designPatterns.singletonPattern;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 登记式/静态内部类单例模式
 * 是否lazy初始化：是
 * 是否多线程安全：是
 * 实现难度：一般
 * 描述：这种方式能达到双检锁一样的功效，但是实现更加简单。
 * 对静态域使用延迟初始化，应使用这种方式而不是双检锁方式。
 * 这种方式只适用于静态域的使用情况，双检锁方式可在实例化需要延迟初始化时使用
 * <p>
 * 这种方式同样了类加载机制来保证初始化instance时只有一个线程，他与单例模式（饿汉式）的不同之处在于：
 * 饿汉式只要Singleton类被装载了，那么instance就会被实例化（没有达到lazy初始化效果）
 * 而静态内部类方式的StaticInteriorSingleton类被装载了，instance不一定被初始化。
 * 因为SingletonHolder类没有被主动使用，只有显示调用getInstance方法时，才会显示装载SingletonHolder类
 * 从而实例化instance。
 *
 * 如果实例化instance很消耗资源，因此想让其延迟加载，另一方面又不希望在StaticInteriorSingleton类
 * 加载时就实例化，因为不能确保StaticInteriorSingleton类还可能在其他的地方被主动使用从而被加载，
 * 那么这个时候实例化instance显然不合适。这种时候，静态内部类单例模式比饿汉式就显得很合理。
 * @Author: chendeyin
 * @Date: 2020/12/8 15:29
 */
@Slf4j
public class StaticInteriorSingleton {
    // 新建一个静态内部类
    private static class SingletonHolder {
        // 声明一个常量对象
        private static final StaticInteriorSingleton INSTANCE = new StaticInteriorSingleton();
    } // end 静态内部类

    // 构造函数私有
    private StaticInteriorSingleton(){

    }

    public static final StaticInteriorSingleton getInstance(){
        return SingletonHolder.INSTANCE;
    }

    public void show(){
        log.info("登记式/静态内部类单例模式");
    }

    public static void main(String[] args) {
        StaticInteriorSingleton instance = StaticInteriorSingleton.getInstance();
        instance.show();
    }

}



