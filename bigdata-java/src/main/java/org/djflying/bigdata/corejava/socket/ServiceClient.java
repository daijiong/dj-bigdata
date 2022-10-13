package org.djflying.bigdata.corejava.socket;

import java.io.*;
import java.net.Socket;

/**
 * socket客户端
 *
 * @author dj4817
 * @version $Id: ServiceClient.java, v 0.1 2017/11/30 13:36 dj4817 Exp $$
 */
public class ServiceClient {

    /**
     * 程序入口
     *
     * @param args
     */
    public static void main(String[] args) {

        InputStream inputStream = null;
        OutputStream outputStream = null;
        Socket socket = null;
        try {
            // 1.创建socket连接，向服务器发出请求
            socket = new Socket("localhost", 8899);
            // 2.从socket中获取输入输出流
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
            // 3.向socket输出流中写入数据
            PrintWriter pw = new PrintWriter(outputStream);
            pw.println("hello");
            pw.flush();

            // 4.从socket输入流中读取数据
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String result = br.readLine();
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 5.关闭资源
            try {
                inputStream.close();
                outputStream.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
