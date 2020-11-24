package com.cdy.basicdata.designPatterns.singletonPattern;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.DigestUtils;
import sun.misc.BASE64Encoder;

import java.security.MessageDigest;

/**
 * @Description: 主类
 * @Author: chendeyin
 * @Date: 2020/11/23 14:35
 */
@Slf4j
public class App {

    public static void main(String[] args)  throws Exception{
        // 下面的声明语句错误
        // SingleObject singleObject = new SingleObject();
        SingleObject singleObject = SingleObject.getInstance();

        singleObject.showMessage();

//        Singleton1 singleton1 = new Singleton1();

        Singleton1 singleton1 = Singleton1.getInstance();
        singleton1.showMessage();

        Singleton2 singleton2 = Singleton2.getInstance();
        singleton2.showMessage();


    }
}
