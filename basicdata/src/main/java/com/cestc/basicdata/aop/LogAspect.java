package com.cestc.basicdata.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.logging.Logger;

/**
 * @Description:
 * @Author: chendeyin
 * @Date: 2020/9/20 14:12
 * @See: com.cestc.basicdata.aop
 * @Modified:
 */

@Aspect
@Component
public class LogAspect {

    private static final  Logger = LoggerFactory.getLogger(LogAspect.class);


    private static final String CONTROLLER_LOG = "";

    public static String getPackageName(){
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
    @Pointcut("execution(* com.cestc.basicdata.controller.*.*(..))")
    public void  ControllerLog(){
    }

    @Before("ControllerLog()")
    public void doBefore(JoinPoint joinPoint){
        // 请求属性
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();


    }




}
