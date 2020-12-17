package com.cdy.basicdata.designPatterns.simpleFactoryPattern;

import lombok.extern.slf4j.Slf4j;

/**
 * 在日常开发中，凡是需要生成复杂对象的地方，都可以考虑使用工厂模式来代替
 * 注意：
 * 上述复杂对象指的是类的构造函数参数过多等对类的构造有影响的情况
 * 因为类的构造过于复杂，如果直接在其他业务类使用，则两者的耦合过重
 * 后续业务更改，就需要在任何引用该类的源代码内进行更改，非常浪费时间
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

