package com.cdy.basicdata.structurePattern.adapterPattern;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 适配器模式
 * 在现实生活中，经常出现两个对象因接口不兼容而不能在一起工作的实例，这时需要第三者进行适配。
 * 例如，讲中文的人同讲英文的人对话时需要一个翻译，用直流电的笔记本电脑接交流电源时需要一个电源适配器，
 * 用计算机访问照相机的 SD 内存卡时需要一个读卡器等。
 * <p>
 * 在软件设计中也可能出现：需要开发的具有某种业务功能的组件在现有的组件库中已经存在，但它们与当前系统的接口规范不兼容，
 * 如果重新开发这些组件成本又很高，这时用适配器模式能很好地解决这些问题。
 * <p>
 * 定义: 将一个类的接口转成客户希望的的另外一个接口，使得原本由于接口不兼容而不能一起工作的那些类能一起工作。
 * 适配器模式分为类结构型模式和对象结构型模式两种，前者之间的耦合度比后者高，且要求程序员了解现有的组件库
 * 中的相关组件的内部结构，所以应用的较少一些。
 * 优点:
 * 1、客户端通过适配器可以透明地调用目标接口
 * 2、复用了现存的类，不需要修改原有代码而重用现有的适配者类
 * 3、将目标类和适配者类解耦，解决了目标类和适配者类接口不一致的问题
 * 4、在很多业务场景中符合开闭原则
 * 缺点:
 * 1、适配器编写过程中需要结合业务场景全面考虑，可能会增加系统的复杂性
 * 2、增加阅读代码难度，降低代码的可读性，过多使用使用适配器会使系统代码变得凌乱
 * <p>
 * 结构与实现:
 * 类适配器
 * <p>
 * 对象适配器:
 * 对象适配器模式可采用将现有组件库已经实现的组件引入适配器类中，该类同时实现当前系统的业务接口。
 * 模式的结构:
 * 适配器模式（Adapter）包含以下主要角色
 * 1、目标（Target）接口:当前系统业务所期待的接口，它可以使抽象类或接口。
 * 2、适配者（Adaptee）类: 它是被访问和适配的现存组件库中的组件接口。
 * 3、适配器（Adapter）类: 他是一个转换器，通过继承或引用适配者的对象，把适配器接口转换成目标接口，让客户按目标接口的格式访问适配者。
 * @Author: chendeyin
 * @Date: 2021/1/4 15:30
 */
@Slf4j
public class AdapterApp {

//    public static void main(String[] args) {
//        // 类适配器模式测试
////        classAdapterTest();
//
//
//        // 对象适配器模式测试
//        objectAdapterTest();
//    }
//
//    public static void classAdapterTest(){
//        log.info("类适配器模式测试:");
//        Target target = new ClassAdapter();
//        target.request();
//    }
//
//    public static void objectAdapterTest(){
//        log.info("对象适配器测试");
//        Adaptee adaptee = new Adaptee();
//        ObjectAdapter objectAdapter = new ObjectAdapter(adaptee);
//        objectAdapter.request();
//
//    }
}

/**
 * 目标接口
 */
interface Target {
    public void request();
}

/**
 * 适配者接口
 */
@Slf4j
class Adaptee {
    public void specificRequest() {
        log.info("此为适配者的specificRequest()方法, 被调用!");
    }
}

///**
// * 类适配器类
// */
//class ClassAdapter extends Adaptee implements Target {
//    @Override
//    public void request() {
//        specificRequest();
//    }
//}

/**
 * 对象适配器类
 * 在对象适配器中客户端需要调用request()方法，而适配器者类Adaptee没有该方法，
 * 但是它提供的specificRequest()方法却是客户端所需要的。为了使客户端能够使用
 * 适配者类，需要提供一个包装类Adapter，即适配器类。这个包装类包装了一个适配者
 * 的实例，从而将客户端与适配者衔接起来，在适配器的request()方法中调用适配者的
 * specificRequest()方法。因为适配器类与适配者类是关联（也可以称之为委派关系），
 * 所以这种适配器模式称为对象适配器模式
 *
 */
class ObjectAdapter implements Target {

    private Adaptee adaptee; // 维持一个对适配者对象的引用

    public ObjectAdapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void request() {
        adaptee.specificRequest();
    }
}

