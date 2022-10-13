package org.djflying.bigdata.corejava.netty.sendorder.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * netty服务端程序
 *
 * @author dj4817
 * @version $Id: EchoServer.java, v 0.1 2018/3/27 18:00 dj4817 Exp $$
 */
public class EchoServer {

    /** 服务器地址 */
    private String  address;
    /** 端口 */
    private Integer port;

    public EchoServer(String address, Integer port) {
        this.address = address;
        this.port = port;
    }

    /**
     * 启动服务器
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
            serverBootstrap.localAddress(address, port);
            //设置childHandler执行所有的连接请求
            serverBootstrap.childHandler(new ChannelInitializer<Channel>() {
                @Override
                protected void initChannel(Channel channel) throws Exception {
                    // 注册两个InboundHandler，执行顺序为注册顺序，所以应该是InboundHandler1 InboundHandler2
                    // 注册两个OutboundHandler，执行顺序为注册顺序的逆序，所以应该是OutboundHandler2 OutboundHandler1
                    // 注意：OutHandler不能再最后，不然会不生效
                    channel.pipeline().addLast(new EchoInHandler1());
                    channel.pipeline().addLast(new EchoOutHandler1());
                    channel.pipeline().addLast(new EchoOutHandler2());
                    channel.pipeline().addLast(new EchoInHandler2());
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
     * netty服务端测试
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        EchoServer server = new EchoServer("localhost", 20000);
        server.start();

    }

    /**
     * Getter method for property <tt>address</tt>.
     *
     * @return property value of address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Setter method for property <tt>address</tt>.
     *
     * @param address value to be assigned to property address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Getter method for property <tt>port</tt>.
     *
     * @return property value of port
     */
    public Integer getPort() {
        return port;
    }

    /**
     * Setter method for property <tt>port</tt>.
     *
     * @param port value to be assigned to property port
     */
    public void setPort(Integer port) {
        this.port = port;
    }
}
