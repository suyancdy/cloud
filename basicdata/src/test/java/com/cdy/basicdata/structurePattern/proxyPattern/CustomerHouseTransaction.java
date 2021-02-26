package com.cdy.basicdata.structurePattern.proxyPattern;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

/**
 * @Description:
 * @Author: chendeyin
 * @Date: 2021/2/26 9:26
 */
// 目标对象
@Slf4j
class CustomerHouseTransaction implements HouseTransaction {
    @Override
    public void buyHouse() {
        log.debug("XX要买房");
    }

    @Override
    public BigDecimal sellHouse(BigDecimal price) {
        //
        log.debug("XX要出售房子,卖了{}元钱", price.toString());
        return price;
    }
}
