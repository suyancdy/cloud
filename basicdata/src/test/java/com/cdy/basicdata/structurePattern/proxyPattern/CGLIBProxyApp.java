package com.cdy.basicdata.structurePattern.proxyPattern;

import com.thoughtworks.xstream.converters.reflection.CGLIBEnhancedConverter;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Description: CGLIB动态代理
 * JDK动态代理依赖接口实现，当只有类没有接口时就需要CGLIB动态代理
 *
 * CGLIB代理时针对类来实现代理，原理是对指定的目标类（委托类）生成一个子类并重写其中业务方法来实现代理。
 * 代理类对象是由Enhancer类创建的，CGLIB创建动态代理类的模式为：
 *
 *
 * @Author: chendeyin
 * @Date: 2021/2/26 9:28
 */
@Slf4j
public class CGLIBProxyApp {

    public static void main(String[] args) {
        test();
    }

    public static void test() {
        CglibMethodInterceptor cglibMethodInterceptor = new CglibMethodInterceptor();
        // 使用动态代理创建代理对象
        //
        /**
         * CGLIB 增强类对象，代理类对象是由 Enhancer 类创建的，
         * Enhancer 是 CGLIB 的字节码增强器，可以很方便的对类进行拓展
         */
        Enhancer enhancer = new Enhancer();
        // 设置产生的代理对象的父类，增强类型
        enhancer.setSuperclass(CustomerHouseTransaction.class);
        // 设置代理逻辑对象，代理逻辑对象实现MethodInterceptor接口
        enhancer.setCallback(cglibMethodInterceptor);
        //
        CustomerHouseTransaction proxy =  (CustomerHouseTransaction)enhancer.create();
        //

        log.debug("===: {}", proxy.getClass().getSuperclass());
        proxy.buyHouse();


    }

}


// CglibMethodInterceptor 用于对方法调用拦截以及回调
@Slf4j
@Data
class CglibMethodInterceptor implements MethodInterceptor {

    /**
     *
     * @param o 被代理的对象（目标对象）
     * @param method 代理方法
     * @param objects 方法的参数
     * @param methodProxy CGLIB方法代理对象
     * @return  CGLIB生成用来代Method对象的一个对象，使用MethodProxy比调用JDK自身的Method直接执行方法效率会有提升
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        log.debug("方法{}调用之前", method.getName());
        Object result = methodProxy.invokeSuper(o, objects);
        log.debug("方法{}调用之后",method.getName());
        return result;
    }
}





