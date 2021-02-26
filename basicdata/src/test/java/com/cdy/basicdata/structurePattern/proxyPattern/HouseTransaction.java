package com.cdy.basicdata.structurePattern.proxyPattern;

import java.math.BigDecimal;

/**
 * @Description:
 * @Author: chendeyin
 * @Date: 2021/2/26 9:27
 */
// 委托类接口
interface HouseTransaction {

    // 买房
    void buyHouse();
    // 卖房
    BigDecimal sellHouse(BigDecimal price);
}
