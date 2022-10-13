package org.djflying.bigdata.corejava.netty.sendobject.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.djflying.bigdata.corejava.netty.sendobject.coder.PersonEncoder;

import java.net.InetSocketAddress;

/**
 * netty客户端
 *
 * @author dj4817
 * @version $Id: EchoClient.java, v 0.1 2017/12/9 10:46 dj4817 Exp $$
 */
public class EchoClient {

    private final String host;
    private final int    port;

    /**
     * 全参构造器
     *
     * @param host
     * @param port
     */
    public EchoClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    /**
     * netty客户端启动方法
     *
     * @throws Exception
     */
    public void start() throws Exception {

        EventLoopGroup nioEventLoopGroup = null;
        try {
            // 客户端引导类
            Bootstrap bootstrap = new Bootstrap();
            // EventLoopGroup可以理解为是一个线程池，这个线程池用来处理连接、接受数据、发送数据
            nioEventLoopGroup = new NioEventLoopGroup();
            // 多线程处理
            bootstrap.group(nioEventLoopGroup);
            // 指定通道类型为NioServerSocketChannel，一种异步模式，OIO阻塞模式为OioServerSocketChannel
            bootstrap.channel(NioSocketChannel.class);
            // 服务器地址和端口
            bootstrap.remoteAddress(new InetSocketAddress(host, port));
            // 业务处理类
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    // 注册outhandler
                    socketChannel.pipeline().addLast(new PersonEncoder());
                    // 注册inhandler
                    socketChannel.pipeline().addLast(new EchoClientHandler());
                }
            });
            // 连接服务器，一直到等到chanel中有数据，并且handler处理完成
            ChannelFuture channelFuture = bootstrap.connect().sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            // 关闭nioEventLoopGroup
            nioEventLoopGroup.shutdownGracefully().sync();
        }
    }

    /**
     * 主程序
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {

        EchoClient client = new EchoClient("localhost", 20000);
        client.start();
    }
}
