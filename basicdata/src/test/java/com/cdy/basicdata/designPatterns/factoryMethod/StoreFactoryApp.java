package com.cdy.basicdata.designPatterns.factoryMethod;

import com.alibaba.fastjson.JSON;
import com.cdy.basicdata.system.entity.People;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @Description:
 * @Author: chendeyin
 * @Date: 2020/12/29 16:29
 */
@Slf4j
public class StoreFactoryApp {

    public static void main(String[] args) throws JsonProcessingException {

    }

    public static void test() {

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
    public Boolean deliverGoods(DeliverReq deliverReq){
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

//        Boolean isSuccess =

    }

}

