package com.cdy.basicdata.designPatterns.abstractFactory;

import com.cdy.basicdata.system.entity.Dishes;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;

/**
 * 前面介绍的工厂方法模式中考虑的是一类产品的生产，如畜牧场只养动物、电视机厂只生产电视机、计算机软件学院只培养计算机软件专业的学生等
 *
 * 同种类称为同等级，也就是说：工厂方法模式只考虑生产同等级的产品，但是在现实生活中许多工厂是综合型的工厂，
 * 能生产多等级（种类） 的产品，如农场里既养动物又种植物，电器厂既生产电视机又生产洗衣机或空调，大学既有软件专业又有生物专业等
 *
 * 本节要介绍的抽象工厂模式将考虑多等级产品的生产，将同一个具体工厂所生产的位于不同等级的一组产品称为一个产品族
 *
 * 定义与特点：
 *     定义：是一种为访问类提供一个创建一组相关或相互依赖对象的接口，且
 *   1、
 * @Description:
 * @Author: chendeyin
 * @Date: 2020/12/18 16:50
 */
public class App {

    public static void main(String[] args) {
        Dishes dishes = new Dishes();
        dishes.setId(1);
        dishes.setDishesName("方式发");

    }

}
