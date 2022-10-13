package org.djflying.bigdata.corejava.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * nio服务端程序
 *
 * @author dj4817
 * @version $Id: MultiplexerTimeServer.java, v 0.1 2017/12/5 21:42 dj4817 Exp $$
 */
public class MultiplexerTimeServer {

    private int                 port;
    private Selector            selector;
    private ServerSocketChannel serverSocketChannel;
    private volatile boolean    stop;

    /**
     * 无参构造器
     */
    public MultiplexerTimeServer() {
    }

    /**
     * 带参构造器
     *
     * @param port
     */
    public MultiplexerTimeServer(int port) {

        try {
            // 打开一个监听器
            selector = Selector.open();
            // 打开一个socket channel
            serverSocketChannel = ServerSocketChannel.open();
            // channel配置非阻塞
            serverSocketChannel.configureBlocking(false);
            // 配置socket连接绑定的端口
            serverSocketChannel.socket().bind(new InetSocketAddress(port), 1024);
            // 将channel注册到selector上，并定义类型为接收请求。
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理请求
     */
    public void handleInput() {

        while (!stop) {
            try {
                selector.select(1000);
                // 查找当前selector下所有活跃的selectionKey（每一个SelectionKey对应一个channel）
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    // 将当前处理的selectionKey从集合中移除
                    iterator.remove();
                    // 处理当前selectionKey
                    handleKey(selectionKey);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 多路复用器关闭后，所有注册在上面的Channel和Pipe等资源都会被自动去注册并关闭，所以不需要重复释放资源
        if (selector != null) {
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 处理一个SelectionKey
     *
     * @param selectionKey
     * @throws IOException
     */
    private void handleKey(SelectionKey selectionKey) throws IOException {

        // 判断某个selectionKey是否有效，有可能正在处理的时候客户端把channel关闭了。
        if (selectionKey.isValid()) {
            // 处理新接入的请求消息
            if (selectionKey.isAcceptable()) {
                // Accept the new connection
                // 获取这个selectionKey对应的channel
                ServerSocketChannel ssc = (ServerSocketChannel) selectionKey.channel();
                ssc.accept();
                ssc.configureBlocking(false);
                // Add the new connection to the selector
                ssc.register(selector, SelectionKey.OP_READ);
            }
            if (selectionKey.isReadable()) {
                SocketChannel sc = (SocketChannel) selectionKey.channel();
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                int readBytes = sc.read(readBuffer);
                if (readBytes > 0) {
                    // 判断TCP包的完整性
                    readBuffer.flip();
                    byte[] bytes = new byte[readBuffer.remaining()];
                    readBuffer.get(bytes);
                    // 反序列化
                    String body = new String(bytes, "UTF-8");
                    System.out.println("The time server receive order : " + body);
                    String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new java.util.Date(System.currentTimeMillis()).toString() : "BAD ORDER";
                    doWrite(sc, currentTime);
                } else if (readBytes < 0) {
                    selectionKey.cancel();
                    sc.close();
                } else {
                    ; // 读到0字节，忽略
                }
            }
        }
    }

    /**
     * 向channel中写入数据
     *
     * @param sc
     * @param response
     */
    private void doWrite(SocketChannel sc, String response) throws IOException {

        if (response != null && response.trim().length() > 0) {
            // 序列化
            byte[] bytes = response.getBytes();
            ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
            writeBuffer.put(bytes);
            writeBuffer.flip();
            sc.write(writeBuffer);
        }
    }

    /**
     * Getter method for property <tt>selector</tt>.
     *
     * @return property value of selector
     */
    public Selector getSelector() {
        return selector;
    }

    /**
     * Setter method for property <tt>selector</tt>.
     *
     * @param selector value to be assigned to property selector
     */
    public void setSelector(Selector selector) {
        this.selector = selector;
    }

    /**
     * Getter method for property <tt>serverSocketChannel</tt>.
     *
     * @return property value of serverSocketChannel
     */
    public ServerSocketChannel getServerSocketChannel() {
        return serverSocketChannel;
    }

    /**
     * Setter method for property <tt>serverSocketChannel</tt>.
     *
     * @param serverSocketChannel value to be assigned to property serverSocketChannel
     */
    public void setServerSocketChannel(ServerSocketChannel serverSocketChannel) {
        this.serverSocketChannel = serverSocketChannel;
    }

    /**
     * Getter method for property <tt>port</tt>.
     *
     * @return property value of port
     */
    public int getPort() {
        return port;
    }

    /**
     * Setter method for property <tt>port</tt>.
     *
     * @param port value to be assigned to property port
     */
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * 主程序
     *
     * @param args
     */
    public static void main(String[] args) {

        int port = 8099;
        if (null != args && args.length > 0) {
            port = Integer.valueOf(args[0]);
        }
        MultiplexerTimeServer multiplexerTimeServer = new MultiplexerTimeServer(port);
        System.out.println("The time multiplexerTimeServer is start in port : " + port);
        multiplexerTimeServer.handleInput();
    }
}
