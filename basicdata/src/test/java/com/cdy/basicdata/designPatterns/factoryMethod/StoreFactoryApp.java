package com.cdy.basicdata.designPatterns.factoryMethod;

import com.alibaba.fastjson.JSON;
import com.cdy.basicdata.system.entity.People;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: chendeyin
 * @Date: 2020/12/29 16:29
 */
@Slf4j
public class StoreFactoryApp {

    public static void main(String[] args) throws JsonProcessingException {
        test();
    }

    public static void test() throws JsonProcessingException {
        StoreFactory storeFactory = new StoreFactory();
        // 1.优惠券
        ICommodityService   commodityService_1 = storeFactory.getCommodityService(1);
        commodityService_1.sendCommodity("10001",
                "EGM1023938910232121323432","791098764902132", null);

        // 2.实物商品
        ICommodityService commodityService_2 = storeFactory.getCommodityService(2);
        Map<String, String> extMap = new HashMap<>();
        extMap.put("consigneeUserName", "谢菲");
        extMap.put("consigneeUserPhone", "15111223344");
        extMap.put("consigneeUserAddress", "甘肃省庆阳市西峰区XXX小区xxx室");
        commodityService_2.sendCommodity("10001", "9820198721311","1023000020112221113", extMap);

        // 3.第三方兑换卡
        ICommodityService commodityService_3 = storeFactory.getCommodityService(3);
        commodityService_3.sendCommodity("10001","AQY1xjkUodl8LO975GdfrYUio",null, null);

    }

    public static void test1() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        People people = new People();
        people.setAge(56);
        people.setName("小城");
        log.info("===:{}", objectMapper.writeValueAsString(people));
        log.info("===");
        log.info("===:{}", JSON.toJSON(people));
    }

}

class CouponResult {

    private String code; // 编码
    private String info; // 描述

    public CouponResult(String code, String info) {
        this.code = code;
        this.info = info;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}


/**
 * 定义发奖的接口
 * uId: 用户id
 * commodityId: 奖品id
 * bziId: 业务id
 * extMap: 拓展字段
 */
interface ICommodityService {
    void sendCommodity(String uId, String commodityId,
                       String bziId, Map<String, String> extMap) throws JsonProcessingException;
}

@Slf4j
class CouponService {

    public CouponResult sendCoupon(String uId, String couponNumber, String uuid) {
        System.out.println("模拟发放优惠券一张：" + uId + "," + couponNumber + "," + uuid);
        return new CouponResult("0000", "发放成功");
    }

}

/**
 * 优惠券
 */
@Slf4j
class CouponCommodityService implements ICommodityService {
    ObjectMapper objectMapper = new ObjectMapper();

    // 注入
    private CouponService couponService = new CouponService();

    @Override
    public void sendCommodity(String uId, String commodityId, String bziId, Map<String, String> extMap) throws JsonProcessingException {
        CouponResult couponResult = couponService.sendCoupon(uId, commodityId, bziId);
        log.info("请求参数[优惠券] -> uId:{}, commodityId:{}, bizId:{}, extMap:{}", uId, commodityId, bziId, objectMapper.writeValueAsString(extMap));
        log.info("测试结果[优惠券]: {}", JSON.toJSON(couponResult));
        if (!couponResult.getCode().equals("0000")) {
            throw new RuntimeException(couponResult.getInfo());
        }
    }
}


/**
 * 实物商品的请求参数
 */
@Data
class DeliverReq {
    private String userName; // 用户姓名
    private String userPhone; //
    private String sku;       // 商品sku
    private String orderId;   // 订单id
    private String consigneeUserName; // 收货人姓名
    private String consigneeUserPhone;    // 收货人手机
    private String consigneeUserAddress;  // 收获人地址

    public DeliverReq() {
    }

    @Override
    public String toString() {
        return "DeliverReq{" +
                "userName='" + userName + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", sku='" + sku + '\'' +
                ", orderId='" + orderId + '\'' +
                ", consigneeUserName='" + consigneeUserName + '\'' +
                ", consigneeUserPhone='" + consigneeUserPhone + '\'' +
                ", consigneeUserAddress='" + consigneeUserAddress + '\'' +
                '}';
    }
}

@Slf4j
class GoodsService {
    public Boolean deliverGoods(DeliverReq deliverReq) {
        log.info("模拟发货实物商品一个:{}", JSON.toJSONString(deliverReq));
        return true;
    }
}

/**
 * 实物商品的
 */
@Slf4j
class GoodsCommodityService implements ICommodityService {

    // 注入
    private GoodsService goodsService = new GoodsService();

    @Override
    public void sendCommodity(String uId, String commodityId, String bziId, Map<String, String> extMap) throws JsonProcessingException {
        DeliverReq deliverReq = new DeliverReq();
        deliverReq.setUserName("name_" + uId);
        deliverReq.setUserPhone("phone_" + uId);
        deliverReq.setSku(commodityId);
        deliverReq.setOrderId(bziId);
        deliverReq.setConsigneeUserName(extMap.get("consigneeUserName"));
        deliverReq.setConsigneeUserPhone(extMap.get("consigneeUserPhone"));
        deliverReq.setConsigneeUserAddress(extMap.get("consigneeUserAddress"));

        Boolean isSuccess = goodsService.deliverGoods(deliverReq);
        log.info("请求参数[实物商品] => uId:{} commodityId:{} bizId:{} extMap:{}", uId, commodityId, bziId, JSON.toJSON(extMap));
        log.info("测试结果[实物商品] : {}", isSuccess);
        if (!isSuccess) throw new RuntimeException("实物商品发送失败");
    }
}


@Slf4j
class IQiYiCardService {
    public void grantToken(String bindMobileNumber, String cardId) {
        log.info("模拟发放爱奇艺会员卡一张: {}, {}", bindMobileNumber, cardId);

    }
}

/**
 * 第三方兑换卡
 */
@Slf4j
class CardCommodityService implements ICommodityService {

    // 模拟注入
    private IQiYiCardService iQiYiCardService = new IQiYiCardService();

    @Override
    public void sendCommodity(String uId, String commodityId, String bziId, Map<String, String> extMap) throws JsonProcessingException {
        String mobile = queryUserMobile(uId);
        iQiYiCardService.grantToken(mobile, bziId);
        log.info("请求参数[爱奇艺兑换卡] => uId: {}, commodityOId: {}, bizId: {}, extMap:{}", uId, commodityId, bziId, JSON.toJSON(extMap));
        log.info("测试结果[爱奇艺兑换卡]: success");
    }

    private String queryUserMobile(String uId) {
        return "15111223344";
    }
}

/**
 * 创建商品工厂
 */
class StoreFactory {
    public ICommodityService getCommodityService(Integer commodityType) {
        if (commodityType == null) {
            return null;
        } else if (commodityType == 1) {
            return new CouponCommodityService();
        }else if (commodityType == 2){
            return new GoodsCommodityService();
        }else if (commodityType == 3){
            return new CardCommodityService();
        }
        throw new RuntimeException("不存在的商品服务类型");
    }
}

