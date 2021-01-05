package com.cdy.basicdata.structurePattern.adapterPattern;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 概述：
 * 与电源适配器相似，在适配者模式中引入一个被称为适配器(Adapter)的包装类,
 * 而它所包装的对象称为适配者(Adaptee)，即被适配的类。适配器的实现就是把
 * 客户类的请求转化为对适配者的相应接口的调用。也就是说：当客户调用适配器
 * 的方法时，在适配器类的内部将调用适配者类的方法，而这个过程对客户类是透
 * 明的，客户类并不直接访问适配者类。因此，适配器让那些由于接口不兼容而不
 * 能交互的类可以一起工作。
 * <p>
 * 适配器模式可以将一个类的接口和另一个类的接口匹配起来，而无需修改原来的
 * 适配者接口和抽象目标类的接口
 * <p>
 * 定义：
 * 适配器模式（Adapter Pattern）：将一个接口转换成客户希望的另一个接口，
 * 使接口不兼容的那些类可以一起工作，其别名为包装器（Wrapper）。适配器模
 * 式既可以作为类结构型模式，也可以作为对象结构型模式。
 *
 * 在适配器模式中，我们通过增加一个新的适配器类来解决接口不兼容的问题，使得
 * 原本没有任何关系的类可以协同工作。根据适配器类与与适配者类的关系不同，适
 * 配器模式可分为对象适配器和类适配器两种，
 *
 * 在对象适配器模式中，适配器与适配者之间的关系是关联关系；在类适配器模式中，适配器与适配者之间是继承（或实现）关系
 *
 * 在实际开发中，对像适配器的使用频率更高。
 * 对象适配器模式结构图中包含如下几个角色
 * 1、Target(目标抽象类)：目标抽象类定义客户所需接口，可以是一个抽象类或接口，也可以是具体类
 * 2、Adaptee(适配者类)：适配者即被适配的角色。它定义了一个已经存在的接口，这个接口需要适配
 *   适配者类一般是一个具体类，包含了客户希望使用的业务方法，在有些情况下，可能没有适配者类的
 *   源代码
 * 3、适配器可以调用另一个接口，作为转换器，对Adaptee和Target进行适配，适配器类是适配器模式
 *   的核心，在对象适配器中，它通过继承Target并关联一个Adaptee对象使两者产生联系。
 *
 * @Author: chendeyin
 * @Date: 2021/1/5 10:30
 */
@Slf4j
public class AdapterApp2 {
    public static void main(String[] args) {
        List<Integer> integerList = new ArrayList<>();
        integerList.add(null);

        log.info("列表的大小为: {}", integerList.size());
    }
}

interface ScoreOperation{
    public int[] sort(int[] array);
}
