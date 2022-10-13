package org.djflying.bigdata.corejava.socket;

import java.io.*;
import java.net.Socket;

/**
 * socket服务端业务处理任务
 *
 * @author dj4817
 * @version $Id: ServiceServerTask.java, v 0.1 2017/11/30 14:25 dj4817 Exp $$
 */
public class ServiceServerTask implements Runnable {

    private Socket socket;

    /**
     * 空参构造器
     */
    public ServiceServerTask() {
    }

    /**
     * 全参构造器
     *
     * @param socket
     */
    public ServiceServerTask(Socket socket) {
        this.socket = socket;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {

        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            System.out.println("接收到来自IP" + socket.getInetAddress().getHostAddress() + "的请求");
            // 1.从socket连接中获取到与client之间的网络通信输入输出流。
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
            // 2.从网络通信输入流中读取客户端发送过来的数据。注意：socketinputstream的读数据的方法都是阻塞的。
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String param = br.readLine();
            // 3.业务处理
            GetDataServiceImpl service = new GetDataServiceImpl();
            String result = service.getData(param);
            // 4.将调用结果写到sokect的输出流中，以发送给客户端
            PrintWriter pw = new PrintWriter(outputStream);
            pw.println(result);
            pw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 5.关闭资源
            try {
                if (null != inputStream) {
                    inputStream.close();
                }
                if (null != outputStream) {
                    outputStream.close();
                }
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
