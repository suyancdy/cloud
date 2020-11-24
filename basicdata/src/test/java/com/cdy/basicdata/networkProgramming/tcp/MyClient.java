package com.cdy.basicdata.networkProgramming.tcp;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @Description:
 * @Author: chendeyin
 * @Date: 2020/11/24 19:02
 */
@Slf4j
public class MyClient {
    public static void main(String[] args) {

    }

    public static void t1() throws Exception {
        String ip = "127.0.0.1";
        int port = 8520;
        Socket socket = new Socket(ip, port);

    }
}

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

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
