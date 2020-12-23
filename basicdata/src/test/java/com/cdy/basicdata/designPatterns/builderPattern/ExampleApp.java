package com.cdy.basicdata.designPatterns.builderPattern;

import io.swagger.models.auth.In;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: chendeyin
 * @Date: 2020/12/21 17:56
 */
@Data
public class ExampleApp {

    public static void main(String[] args) {
        test();
    }

    public static void test() {
        MealBuilder mealBuilder = new MealBuilder();
        Meal meal = mealBuilder.prepareVegMeal();
        meal.showMessage();
    }

}

/**
 * 步骤1
 */

// 包装
interface Packing {
    // 包装
    public String pack();
}

// 食物条目和食物包装的接口
interface Item {
    // 名称
    public String name();

    // 包装
    public Packing packing();

    // 价钱
    public BigDecimal price();
}

/**
 * 步骤2
 */

// 实现Packing接口的实体类1
class Wrapper implements Packing {
    @Override
    public String pack() {
        return "Wrapper";
    }
}

class Bottle implements Packing {
    @Override
    public String pack() {
        return "Bottle";
    }
}

/**
 * 步骤3
 */
// 创建实现Item接口的抽象类，该类提供默认的功能
abstract class Burger implements Item {

    // 包装
    @Override
    public Packing packing() {
        return new Wrapper();
    }

    // 价格
    @Override
    public abstract BigDecimal price();

}

abstract class ColdDrink implements Item {

    @Override
    public Packing packing() {
        return new Bottle();
    }

    @Override
    public abstract BigDecimal price();
}

/**
 * 创建了拓展了Burger和ColdDrink的实体类
 */

// veg汉堡
class VegBurger extends Burger {
    @Override
    public String name() {
        return "Veg Burger";
    }

    @Override
    public BigDecimal price() {
        return new BigDecimal("25.0");
    }
}

// 鸡肉汉堡
class ChickenBurger extends Burger {
    @Override
    public String name() {
        return "Chicken Burger";
    }

    @Override
    public BigDecimal price() {
        return new BigDecimal("50.0");
    }
}

// 可口
class Coke extends ColdDrink {
    @Override
    public String name() {
        return "Coke";
    }

    @Override
    public BigDecimal price() {
        return new BigDecimal("35.0");
    }
}

// 百事
class Pepsi extends ColdDrink {
    @Override
    public String name() {
        return "Pepsi";
    }

    @Override
    public BigDecimal price() {
        return new BigDecimal("35.0");
    }
}

// 套餐
@Slf4j
class Meal {

    private List<Item> itemList = new ArrayList<>();


    public void addItem(Item item) {
        itemList.add(item);
    }

    public BigDecimal getCost() {

        BigDecimal cost = new BigDecimal("0.0");
        for (Item i : itemList) {
            cost = cost.add(i.price());
        }
        return cost;
    }

    public void showMessage(){
        log.info("总价钱为：{}, 清单如下：", getCost().toString());
        itemList.forEach(i ->{
            log.info("食物名称: {}, 包装为: {}, 单价钱为：{}", i.name(), i.packing().pack(), i.price().toString());
        });
    }
}

// 服务员负责创建套餐
class MealBuilder {

    // 准备蔬菜汉堡
    public Meal prepareVegMeal(){
        Meal meal = new Meal();
        meal.addItem(new VegBurger());
        meal.addItem(new Coke());
        return meal;
    }

    // 准备无蔬菜汉堡
    public Meal prepareNonVegMeal(){
        Meal meal = new Meal();
        meal.addItem( new ChickenBurger());
        meal.addItem(new Pepsi());
        return meal;
    }

}






