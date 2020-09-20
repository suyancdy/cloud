package com.cestc.basicdata.system.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @Description:
 * @Author: chendeyin
 * @Date: 2020/9/20 14:12
 * @See: com.cestc.basicdata.system.aop
 * @Modified:
 */

@Aspect
@Component
public class LogAspect {

    private static final Logger log = LoggerFactory.getLogger(LogAspect.class);


    private static final String CONTROLLER_LOG = "";

    public static String getPackageName() {
        String packageName = LogAspect.class.getClass().getPackage().getName();
        log.info("当前的包名为：{}", packageName);
        return packageName;
    }


    /**
     * @description: 定义一个切点
     * @author: chendeyin
     * @date: 2020/9/20 14:17
     * @return: void
     */
    @Pointcut("execution( * com.cestc.basicdata.system.controller.*.*(..))")
    public void controllerLog() {
    }

    @Before("controllerLog()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        log.info("请求方法: {}, 路径: {}", request.getMethod(), request.getRequestURL().toString());
        log.info("controller层方法为: {}", joinPoint.getTarget().getClass() + "." + joinPoint.getSignature().getName());
        Enumeration<String> enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String name = (String) enu.nextElement();
            log.info("controller层参数: {}   值为: {}", name, request.getParameter(name));
        }

    }


    @AfterReturning(returning = "returnValue", pointcut = "controllerLog()")
    public void doAfter(Object returnValue) {
        log.info("返回值为: {}", returnValue.toString());
    }


    @Pointcut("execution( * com.cestc.basicdata.system.service.impl.*.*(..))")
    public void serviceLog() {

    }

    @Before("serviceLog()")
    public void doServiceBefore(JoinPoint joinPoint) throws Exception{
        Signature signature = joinPoint.getSignature();
        log.info("service层方法为: {}", joinPoint.getTarget().getClass() + "." + signature.getName());
        MethodSignature methodSignature = (MethodSignature) signature;
        String[] parameterNameArr = methodSignature.getParameterNames();
        Object[] objectArr = joinPoint.getArgs();
        if (parameterNameArr.length == objectArr.length) {
            for (int i = 0; i < parameterNameArr.length; i++) {
                log.info("service层参数: {}   值为: {}", parameterNameArr[i], objectArr[i]);
            }
        }else {
            throw new Exception("service层入参的参数名个数和参数个数不一致！！！");
        }
    }


}
