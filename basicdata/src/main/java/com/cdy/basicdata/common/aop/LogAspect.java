package com.cdy.basicdata.common.aop;


import com.cdy.basicdata.system.domain.Result;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Enumeration;

/**
 * @Description: aop拦截controller、service、dao三层的入参和出参
 * @Author: chendeyin
 * @Date: 2020/9/20 14:12
 * @Modified:
 */
@Aspect
@Component
public class LogAspect {

    private static final Logger log = LoggerFactory.getLogger(LogAspect.class);


    @Value("${test.door.whiteList}")
    private String whiteList;

    private static final String CONTROLLER_LOG = "";

    public static String getPackageName() {
        String packageName = LogAspect.class.getClass().getPackage().getName();
        log.info("当前的包名为：{}", packageName);
        return packageName;
    }

    /**
     * 定义一个注解切点
     */
    @Pointcut("@annotation(com.cdy.basicdata.common.aop.LogAspectAnnotation)")
    public void logAspectAnnotation() {
    }


    @Around("logAspectAnnotation()")
    public Object doAnnotation(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.debug("string: {}", proceedingJoinPoint.toString());
        log.debug("shortString: {}", proceedingJoinPoint.toShortString());
        log.debug("longString: {}", proceedingJoinPoint.toLongString());


        log.debug("被代理的对象（即目标对象）: {}", proceedingJoinPoint.getTarget());
        log.debug("代理对象本身：{}", proceedingJoinPoint.getThis());


        log.debug("目标方法为: {}", proceedingJoinPoint.getSignature().getName());


        // 获取内容
        Signature signature = proceedingJoinPoint.getSignature();

        log.debug("签名内容为: {}", signature.getClass());
        MethodSignature methodSignature = (MethodSignature) signature;
        Class clazz = proceedingJoinPoint.getTarget().getClass();
        Method method = clazz.getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
        LogAspectAnnotation logAspectAnnotation = method.getAnnotation(LogAspectAnnotation.class);
        // 获取字段值
        String[] parameterNameArr = methodSignature.getParameterNames();

        int index = Arrays.binarySearch(parameterNameArr, logAspectAnnotation.key());
        Object filedValue = proceedingJoinPoint.getArgs()[index];
        log.debug("用户id为: {}", filedValue);
        String[] whiteListItemArr = whiteList.split(",");
        boolean flag = Arrays.stream(whiteListItemArr).anyMatch(i -> i.equals(filedValue));
        if (flag) {
            return proceedingJoinPoint.proceed();
        }

        // Class returnClass = method.getReturnType();

        return Result.defineError(444, "非法用户");
    }


//    /**
//     * @description: 定义一个切点
//     * @author: chendeyin
//     * @date: 2020/9/20 14:17
//     * @return: void
//     */
//    @Pointcut("execution( * com.cesec.springboot.system.controller.*.*(..))")
//    public void controllerLog() {
//    }
//
//    /**
//     * @description: 控制层前调方法
//     * @author: chendeyin
//     * @date: 2020/9/21 10:34
//     * @param joinPoint:
//     * @return: void
//     */
//    @Before("controllerLog()")
//    public void doBefore(JoinPoint joinPoint) {
//        ServletRequestAttributes attributes =
//                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//        log.info("请求方法: {}, 路径: {}", request.getMethod(), request.getRequestURL().toString());
//        log.info("进入controller层方法: {}", joinPoint.getTarget().getClass() + "." + joinPoint.getSignature().getName());
//        Enumeration<String> enu = request.getParameterNames();
//        while (enu.hasMoreElements()) {
//            String name = (String) enu.nextElement();
//            log.info("进入controller层参数: {}  值为: {}", name, request.getParameter(name));
//        }
//    }
//
//    /**
//     * @description: 控制层后调方法
//     * @author: chendeyin
//     * @date: 2020/9/21 10:37
//     * @param joinPoint:
//     * @param returnValue:
//     * @return: void
//     */
//    @AfterReturning(returning = "returnValue", pointcut = "controllerLog()")
//    public void doAfter( JoinPoint joinPoint, Object returnValue) {
//        log.info("退出controller层方法为: {}", joinPoint.getTarget().getClass() + "." + joinPoint.getSignature().getName());
//        if (null != returnValue){
//            log.info("退出controller层返回值为: {}", returnValue.toString());
//        }else {
//            log.info("退出controller层返回值为: {}", returnValue);
//        }
//    }
//
//    /**
//     * @description: 定义service层切面
//     * @author: chendeyin
//     * @date: 2020/9/21 10:39
//     * @return: void
//     */
//    @Pointcut("execution( * com.cesec.springboot.system.service.impl.*.*(..))")
//    public void serviceLog() {
//
//    }
//
//    /**
//     * @description: service层前调方法
//     * @author: chendeyin
//     * @date: 2020/9/21 10:38
//     * @param joinPoint:
//     * @return: void
//     */
//    @Before("serviceLog()")
//    public void doServiceBefore(JoinPoint joinPoint) throws Exception{
//        Signature signature = joinPoint.getSignature();
//        log.info("进入service层方法为: {}", joinPoint.getTarget().getClass() + "." + signature.getName());
//        MethodSignature methodSignature = (MethodSignature) signature;
//        String[] parameterNameArr = methodSignature.getParameterNames();
//        Object[] objectArr = joinPoint.getArgs();
//        if (parameterNameArr.length == objectArr.length) {
//            for (int i = 0; i < parameterNameArr.length; i++) {
//                log.info("进入service层参数: {}   值为: {}", parameterNameArr[i], objectArr[i]);
//            }
//        }else {
//            throw new Exception("进入service层入参的参数名个数和参数个数不一致！！！");
//        }
//    }
//
//    /**
//     * @description: service后调方法
//     * @author: chendeyin
//     * @date: 2020/9/21 10:39
//     * @param joinPoint:
//     * @param returnValue:
//     * @return: void
//     */
//    @AfterReturning(returning = "returnValue", pointcut = "serviceLog()")
//    public void doServiceAfter( JoinPoint joinPoint, Object returnValue) {
//        log.info("退出service层方法为: {}", joinPoint.getTarget().getClass() + "." + joinPoint.getSignature().getName());
//        log.info("退出service层返回值为: {}", returnValue);
//    }
//
//    /**
//     * @description: 定义mapper层切面
//     * @author: chendeyin
//     * @date: 2020/9/21 10:39
//     * @return: void
//     */
//    @Pointcut("execution( * com.cesec.springboot.system.dao.*.*(..))")
//    public void mapperLog() {
//
//    }
//
//    /**
//     * @param joinPoint:
//     * @description: mapper层前调方法
//     * @author: chendeyin
//     * @date: 2020/9/21 10:38
//     * @return: void
//     */
//    @Before("mapperLog()")
//    public void doMapperBefore(JoinPoint joinPoint) throws Exception {
//        Signature signature = joinPoint.getSignature();
//        log.info("进入mapper层方法为: {}", joinPoint.getTarget().getClass() + "." + signature.getName());
//        Object[] objectArr = joinPoint.getArgs();
//        if (0 != objectArr.length) {
//            for (int i = 0; i < objectArr.length; i++) {
//                log.info("进入mapper层参数值为: {}", objectArr[i]);
//            }
//        }
//    }
//
//    /**
//     * @param joinPoint:
//     * @param returnValue:
//     * @description: mapper后调方法
//     * @author: chendeyin
//     * @date: 2020/9/21 10:39
//     * @return: void
//     */
//    @AfterReturning(returning = "returnValue", pointcut = "mapperLog()")
//    public void doMapperAfter(JoinPoint joinPoint, Object returnValue) {
//        log.info("退出mapper层方法为: {}", joinPoint.getTarget().getClass() + "." + joinPoint.getSignature().getName());
//        if (null != returnValue) {
//            log.info("退出mapper层返回值为: {}", returnValue);
//        }
//    }

}
