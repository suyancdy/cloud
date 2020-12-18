package com.cdy.basicdata.designPatterns.simpleFactoryPattern;

import lombok.extern.slf4j.Slf4j;

/**
 * 简单工厂模式
 *
 * 在日常开发中，凡是需要生成复杂对象的地方，都可以考虑使用工厂模式来代替
 * 注意：
 * 上述复杂对象指的是类的构造函数参数过多等对类的构造有影响的情况
 * 因为类的构造过于复杂，如果直接在其他业务类使用，则两者的耦合过重
 * 后续业务更改，就需要在任何引用该类的源代码内进行更改，非常浪费时间
 *
 * 定义：定义一个创建产品对象的工厂接口，将产品对象的实际创建工作推迟到具体子工厂类当中
 *      这满足创建型模式中所要求的“创建与使用相分离”的特点。
 *
 * 按照实际业务场景划分，工厂模式有3种不同的实现方式，分别是简单工厂模式、工厂方法模式、抽象工厂模式
 *
 * 我们把被创建的对象称为“产品”，把创建产品的对象称为“工厂”。如果要创建的产品不多，只要一个工厂类就可以完成，这种模式叫“简单工厂模式”
 *
 * 在简单工厂模式中创建实例的方法为静态方法，因此简单工厂模式又称为静态工厂方法模式
 *
 * 简单来说，简单工厂模式有一个具体的工厂类，可以生成多个不同的产品，属于创建型设计模式。简单工厂模式不是GoF 23种设计模式之列
 *
 * 简单工厂模式每增加一个产品就要增加一个具体产品类和一个对应的工厂类，这增加了系统的复杂度，违背了“开闭原则”。
 *
 * “工厂方法模式”是对简单工厂模式的进一步抽象化，其好处是可以使系统在不修改原来代码的情况下引进新的产品，即满足开闭原则
 *
 * 优点：
 *    1、工厂类包含必要的逻辑判断，可以决定在什么时候创建哪一个产品的实例。
 *       客户端可以免除直接创建产品对象的职责，很方便地创建出相应的产品。工厂和产品的职责区分明确。
 *    2、客户端无需知道所创建具体产品的类名，只需要知道参数即可。
 *    3、也可以引入配置文件，在不修改客户端代码的情况下更换和添加新的具体产品类。
 * 缺点：
 *    1、简单工厂模式的工厂类单一，负责所有产品的创建，职责过重，一旦异常，整个系统将受影响。且工厂类代码会非常臃肿，违背高内聚原则。
 *    2、使用简单工厂模式会增加系统中类的个数（引入新的工厂类），增加系统的复杂度和理解难度。
 *    3、系统扩展困难，一旦增加新产品不得不修改工厂逻辑，在产品类型较多时，可能造成逻辑过于复杂。
 *    4、简单工厂模式使用了static方法，造成工厂角色无法形成基于继承的等级结构。
 *
 * 应用场景：
 *    对于产品种类相对较少的情况，考虑使用简单工厂模式。
 *    使用简单工厂模式的客户端只需要传入工厂类的参数，不需要关心如何创建对象的逻辑，可以很方便地创建所需产品
 *
 * 模式的结构与实现：
 *    简单工厂模式的主要角色如下：
 *    1、简单工厂（SimpleFactory）：是简单工厂的核心，负责实现创建所有实例的内部逻辑。工厂类的创建产品类的方法可以被外界直接使用，创建所需的产品对象
 *    2、抽象产品（Product）：是简单工厂创建的所有对象的父类，负责描述所有实例共有的公共接口。
 *    3、具体产品（ConcreteProduct）：是简单工厂模式的创建目标。
 *
 * @Description:
 * @Author: chendeyin
 * @Date: 2020/12/17 16:48
 */
public class App {
    public static void main(String[] args) {
        ProductA productA = (ProductA) SimpleFactory.makeProduct(0);
        productA.show();
    }
}


class SimpleFactory {
    static final int PRODUCT_A = 0;
    static final int PRODUCT_B = 1;
    static final int PRODUCT_C = 2;

    public static Product makeProduct(int kind) {
        if (kind == PRODUCT_A) {
            return new ProductA();
        } else if (kind == PRODUCT_B) {
            return new ProductB();
        }
        return null;
    }
}

interface Product {
    void show();
}

@Slf4j
class ProductA implements Product {
    @Override
    public void show() {
        log.info("具体产品A信息...");
    }
}

@Slf4j
class ProductB implements Product {
    @Override
    public void show() {
        log.info("具体产品B信息...");
    }
}

