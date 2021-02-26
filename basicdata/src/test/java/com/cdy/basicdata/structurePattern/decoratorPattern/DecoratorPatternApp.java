//package com.cdy.basicdata.structurePattern.decoratorPattern;
//
//import lombok.Data;
//import lombok.extern.slf4j.Slf4j;
//
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//
///**
// * @Description: 装饰器模式
// * 装饰器模式允许向一个现有的对象添加新的功能，同时又不改变其结构
// * 这种模式创建一个装饰类，用来包装原有的类，并在保持类签名完整性的前提下，
// * 提供了额外的功能
// * <p>
// * 装饰器主要解决的是直接继承下因功能的不断横向扩展导致⼦类膨胀的问题，
// * ⽽是⽤装饰器模式后就会⽐直接继承显得更加灵活同时这样也就不再需要考虑⼦类的维护。
// * @Author: chendeyin
// * @Date: 2021/2/23 15:39
// */
//@Slf4j
//public class DecoratorPatternApp {
//
//    public static void main(String[] args) {
//        t1();
//    }
//
//    public static void t1(){
//       LoginSsoDecorator loginSsoDecorator = new LoginSsoDecorator(new SsoInterceptor());
//       String request="lsuccesshuahua";
//        boolean success = loginSsoDecorator.preHandle(request, "ewcdqwt40liuiu", "t");
//        log.debug("登录校验: {}, 是否放行: {}", request, success ? "放行": "拦截");
//    }
//
//}
//
//
//// 抽象组件-拦截接口
//interface HandleInterceptor {
//    boolean preHandle(String request, String response, Object handler);
//}
//
//
//// 具体组件-模拟单点登录功能
//class SsoInterceptor implements HandleInterceptor {
//    @Override
//    public boolean preHandle(String request, String response, Object handler) {
//        // 模拟获取cookie
//        String ticket = request.substring(1, 8);
//        // 模拟校验
//        return ticket.equals("success");
//    }
//}
//
//// 抽象类装饰角色
//
///**
// * 1、继承处理接口
// * 2、提供构造函数
// * 3、覆盖方法preHandle
// */
//@Data
//abstract class SsoDecorator implements HandleInterceptor {
//
//    private HandleInterceptor handleInterceptor;
//
//    public SsoDecorator() {
//    }
//
//    public SsoDecorator(HandleInterceptor handleInterceptor) {
//        this.handleInterceptor = handleInterceptor;
//    }
//
//    @Override
//    public boolean preHandle(String request, String response, Object handler) {
//        return handleInterceptor.preHandle(request, response, handler);
//    }
//}
//
//
//// 装饰器角色逻辑实现
//@Slf4j
//class LoginSsoDecorator extends SsoDecorator {
//
//    private static Map<String, String> authMap = new ConcurrentHashMap<>();
//
//    static {
//        authMap.put("huahua", "queryUserInfo");
//        authMap.put("doudou", "queryUserInfo");
//    }
//
//    public LoginSsoDecorator(HandleInterceptor handleInterceptor) {
//        super(handleInterceptor);
//    }
//
//    @Override
//    public boolean preHandle(String request, String response, Object handler) {
//
//        boolean success = super.preHandle(request, response, handler);
//        if (!success) return  false;
//
//        String userId = request.substring(8);
//        String method = authMap.get(userId);
//        log.debug("模拟单点登录方法访问拦截校验: {}, {}", userId, method);
//        // 模拟方法校验
//        return "queryUserInfo".equals(method);
//    }
//}
//
