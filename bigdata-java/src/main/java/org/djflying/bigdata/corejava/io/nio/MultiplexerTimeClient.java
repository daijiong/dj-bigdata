package org.djflying.bigdata.corejava.io.nio;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * nio客户端程序
 *
 * @author dj4817
 * @version $Id: MultiplexerTimeClient.java, v 0.1 2017/12/6 10:28 dj4817 Exp $$
 */
public class MultiplexerTimeClient {

    private String           host;
    private int              port;
    private Selector         selector;
    private SocketChannel    socketChannel;
    private volatile boolean stop;

    /**
     * 无参构造器
     */
    public MultiplexerTimeClient() {
    }

    /**
     * 带参构造器
     *
     * @param host
     * @param port
     */
    public MultiplexerTimeClient(String host, int port) {

        this.host = StringUtils.isBlank(host) ? "localhost" : host;
        this.port = port;
        try {
            // 打开一个监听器
            selector = Selector.open();
            // 打开一个socket channel
            socketChannel = SocketChannel.open();
            // channel配置非阻塞
            socketChannel.configureBlocking(false);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * 处理请求
     *
     */
    public void handleInput() {

        while (!stop) {
            try {
                selector.select(1000);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    iterator.remove();
                    handleKey(selectionKey);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 处理Key
     *
     * @param selectionKey
     */
    private void handleKey(SelectionKey selectionKey) {

        try {
            if (selectionKey.isValid()) {
                SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                if (selectionKey.isConnectable()) {
                    if (socketChannel.finishConnect()) {
                        socketChannel.register(selector, SelectionKey.OP_READ);
                        doWrite(socketChannel);
                    } else {
                        System.exit(1);
                    }
                }
                if (selectionKey.isReadable()) {
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    int readBytes = socketChannel.read(byteBuffer);
                    if (readBytes > 0) {
                        byteBuffer.flip();
                        byte[] bytes = new byte[byteBuffer.remaining()];
                        byteBuffer.get(bytes);
                        String body = new String(bytes, "UTF-8");
                        System.out.println("Now is : " + body);
                        this.stop = true;
                    } else if (readBytes < 0) {
                        // 对端链路关闭
                        selectionKey.cancel();
                        socketChannel.close();
                    } else {
                        ; // 读到0字节，忽略
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 写数据到channel中
     *
     * @param socketChannel
     */
    private void doWrite(SocketChannel socketChannel) {

        try {
            byte[] req = "QUERY TIME ORDER".getBytes();
            ByteBuffer writeBuffer = ByteBuffer.allocate(req.length);
            writeBuffer.put(req);
            writeBuffer.flip();
            socketChannel.write(writeBuffer);
            if (!writeBuffer.hasRemaining()) {
                System.out.println("Send order 2 server succeed.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Getter method for property <tt>host</tt>.
     *
     * @return property value of host
     */
    public String getHost() {
        return host;
    }

    /**
     * Setter method for property <tt>host</tt>.
     *
     * @param host value to be assigned to property host
     */
    public void setHost(String host) {
        this.host = host;
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
     * Getter method for property <tt>socketChannel</tt>.
     *
     * @return property value of socketChannel
     */
    public SocketChannel getSocketChannel() {
        return socketChannel;
    }

    /**
     * Setter method for property <tt>socketChannel</tt>.
     *
     * @param socketChannel value to be assigned to property socketChannel
     */
    public void setSocketChannel(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
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
        MultiplexerTimeClient multiplexerTimeClient = new MultiplexerTimeClient("localhost", port);
        System.out.println("The time multiplexerTimeClient is start in port : " + port);
        multiplexerTimeClient.handleInput();

    }
}
