package com.cestc.basicdata.system.aop;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
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
    @Pointcut("execution(public * com.cestc.basicdata.system.controller.*.*(..))")
    public void ControllerLog() {
    }

    @Before("ControllerLog()")
    public void doBefore() {
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        log.info("请求方法: {}, 路径: {}", request.getMethod(), request.getRequestURL().toString());
        Enumeration<String> enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String name = (String) enu.nextElement();
            log.info("参数: {}   值为: {}", name, request.getParameter(name));
        }
    }


    @AfterReturning(returning = "returnValue", pointcut = "ControllerLog()")
    public void doAfter(Object returnValue) {
        log.info("返回值为: {}", returnValue.toString());
    }


}
