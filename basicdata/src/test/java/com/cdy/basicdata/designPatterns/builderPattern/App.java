package com.cdy.basicdata.designPatterns.builderPattern;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 建造者模式
 * <p>
 * 在软件开发过程中有时需要创建一个复杂的对象，这个对象通常由多个子部件按照一定的步骤组和而成。
 * 各个部件可以灵活选择，但是创建的步骤大同小异，这类产品的创建无法用前面介绍的工厂模式描述，
 * 只有建造者模式可以很好地描述该类残品的创建
 * <p>
 * 定义：将一个复杂的对象的构造与它的表示分离，使相同的构建过程可以创建不同的表示，此为建造者模式
 * 它是将一个复杂的对象分解为多个简单的对象，然后一步步构建而成。他将变与不变相分离，
 * 即产品的组成部分是不变的，但是每一部分是可以灵活选择的。
 * <p>
 * 优点：
 * 1、封装性好，构建和表示相分离
 * 2、扩展性好，各个具体的建造者相互独立，有利于系统的解耦。
 * 3、客户端不必知道产品内部组成的细节，建造者可以对创建过程逐步细化，而不对其它模块产生任何影响，便于控制细节风险。
 * 缺点：
 * 1、产品的组成部分必须相同，这限制了其使用范围。
 * 2、如果产品的内部变化复杂，如果产品内部发生变化，则建造者也要同步修改，后期维护成本较大。
 * <p>
 * 建造者模式与工厂模式的关注的不同：建造者模式注重零部件的组装过程，而工厂模式更注重零部件的创建过程，但两者可以结合使用。
 * <p>
 * 结构与实现：
 * 建造者模式由产品、抽象建造者、具体建造者、指挥者等4个要素构成
 * 1、模式的结构：
 * 1、
 * @Author: chendeyin
 * @Date: 2020/12/20 14:41
 */
@Slf4j
public class App {
    public static void main(String[] args) {
        Builder builder = new Builder();
        // 豪华欧式
        DecorationPackageMenu decorationPackageMenu = (DecorationPackageMenu) builder.levelOne(132.52d);
        log.info("物料清单为：{}", decorationPackageMenu.getDetail());

        // 轻奢田园
        DecorationPackageMenu decorationPackageMenu1 = (DecorationPackageMenu)builder.levelTwo(98.25);
        log.info("物料清单为：{}",decorationPackageMenu1.getDetail());

        DecorationPackageMenu decorationPackageMenu2 = (DecorationPackageMenu)builder.levelThree(85.43);
        log.info("物料清单为：{}", decorationPackageMenu2.getDetail());



    }


}

interface IMenu {
    // 吊顶
    IMenu appendCeiling(Matter matter);

    // 涂料
    IMenu appendCoat(Matter matter);

    // 地板
    IMenu appendFloor(Matter matter);

    // 地砖
    IMenu appendTile(Matter matter);

    // 明细
    String getDetail();
}

// 装修包的实现

@Slf4j
class DecorationPackageMenu implements IMenu {

    // 装修清单
    private List<Matter> matterList = new ArrayList<>();

    // 装修价格
    private BigDecimal price = BigDecimal.ZERO;

    // 装修面积
    private BigDecimal area;

    // 装修等级
    private String grade;

    public DecorationPackageMenu() {
    }

    public DecorationPackageMenu(BigDecimal area, String grade) {
        this.area = area;
        this.grade = grade;
    }

    // 吊顶
    @Override
    public IMenu appendCeiling(Matter matter) {
        matterList.add(matter);
        price = price.add(area.multiply(new BigDecimal("0.2")).multiply(matter.price()));
        return this;
    }

    // 涂料
    @Override
    public IMenu appendCoat(Matter matter) {
        matterList.add(matter);
        price = price.add(area.multiply(new BigDecimal("1.4")).multiply(matter.price()));
        return this;
    }

    // 地板
    @Override
    public IMenu appendFloor(Matter matter) {
        matterList.add(matter);
        price = price.add(area.multiply(matter.price()));
        return this;
    }

    // 地砖
    @Override
    public IMenu appendTile(Matter matter) {
        matterList.add(matter);
        price = price.add(area.multiply(matter.price()));
        return this;
    }

    @Override
    public String getDetail() {

        StringBuilder detail = new StringBuilder("\r\n---------------\r\n" +
                "装修清单" + "\r\n" +
                "套餐等级" + grade + "\r\n" +
                "套餐价格" + price.setScale(2, BigDecimal.ROUND_HALF_UP) + "员\r\n" +
                "房屋面积" + area.doubleValue() + "平米\r\n" +
                "材料清单\r\n");

        for (Matter matter : matterList) {
            detail.append(matter.scene())
                    .append(":")
                    .append(matter.brand())
                    .append("、")
                    .append(matter.model())
                    .append("、平米价格：")
                    .append(matter.price())
                    .append("元。\n");
        }
        return detail.toString();
    }
}


/**
 *
 */
@Slf4j
class Builder {
    public IMenu levelOne(Double area) {
        DecorationPackageMenu decorationPackageMenu = new DecorationPackageMenu(new BigDecimal(area), "豪华欧式");
        decorationPackageMenu.appendCeiling(new LevelTwoCeiling()); // 二级顶
        decorationPackageMenu.appendCoat(new DuluxCoat()); // 多乐士
        decorationPackageMenu.appendFloor(new ShengXiangFloor()); // 圣象
        return decorationPackageMenu;
    }

