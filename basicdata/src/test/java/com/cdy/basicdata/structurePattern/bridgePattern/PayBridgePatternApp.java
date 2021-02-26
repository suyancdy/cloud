//package com.cdy.basicdata.structurePattern.bridgePattern;
//
//import lombok.Data;
//import lombok.extern.slf4j.Slf4j;
//
//import java.math.BigDecimal;
//
///**
// * @Description: 重构PayController
// * @Author: chendeyin
// * @Date: 2021/2/23 9:37
// */
//@Slf4j
//public class PayBridgePatternApp {
//    public static void main(String[] args) {
//        log.debug("模拟测试场景，微信支付，人脸方式");
//        Pay wxPay = new WxPay(new PayFaceMode());
//        wxPay.transfer("110", "01111111", new BigDecimal(1000));
//
//        log.debug("换行");
//
//        log.debug("模拟测试场景，支付宝支付，指纹方式");
//        Pay zfbPay = new ZfbPay(new PayFingerprintMode());
//        zfbPay.transfer("119", "02222222", new BigDecimal(1254.5));
//
//
//    }
//
//}
//
//@Slf4j
//@Data
//abstract class Pay {
//    protected IPayMode payMode;
//
//    public Pay() {
//    }
//
//    public Pay(IPayMode payMode) {
//        this.payMode = payMode;
//    }
//
//    // 转账
//    public abstract String transfer(String userId, String tradeId, BigDecimal amount);
//}
//
//// 微信支付
//@Slf4j
//class WxPay extends Pay {
//    public WxPay(IPayMode payMode) {
//        super(payMode);
//    }
//
//    @Override
//    public String transfer(String userId, String tradeId, BigDecimal amount) {
//        log.debug("模拟微信渠道支付转账开始，userId:{}, tradeId:{}, amount:{}", userId, tradeId, amount);
//        boolean security = payMode.security();
//        log.debug("模拟微信渠道支付风控校验，userId:{}, tradeId:{}, security:{}", userId, tradeId, security);
//        if (!security){
//            log.warn("模拟微信渠道支付转账拦截，userId:{}, tradeId:{}, amount:{}", userId, tradeId, amount);
//            return "0001";
//        }
//        log.debug("模拟微信渠道支付转账成功，userId:{}, tradeId:{}, amount:{}", userId, tradeId, amount);
//
//        return "0000";
//    }
//}
//
//// 支付宝支付
//@Slf4j
//class ZfbPay extends  Pay{
//
//    public ZfbPay(IPayMode payMode) {
//        super(payMode);
//    }
//
//    @Override
//    public String transfer(String userId, String tradeId, BigDecimal amount) {
//        log.debug("模拟支付宝渠道支付转账开始，userId:{}, tradeId:{}, amount:{}", userId, tradeId, amount);
//        boolean security = payMode.security();
//        log.debug("模拟支付宝渠道支付风控校验，userId:{}, tradeId:{}, security:{}", userId, tradeId, security);
//        if (!security){
//            log.warn("模拟支付宝渠道支付转账拦截，userId:{}, tradeId:{}, amount:{}", userId, tradeId, amount);
//            return "0001";
//        }
//        log.debug("模拟支付宝渠道支付转账成功，userId:{}, tradeId:{}, amount:{}", userId, tradeId, amount);
//
//        return "0000";
//    }
//}
//
//
//interface IPayMode {
//    // 任何一个支付模式，刷脸，指纹，密码都会过不同程度的安全风控，这里定义一个安全校验接口
//    public boolean security();
//}
//
//// 人脸
//@Slf4j
//class PayFaceMode implements IPayMode {
//    @Override
//    public boolean security() {
//        log.debug("人脸支付，风控校验脸部识别");
//        return true;
//    }
//}
//
//// 指纹
//@Slf4j
//class PayFingerprintMode implements IPayMode {
//    @Override
//    public boolean security() {
//        log.debug("指纹支付，风控校验指纹信息");
//        return true;
//    }
//}
//
//// 密码
//@Slf4j
//class PayCypherMode implements IPayMode {
//    @Override
//    public boolean security() {
//        log.debug("密码支付，风控校验环境安全");
//        return true;
//    }
//}
//
//
//
