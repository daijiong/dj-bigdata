package org.djflying.bigdata.corejava.netty.sendstring.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * netty服务器
 *
 * @author dj4817
 * @version $Id: EchoServer.java, v 0.1 2017/12/9 20:11 dj4817 Exp $$
 */
public class EchoServer {

    private final String host;
    private final int    port;

    /**
     * 全参构造器
     *
     * @param host
     * @param port
     */
    public EchoServer(String host, int port) {
        this.host = host;
        this.port = port;
    }

    /**
     * netty服务端启动方法
     */
    public void start() throws Exception {

        EventLoopGroup eventLoopGroup = null;
        try {
            // server端引导类
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            // 连接池处理数据
            eventLoopGroup = new NioEventLoopGroup();
            // 装配bootstrap
            serverBootstrap.group(eventLoopGroup);
            // 指定通道类型为NioServerSocketChannel，一种异步模式，OIO阻塞模式为OioServerSocketChannel
            serverBootstrap.channel(NioServerSocketChannel.class);
            //设置InetSocketAddress让服务器监听某个端口已等待客户端连接。
            serverBootstrap.localAddress(host, port);
            // 设置childHandler执行所有的连接请求
            serverBootstrap.childHandler(new ChannelInitializer<Channel>() {
                @Override
                protected void initChannel(Channel channel) throws Exception {
                    // 注册handler
                    channel.pipeline().addLast(new EchoServerHandler());
                }
            });
            // 最后绑定服务器等待直到绑定完成，调用sync()方法会阻塞直到服务器完成绑定,然后服务器等待通道关闭，因为使用sync()，所以关闭操作也会被阻塞。
            ChannelFuture channelFuture = serverBootstrap.bind().sync();
            System.out.println("开始监听，端口为：" + channelFuture.channel().localAddress());
            channelFuture.channel().closeFuture().sync();
        } finally {
            eventLoopGroup.shutdownGracefully().sync();
        }
    }

    /**
     * 主程序
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {

        EchoServer server = new EchoServer("localhost", 20000);
        server.start();
    }
}
