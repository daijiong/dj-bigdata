package org.djflying.bigdata.corejava.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * socket服务端
 *
 * @author dj4817
 * @version $Id: ServiceServer.java, v 0.1 2017/11/30 13:35 dj4817 Exp $$
 */
public class ServiceServer {

    /**
     * 程序入口
     *
     * @param args
     */
    public static void main(String[] args) {

        try {
            // 1.创建一个ServerSocket，绑定到本机的8899端口上
            ServerSocket server = new ServerSocket();
            server.bind(new InetSocketAddress("localhost", 8899));
            System.out.println("socket服务端已开启：");
            while (true) {
                // 2.接受客户端的连接请求。注意：accept是一个阻塞方法，会一直等待，直到有客户端请求连接才返回。
                Socket socket = server.accept();
                // 3.每次有客户端连接过来就开启一个线程来执行任务。
                Thread thread = new Thread(new ServiceServerTask(socket), "thread");
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
