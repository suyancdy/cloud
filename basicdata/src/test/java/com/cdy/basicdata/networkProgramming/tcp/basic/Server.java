package com.cdy.basicdata.networkProgramming.tcp.basic;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Description: 使用ServerSocket创建TCP服务器端
 * 在两个通讯实体没有建立虚拟链路之前，必须有一个通信实体先做出主动姿态，主动接收来自其他通讯实体的连接请求。
 * Java中能接收其他通讯实体的连接请求的类是ServerSocket，ServerSocket对象用于监听来自客户端的Socket连接
 * 如果没有连接，它将一直处于等待状态。ServerSocket包含了一个监听客户端请求的方法
 * 1、Socket accept(): 如果接收到一个客户端Socket的连接请求，该方法将返回一个与客户端Socket对应的Socket，
 *    否侧该方法一直处于等待状态，线程也被阻塞
 * 为了创建ServerSocket对象，ServerSocket类提供了如下几个构造器
 *  1、
 *  2、
 *  3、
 * 当ServerSocket使用完毕后，应使用ServerSocket的close()方法来关闭该ServerSocket
 * 在通常情况下，服务器不应该只接收一个客户端请求，而应该不断地接收来自客户端的所有请求，
 * 所以Java程序通常会循环不断地调用ServerSocket的accept()方法
 *
 *
 * 当客户端、服务器端产生了对应的Socket之后，程序无序在区分服务器端和客户端，而是通过各自的Socket进行通信
 * InputStream getInputStream()：返回该Socket对象对应的输入流，让程序通过输入流从Socket中读数据
 * OutputStream getOutputStream()：返回该Socket对象对应的输出流，让程序通过该输出流项Socket中写数据
 * @Author: chendeyin
 * @Date: 2020/11/24 14:02
 */
@Slf4j
public class Server {

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) throws Exception {
        t1();
    }

    public static void t1() throws  Exception{
        // 创建一个ServerSocket,用于监听客户端Socket的连接请求
        int port = 8520;
        ServerSocket serverSocket = new ServerSocket(port); // important
        log.info("创建ServerSocket服务器端，端口号为：{}", port);
        // 采用循环不断地接收来自客户端的请求
        while (true){
            // 每当接收到客户端Socket的请求时，服务器端也对应产生一个Socket
            Socket socket = serverSocket.accept(); // important
            PrintStream printStream = new PrintStream(socket.getOutputStream());
            LocalDateTime localDateTime = LocalDateTime.now();

            String s = "你好，我是来自服务器祝福, 服务器时间为: " + localDateTime.format(dateTimeFormatter);
            printStream.println(s);
            printStream.close();
            socket.close();
        }
    }

}
