package com.cdy.basicdata.networkProgramming.tcp;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * @Description:
 * @Author: chendeyin
 * @Date: 2020/11/24 19:02
 */
@Slf4j
public class MyClient {
    public static void main(String[] args) throws Exception {
        t1();
    }

    public static void t1() throws Exception {
        String ip = "127.0.0.1";
        int port = 8520;
        Socket socket = new Socket(ip, port);
        new Thread(new ClientThread(socket)).start();

        PrintStream printStream = new PrintStream(socket.getOutputStream());

        String line = null;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        while ( (line = bufferedReader.readLine())!=null ){
            printStream.println(line);
        }
    }
}

@Slf4j
class ClientThread implements Runnable {

    private Socket socket;

    private BufferedReader bufferedReader = null;

    public ClientThread(Socket socket) throws Exception {
        this.socket = socket;
        bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void run() {
        try {
            String content = null;
            while ( (content = bufferedReader.readLine())!=null  ){
                log.info("来自其他客户端的消息是：{}", content);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
