package com.cdy.basicdata.structurePattern.proxyPattern;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

/**
 * @Description: 静态代理
 * @Author: chendeyin
 * @Date: 2021/2/25 13:36
 */
@Slf4j
public class StaticProxyApp {


    public static void main(String[] args) {
        HouseTransaction houseTransaction = new CustomerHouseTransaction();
        HouseTransaction houseTransactionProxy = new ProxyHouseTransaction(houseTransaction);
        houseTransactionProxy.buyHouse();
    }
}

// 接口
interface HouseTransaction {
    // 买房
    void buyHouse();

    // 卖房
    BigDecimal sellHouse(BigDecimal price);
}

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
        log.debug("XX要出售房子");
        return price;
    }
}

// 代理对象
@Slf4j
@Data
class ProxyHouseTransaction implements HouseTransaction {

    private  HouseTransaction houseTransaction;

    public ProxyHouseTransaction() {
    }

    public ProxyHouseTransaction(HouseTransaction houseTransaction) {
        this.houseTransaction = houseTransaction;
    }

    @Override
    public void buyHouse() {
        log.debug("买房前准备");
        // 买房操作
        houseTransaction.buyHouse();
        log.debug("买房后装修");
    }

    @Override
    public BigDecimal sellHouse(BigDecimal price) {
        log.debug("出售房子前的准备");
        return houseTransaction.sellHouse(price);
    }
}

