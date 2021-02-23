package com.cdy.basicdata.structurePattern.bridgePattern;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 桥接模式
 * @Author: chendeyin
 * @Date: 2021/2/22 14:08
 */
@Slf4j
public class BridgePatternApp {
    public static void main(String[] args) {

        log.debug("测试生成白色正方形");
        Color colorWhite = new White();
        Shape square = new Square();
        square.setColor(colorWhite);
        square.draw();

        log.debug("测试生成黑色圆形");
        Color colorBlack = new Black();
        Shape circle = new Circle();
        circle.setColor(colorBlack);
        circle.draw();


    }
}

// 颜色接口
interface Color {
    // 给指定的形状着色
    void bepaint(String shape);
}

// 白色
@Slf4j
class White implements Color {
    @Override
    public void bepaint(String shape) {
        log.debug("白色的" + shape);
    }
}

// 灰色
@Slf4j
class Gray implements Color {
    @Override
    public void bepaint(String shape) {
        log.debug("灰色的" + shape);
    }
}

// 黑色
@Slf4j
class Black implements Color {
    @Override
    public void bepaint(String shape) {
        log.debug("黑色的" + shape);
    }
}

// 形状抽象类
abstract class Shape {
    // 颜色属性
    private  Color color;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    // 绘制形状
    public abstract void draw();
}

// 圆形
class Circle extends Shape {
    @Override
    public void draw() {
        this.getColor().bepaint("圆形");
    }
}

// 矩形
class Rectangle extends Shape {
    @Override
    public void draw() {
        this.getColor().bepaint("矩形");
    }
}

// 正方形
class Square extends Shape {
    @Override
    public void draw() {
        this.getColor().bepaint("正方形");
    }
}