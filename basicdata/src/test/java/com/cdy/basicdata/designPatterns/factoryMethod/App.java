package com.cdy.basicdata.designPatterns.factoryMethod;

import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/**
 * 工厂方法模式
 *
 * 在现实生活中社会分工越来越细，越来越专业化。
 * 各种产品有专门的工厂生产，彻底告别了自给自足的小农经济时代，
 * 这大大缩短了产品的生产周期，提高了生产效率。
 * 同样，在软件开发中能否做到软件对象的生产和使用相分离呢？
 * 能否在满足“开闭原则”的前提下，客户随意增删或改变对软件相关对象的使用呢？
 * 这就是本节要讨论的问题
 *
 * 简单工厂模式违背了开闭原则，而”工厂方法模式“是对简单工厂模式的进一步抽象化，
 * 其好处是可以使系统在不修改原来代码的情况下引进新的产品，即满足开闭原则。
 *
 * 优点：
 *   1、用户只需知道具体工厂的名称就可得到所要的产品，无须知道产品的具体创建过程。
 *   2、灵活性增强，对于新产品的创建，只需多写一个相应的工厂类
 *   3、典型的解耦框架。高层模块只需要知道产品的抽象类，无须关心其他实现类，满足迪米特法则、依赖倒置原则和里氏替换原则
 * 缺点：
 *   1、类的个数容易过多，增加难度
 *   2、增加了系统的抽象性和理解难度
 *   3、抽象产品只能生成一种产品，此弊端可使用抽象工厂模式解决。
 * 应用场景：
 *   1、客户知道创建产品的工厂名，而不知道具体的产品名。如TCL电视工厂，海信电视工厂
 *   2、创建对象的任务是由多个具体子工厂中的某一个完成，而抽象工厂只提供创建产品的接口
 *   3、客户不关系创建产品的细节，只关心产品的品牌
 *
 *
 *
 * @Description:
 * @Author: chendeyin
 * @Date: 2020/12/18 14:04
 */
@Slf4j
public class App {
    public static void main(String[] args) throws Exception{
        AbstractFactory abstractFactory = (AbstractFactory) ReadXML1.get();
        Product a = abstractFactory.newProduct();
        a.show();
    }


}

// 抽象产品：提供产品的接口
interface Product{
   public void show();
}

// 具体产品A：实现抽象产品中的抽象方法
@Slf4j
class ConcreteProductA implements Product{
    @Override
    public void show() {
        log.info("具体产品A。。。");
    }
}


// 具体产品B：实现抽象产品中的抽象方法
@Slf4j
class ConcreteProductB implements Product{
    @Override
    public void show() {
        log.info("具体产品B。。。");
    }
}

// 抽象工厂：提供了该厂产品的生成方法
interface AbstractFactory{
    Product newProduct();
}

// 具体工厂A：实现了该厂产品的生成方法
@Slf4j
class ConcreteFactoryA implements AbstractFactory {
    @Override
    public Product newProduct() {
        log.info("具体工厂A生成--> 具体产品A。。。");
        return new ConcreteProductA();
    }
}
// 具体工厂B：实现了该厂产品的生成方法
@Slf4j
class ConcreteFactoryB implements AbstractFactory {
    @Override
    public Product newProduct() {
        log.info("具体工厂B生成--> 具体产品B。。。");
        return new ConcreteProductB();
    }
}

@Slf4j
class ReadXML1 {
    // 该方法用于
    public static  Object get() throws Exception{

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(new File("E:\\worksoft\\IDEACode\\cloud\\basicdata\\src\\test\\resources\\config1.xml"));
        NodeList nodeList = document.getElementsByTagName("className");
        Node classNode = nodeList.item(0).getFirstChild();

        String className =ReadXML1.class.getPackage().getName() + "."  + classNode.getNodeValue();

        log.info("类名为：{}", className);

        Class clazz = Class.forName(className);
        Object object = clazz.newInstance();
        return object;
    }
}







