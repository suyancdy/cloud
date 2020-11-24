package com.cdy.basicdata.networkProgramming.tcp.basic;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @Description: 使用Socket进行通信
 *
 * 当客户端、服务器端产生了对应的Socket之后，程序无序在区分服务器端和客户端，而是通过各自的Socket进行通信
 * InputStream getInputStream()：返回该Socket对象对应的输入流，让程序通过输入流从Socket中读数据
 * OutputStream getOutputStream()：返回该Socket对象对应的输出流，让程序通过该输出流项Socket中写数据
 * @Author: chendeyin
 * @Date: 2020/11/24 14:31
 */
@Slf4j
public class Client {
    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 100; i++) {
            log.info("===: {}", i);
            t1();
        }
    }
    public static void t1() throws Exception{
        String ip = "127.0.0.1";
        int port = 8520;
//        Socket socket = new Socket(ip, port); // important
//        // 设置1秒后即认为超时
//        int timeout = 1*1000;
//        socket.setSoTimeout(timeout);

        Socket socket1 = new Socket();
        socket1.connect(new InetSocketAddress(ip,port), 1*1000);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket1.getInputStream()));
        String line = bufferedReader.readLine();
        log.info("来自服务器的消息为： {}", line);
        bufferedReader.close();
        socket1.close();
    }


}
