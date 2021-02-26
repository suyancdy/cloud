package com.cdy.basicdata.structurePattern.proxyPattern;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.math.BigDecimal;

/**
 * @Description: 动态代理
 * @Author: chendeyin
 * @Date: 2021/2/25 15:31
 */
@Slf4j
public class DynamicProxyApp {

    public static void main(String[] args) {
        HouseTransaction houseTransaction = new CustomerHouseTransaction();
        HouseTransaction proxyTransaction = (HouseTransaction) Proxy.newProxyInstance(
                houseTransaction.getClass().getClassLoader(),
                new Class[]{HouseTransaction.class},
                new DynamicProxyInvocationHandler(houseTransaction));
        proxyTransaction.buyHouse();
        proxyTransaction.sellHouse(new BigDecimal(1000000000000000000L));

        log.info("-------------");
    }

}

@Slf4j
@Data
class DynamicProxyInvocationHandler implements InvocationHandler {

    /**
     * 代理类持有目标类的引用（目标对象）
     * 这里会构成一种静态代理关系
     */
    private Object target;

    public DynamicProxyInvocationHandler() {
    }

    public DynamicProxyInvocationHandler(Object target) {
        this.target = target;
    }

    /**
     * @param proxy  代理对象
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.debug("invoke before");
        Object result = method.invoke(target, args);
        log.debug("invoke after");
        return  result;
    }

}


