package com.cdy.basicdata.designPatterns.adapterPattern;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 适配器模式demo
 * @Author: chendeyin
 * @Date: 2021/2/22 10:45
 */
@Slf4j
public class AdapterApp {

    public static void main(String[] args) {
        // 使用类适配器类
        log.debug("测试类适配器");
        Target target = new ClassAdapter();
        target.request();

        log.debug("测试对象适配者");
        Adaptee adaptee = new Adaptee();
        Target objectTarget = new ObjectAdapter(adaptee);
        objectTarget.request();
    }
}

// 目标接口
interface Target {
    void request();
}

// 适配者
@Slf4j
class Adaptee {
    public void specificRequest() {
        log.debug("适配者的业务代码被调用");
    }
}

// 类适配器
@Slf4j
class ClassAdapter extends Adaptee implements Target {
    @Override
    public void request() {
        specificRequest();
    }
}

// 对象适配器
@Slf4j
class ObjectAdapter implements Target {
    // 注入
    private Adaptee adaptee;

    public ObjectAdapter() {
    }
    public ObjectAdapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    public Adaptee getAdaptee() {
        return adaptee;
    }

    public void setAdaptee(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void request() {
        adaptee.specificRequest();
    }
}













