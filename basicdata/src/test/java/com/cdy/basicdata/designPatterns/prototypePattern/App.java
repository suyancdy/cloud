package com.cdy.basicdata.designPatterns.prototypePattern;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;

/**
 * @Description: 原型模式
 * 在有些系统中，存在大量相同或相似对象的创建问题，如果使用传统的构造函数来创建对象，会比较复杂且消耗时间与资源
 * 使用原型模式生成对象的效率比较高，就像孙悟空吹猴毛变成大量孙悟空一样简单
 * <p>
 * 定义：用一个已经创建的实例作为原型，通过复制该原型对象来创建一个和原型相同或者相似的新对象。在这里，
 * 原型实例指定了要创建的对象的种类。用这种方法创建对象非常高效，根本无需创建对象的细节
 * <p>
 * 优点：
 * 1、Java自带的原型模式基于内存二进制流的复制，在性能上比直接new一个对象更加优良
 * 2、可以使用深克隆方式保持对象的状态，使用原型模式将对象复制一份，并将其状态保存起来，简化了创建对象的过程
 * 以便在需要的时候使用（例如恢复到历史某一状态），可辅助实现撤销操作。
 * <p>
 * 缺点：
 * 1、需要为每一个类都配置一个clone方法
 * 2、clone方法位于类的内部，当对已有类进行改造的时候，需要修改代码，违背了开闭原则
 * 3、当实现深克隆时，需要编写较为复杂的代码，而且当对象之间存在多重嵌套引用时
 * 为了实现深克隆，每一层对象对应的类都必须支持神克隆，实现起来比较麻烦。因此深浅克隆需要运用得当
 * <p>
 * 原型模式（Prototype Pattern）是用于创建重复对象，同时又能保持性能。 这种设计模式属于创建型模式，它提供了创建对象的最佳方式。
 * 这种模式是实现了一个原型接口，该接口用于创建当前接口的克隆。当直接创建对象的代价比较大时，则采用这种模式。例如：
 * 一个对象需要在一个高代价的数据库操作之后被创建。可以缓存该对象，在下一个请求时返回它的克隆，在需要的时候更新数据库，以此来减少数据库的调用。
 * <p>
 * <p>
 * 意图：用原型实例指定创建对象的种类，并且通过拷贝这些原型创建新的对象。
 * 主要解决：在运行期建立和删除原型
 * 何时使用：
 * 1、对象之间相同或相似，即只是个别的几个属性不同的时候
 * 2、创建对象成本较大，列如初始化时间比较长，占用CPU太多，或者占用的网络资源太多等，需要优化资源
 * 3、创建一个对象需要频繁的数据准备或者访问权限等，需要提高性能或者提高安全性
 * 4、系统中大量使用该类对象，且各个调用者都需要给它的属性重新赋值
 *
 *
 * @Author: chendeyin
 * @Date: 2020/12/9 14:14
 */
public class App {

}


/**
 *
 */

/**
 * 具体的原型类
 */
@Slf4j
class RealizeType implements Cloneable {
    public RealizeType() {
        log.info("=== 具体原型创建成功！");
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        log.info("=== 具体原型复制成功！");
        return (RealizeType) super.clone();
    }

    // 测试
    public static void main(String[] args) throws CloneNotSupportedException {
        RealizeType realizeType1 = new RealizeType();

        RealizeType realizeType2 = (RealizeType) realizeType1.clone();
        log.info("realizeType1==realizeType2 ? {}", realizeType1 == realizeType2);

    }
}

@Slf4j
class SunWuKong extends JPanel implements Cloneable {

    public SunWuKong() {
        JLabel jLabel = new JLabel(new ImageIcon("F:\\yhsFile\\wallpaper\\89.jpg"));
        this.add(jLabel);
    }

    @Override
    protected Object clone() {
        SunWuKong sunWuKong = null;
        try {
            sunWuKong = (SunWuKong) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return sunWuKong;
    }

    public static void main(String[] args) {
        JFrame jFrame = new JFrame("原型模式测试");
        jFrame.setLayout(new GridLayout(1, 2));
        Container container = jFrame.getContentPane();
        SunWuKong sunWuKong1 = new SunWuKong();
        container.add(sunWuKong1);

        SunWuKong sunWuKong2 = (SunWuKong) sunWuKong1.clone();
        container.add(sunWuKong2);
        jFrame.pack();
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

@Slf4j
@Data
class Citation implements Cloneable {
    private String name;
    private String info;
    private String college;

    public Citation() {
        log.info("奖状创建成功！");
    }

    public Citation(String name, String info, String college) {
        this.name = name;
        this.info = info;
        this.college = college;
        log.info("奖状创建成功！");
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return (Citation) super.clone();
    }

    @Override
    public String toString() {
        return "Citation{" +
                "name='" + name + '\'' +
                ", info='" + info + '\'' +
                ", college='" + college + '\'' +
                '}';
    }


    public static void main(String[] args)  throws  CloneNotSupportedException{
        Citation citation = new Citation("张三", "表现不错，三好学生", "XX小学");
        Citation citation1 = (Citation) citation.clone();
        citation1.setName("李四");
        log.info(citation.toString());
        log.info(citation1.toString());
    }
}