    public IMenu levelTwo(Double area) {
        DecorationPackageMenu decorationPackageMenu = new DecorationPackageMenu(new BigDecimal(area), "轻奢田园");
        decorationPackageMenu.appendCeiling(new LevelTwoCeiling()); // 二级顶
        decorationPackageMenu.appendCoat(new LiBangCoat()); // 立邦
        decorationPackageMenu.appendCoat(new MarcoPoloTile()); // 马可波罗
        return decorationPackageMenu;
    }

    public IMenu levelThree(Double area) {
        DecorationPackageMenu decorationPackageMenu = new DecorationPackageMenu(new BigDecimal(area), "现代简约");
        decorationPackageMenu.appendCeiling(new LevelOneCeiling()); // 一级顶
        decorationPackageMenu.appendCoat(new LiBangCoat()); // 立邦
        decorationPackageMenu.appendTile(new DongPengTile()); // 东鹏
        return decorationPackageMenu;
    }


}


// 物料接口
interface Matter {
    String scene(); // 场景；地板、地砖、涂料、吊顶

    // 品牌
    String brand();

    // 型号
    String model();

    // 价格
    BigDecimal price();

    // 描述
    String desc();
}

// 吊顶 一级顶
class LevelOneCeiling implements Matter {
    @Override
    public String scene() {
        return "吊顶";
    }

    @Override
    public String brand() {
        return "装修公司自带";
    }

    @Override
    public String model() {
        return "一级顶";
    }

    @Override
    public BigDecimal price() {
        return new BigDecimal(260);
    }

    @Override
    public String desc() {
        return "造型只做低⼀级，只有⼀个层次的吊顶，⼀般离顶120-150mm";
    }
}

// 二级顶
class LevelTwoCeiling implements Matter {
    @Override
    public String scene() {
        return "吊顶";
    }

    @Override
    public String brand() {
        return "装修公司自带";
    }

    @Override
    public String model() {
        return "二级顶";
    }

    @Override
    public BigDecimal price() {
        return new BigDecimal(850);
    }

    @Override
    public String desc() {
        return "两个层次的吊顶，⼆级吊顶⾼度⼀般就往下吊20cm，要是层⾼很⾼，也可增加每级的厚度";
    }
}

// 涂料 立邦
class LiBangCoat implements Matter {
    @Override
    public String scene() {
        return "涂料";
    }

    @Override
    public String brand() {
        return "立邦";
    }

    @Override
    public String model() {
        return "默认级别";
    }

    @Override
    public BigDecimal price() {
        return new BigDecimal(650);
    }

    @Override
    public String desc() {
        return "⽴邦始终以开发绿⾊产品、注᯿⾼科技、⾼品质为⽬标，以技术⼒量不断推进科研和开发，满⾜消费者需求。";
    }
}

// 涂料 多乐士
class DuluxCoat implements Matter {
    @Override
    public String scene() {
        return "涂料";
    }

    @Override
    public String brand() {
        return "多乐士";
    }

    @Override
    public String model() {
        return "第二代";
    }

    @Override
    public BigDecimal price() {
        return new BigDecimal(719);
    }

    @Override
    public String desc() {
        return "多乐⼠是阿克苏诺⻉尔旗下的著名建筑装饰油漆品牌，产品畅销于全球100个国\n" +
                "家，每年全球有5000万户家庭使⽤多乐⼠油漆。\"";
    }
}

// 地板 德尔
class DerFloor implements Matter {
    @Override
    public String scene() {
        return "地板";
    }

    @Override
    public String brand() {
        return "德尔（Der）";
    }

    @Override
    public String model() {
        return "A+";
    }

    @Override
    public BigDecimal price() {
        return new BigDecimal(119);
    }

    @Override
    public String desc() {
        return "DER德尔集团是全球领先的专业⽊地板制造商，北京2008年奥运会家装和公装\n" +
                "地板供应商";
    }
}

// 圣象
class ShengXiangFloor implements Matter {
    @Override
    public String scene() {
        return "地板";
    }

    @Override
    public String brand() {
        return "圣象";
    }

    @Override
    public String model() {
        return "一级";
    }

    @Override
    public BigDecimal price() {
        return new BigDecimal(318);
    }

    @Override
    public String desc() {
        return "圣象地板是中国地板⾏业著名品牌。圣象地板拥有中国驰名商标、中国名牌、国\n" +
                "家免检、中国环境标志认证等多项荣誉。";
    }
}


class DongPengTile implements Matter {
    public String scene() {
        return "地砖";
    }

    public String brand() {
        return "东鹏瓷砖";
    }

    public String model() {
        return "10001";
    }

    public BigDecimal price() {
        return new BigDecimal(102);
    }

    public String desc() {
        return "东鹏瓷砖以品质铸就品牌，科技推动品牌，⼝碑传播品牌为宗旨，2014年品牌\n" +
                "价值132.35亿元，位列建陶⾏业榜⾸。";
    }
}

class MarcoPoloTile implements Matter {
    public String scene() {
        return "地砖";
    }

    public String brand() {
        return "⻢可波罗(MARCO POLO)";
    }

    public String model() {
        return "缺省";
    }

    public BigDecimal price() {
        return new BigDecimal(140);
    }

    public String desc() {
        return "⻢可波罗”品牌诞⽣于1996年";
    }
}






