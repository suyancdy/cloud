package com.cdy.basicdata.networkProgramming.basic;

import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @Description:
 * @Author: chendeyin
 * @Date: 2020/11/24 9:44
 */
@Slf4j
public class NetSupport {

    public static void main(String[] args) throws Exception {
//        testInetAddress();
        URLDecoderAndURLEncoder();
    }

    public static void testInetAddress() throws Exception {
        //
        String s = "www.baidu.com";
        // 给定主机名确定主机的ip地址
        InetAddress ip = InetAddress.getByName(s);
        log.info("{}是否可达： {}", s, ip.isReachable(2000));
        log.info("ip的字符串： {}", ip.getHostAddress());
        // 根据原始的IP地址获取对应的InetAddress实例
        InetAddress local = InetAddress.getByAddress(new byte[] {127,0,0,1});
        log.info("本地是否可达： {}", local.isReachable(2000));
        log.info("本地的ip字符串： {}", local.getHostAddress());
        // 获取实例的全限定域名
        log.info("local对应的全限定域名为： {}", local.getCanonicalHostName());

    }


    public static  void URLDecoderAndURLEncoder() throws Exception{
        // 将application/x-www.form-urlencoded字符串转换为普通字符串
        String s = "%E6%9D%A5%E8%87%AA%E6%98%9F%E6%98%9F%E7%9A%84%E4%BD%A0";
        String keyword = URLDecoder.decode(s, "utf-8");
        log.info("s的真实字符为： {}", keyword);


        String common = "我爱你";
        String commonWord = URLEncoder.encode(common, "utf-8");
        log.info("common的XX字符为： {}", commonWord);
    }


    /**
     * URL
     */
    public static void url(){

    }


}
