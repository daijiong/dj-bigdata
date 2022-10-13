package org.djflying.bigdata.corejava.netty.sendorder.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * netty客户端
 *
 * @author dj4817
 * @version $Id: EchoClient.java, v 0.1 2018/3/28 10:00 dj4817 Exp $$
 */
public class EchoClient {

    /**
     * 服务器地址
     */
    private String address;
    /**
     * 服务器端口
     */
    private Integer port;

    public EchoClient(String address, Integer port) {
        this.address = address;
        this.port = port;
    }

    public void start() throws Exception {

        EventLoopGroup nioEventLoopGroup = null;
        try {
            // 客户端引导类
            Bootstrap bootstrap = new Bootstrap();
            // EventLoopGroup可以理解为是一个线程池，这个线程池用来处理连接、接受数据、发送数据
            nioEventLoopGroup = new NioEventLoopGroup();
            bootstrap.group(nioEventLoopGroup);
            // 指定通道类型为NioServerSocketChannel，一种异步模式，OIO阻塞模式为OioServerSocketChannel
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.remoteAddress(new InetSocketAddress(address, port));
            //业务处理类
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel channel)
                        throws Exception {
                    //注册handler
                    channel.pipeline().addLast(new EchoClientHandler());
                }
            });
            // 连接服务器
            ChannelFuture channelFuture = bootstrap.connect().sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            nioEventLoopGroup.shutdownGracefully().sync();
        }

    }


    /**
     * netty客户端测试
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {

        EchoClient echoClient = new EchoClient("localhost", 20000);
        echoClient.start();
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
