package com.cdy.basicdata.structurePattern.proxyPatternPlus;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description:
 * @Author: chendeyin
 * @Date: 2021/2/26 16:21
 */
public class App {
    public static void main(String[] args) {
        Subject realSubject = new RealSubject();
        Subject proxySubject = new ProxySubject(realSubject);
        proxySubject.request("1");
        proxySubject.fun("7878798");
    }
}
// 抽象主题
interface Subject {
    void request(Object param);
    Object fun(Object param);
}
// 真实主题
@Slf4j
class RealSubject implements Subject {
    @Override
    public void request(Object param) {
        log.debug("访问真实主题的方法");
    }
    @Override
    public Object fun(Object param) {
        log.debug("访问带参数的方法,参数为: {}", param.toString());
        return null;
    }
}
// 代理类
@Slf4j
@Data
class ProxySubject implements Subject {
    private Subject subject;
    public ProxySubject() {
    }
    public ProxySubject(Subject subject) {
        this.subject = subject;
    }
    @Override
    public void request(Object param) {
        log.debug("调用方法之前");
        subject.request(param);
        log.debug("调用方法之后");
    }

    @Override
    public Object fun(Object param) {
        log.debug("调用方法之前");
        subject.fun(param);
        log.debug("调用方法之后");
        return null;
    }
}


