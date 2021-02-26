//package com.cdy.basicdata.structurePattern.bridgePattern;
//
//import lombok.extern.slf4j.Slf4j;
//
//import java.math.BigDecimal;
//
///**
// * @Description: if-else实现微信、支付宝支付
// * @Author: chendeyin
// * @Date: 2021/2/22 17:11
// */
//@Slf4j
//public class PayController {
//
//    // 进行测试
//    public static void main(String[] args) {
//        //
//        PayController payController = new PayController();
//        log.debug("模拟测试场景；微信支付，人脸方式");
//        payController.doPay("weixinUserId-110", "000000000001",
//                new BigDecimal(200), 1, 2);
//
//        log.debug("");
//        log.debug("模拟测试场景；支付宝方式，指纹方式");
//        payController.doPay("zfbUserIf110", "0100000000002",
//                new BigDecimal(300), 2, 3);
//    }
//
//    public boolean doPay(String uId,
//                         String tradeId,
//                         BigDecimal amount,
//                         Integer channelType,
//                         Integer modeType) {
//        if (1 == channelType) {
//            log.debug("模拟微信渠道支付开始, uid: {}, tradeId: {}, amount: {}", uId, tradeId, amount);
//            if (1 == modeType) {
//                log.debug("密码支付，风控校验环境安全");
//            } else if (2 == modeType) {
//                log.debug("人脸支付，风控校验脸部识别");
//            } else if (3 == modeType) {
//                log.debug("指纹支付，风控校验指纹信息");
//            }
//        } else if (2 == channelType) {
//            log.debug("模拟支付宝渠道支付开始, uid: {}, tradeId: {}, amount: {}", uId, tradeId, amount);
//            if (1 == modeType) {
//                log.debug("密码支付，风控校验环境安全");
//            } else if (2 == modeType) {
//                log.debug("人脸支付，风控校验脸部识别");
//            } else if (3 == modeType) {
//                log.debug("指纹支付，风控校验指纹信息");
//            }
//        }
//        return true;
//    }
//
//}
