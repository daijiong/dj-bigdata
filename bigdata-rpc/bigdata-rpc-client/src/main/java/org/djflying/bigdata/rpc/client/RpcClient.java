//package org.djflying.bigdata.rpc.client;
//
//import org.djflying.bigdata.rpc.common.RpcDecoder;
//import org.djflying.bigdata.rpc.common.RpcEncoder;
//import org.djflying.bigdata.rpc.common.RpcRequest;
//import org.djflying.bigdata.rpc.common.RpcResponse;
//
//import io.netty.bootstrap.Bootstrap;
//import io.netty.channel.ChannelFuture;
//import io.netty.channel.ChannelInitializer;
//import io.netty.channel.ChannelOption;
//import io.netty.channel.EventLoopGroup;
//import io.netty.channel.nio.NioEventLoopGroup;
//import io.netty.channel.socket.SocketChannel;
//import io.netty.channel.socket.nio.NioSocketChannel;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
///**
// * rpc客户端
// *
// * @author daijiong
// * @version $Id: RpcClient.java, v 0.1 18-10-16 下午5:01 daijiong Exp $$
// */
//public class RpcClient {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(RpcClient.class);
//
//    /** 服务器主机名称 */
//    private String       hostName;
//    /** 服务器端口 */
//    private Integer      port;
//
//    private final Object obj = new Object();
//
//    private RpcResponse  response;
//
//    public RpcClient() {
//    }
//
//    public RpcClient(String hostName, Integer port) {
//        this.hostName = hostName;
//        this.port = port;
//    }
//
//    /**
//     * 向服务器发送请求
//     *
//     * @param request
//     * @return
//     */
//    public RpcResponse send(RpcRequest request) throws Exception {
//
//        EventLoopGroup group = new NioEventLoopGroup();
//        try {
//            Bootstrap bootstrap = new Bootstrap();
//            bootstrap.group(group);
//            bootstrap.channel(NioSocketChannel.class);
//            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
//                @Override
//                protected void initChannel(SocketChannel socketChannel) throws Exception {
//                    // 向pipeline中添加编码、解码、业务处理的handler
//                    //OUT - 1
//                    socketChannel.pipeline().addLast(new RpcEncoder(RpcRequest.class));
//                    //IN - 1
//                    socketChannel.pipeline().addLast(new RpcDecoder(RpcResponse.class));
//                    //IN - 2
//                    socketChannel.pipeline().addLast(new RpcClientHandler(RpcResponse.class));
//                }
//            });
//            bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
//
//            // 链接服务器
//            ChannelFuture future = bootstrap.connect(hostName, port).sync();
//            //将request对象写入outbundle处理后发出（即RpcEncoder编码器）
//            future.channel().writeAndFlush(request).sync();
//
//            // 用线程等待的方式决定是否关闭连接
//            // 其意义是：先在此阻塞，等待获取到服务端的返回后，被唤醒，从而关闭网络连接
//            synchronized (obj) {
//                obj.wait();
//            }
//            if (response != null) {
//                future.channel().closeFuture().sync();
//            }
//            return response;
//        } finally {
//            group.shutdownGracefully();
//        }
//    }
//}
