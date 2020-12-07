package com.cdy.basicdata.networkProgramming.tcp;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : chendeyin
 * @Description : 加入多线程的ServerSocket服务器端
 * <p>
 * 实际应用中的客户端则可能需要与服务器端保持长时间通信，即服务端需要不断读取客户端的数据，并向客户端写入数据；
 * 客户端也需要不断读取服务器端数据，并向服务器端写入数据。
 * <p>
 * 在使用传统BufferedReader的readLine()方法读取数据时，在该方法成功返回之前，线程被阻塞，程序无法继续执行
 * 因此，服务器端应该为每个Socket单独启动一个线程，该线程专门负责与一个客户端进行通信
 * 客户端读取服务器端数据的线程同样会被阻塞，因此系统应该单独启动一个线程，该线程专门负责读取服务器端的数据
 * @date : 2020/11/24 16:40
 */
@Slf4j
public class MyServer {

    //
    public static List<Socket> socketList = Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args)  throws Exception{
        startServer();
    }

    public static void startServer() throws Exception {
        int port = 8520;
        ServerSocket serverSocket = new ServerSocket(port);
        log.info("新建一个ServerSocket，端口号为：{}", port);
        while (true) {
            // 此行代码会阻塞将一直等待别人的连接
            Socket socket = serverSocket.accept();
            log.info("监听到一个请求，将其放入容器中");
            socketList.add(socket);
            new Thread(new ServerThread(socket)).start();
        }
    }

}

@Slf4j
class ServerThread implements Runnable {
    private Socket socket;
    BufferedReader bufferedReader;

    public ServerThread(Socket socket) throws Exception {
        this.socket = socket;
        // 初始化该socket对应的输入流
        bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }


    @Override
    public void run() {
        try {
            String content = null;
            // 采用循环不间断地从Socket中读取客户端发送过来的数据
            while ((content = readFromClient()) != null) {
                // 遍历列表中中的每个Socket，将读到的内容向每个Socket发送一次
                for (Socket so : MyServer.socketList) {
                    PrintStream printStream = new PrintStream(so.getOutputStream());
                    printStream.println(content);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String readFromClient() {
        try {
            return bufferedReader.readLine();
        } // 如果捕获到异常则表明该Socket对应的客户端已经关闭
        catch (IOException e) {
            log.info("该Socket对应的客户端已经关闭， 从列表中移除该Socket");
            MyServer.socketList.remove(socket);
        }
        return null;
    }
}