//package org.djflying.bigdata.rpc.client;
//
//import org.djflying.bigdata.rpc.common.RpcResponse;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import io.netty.channel.ChannelHandlerContext;
//import io.netty.channel.SimpleChannelInboundHandler;
//
///**
// * Rpc客户端处理器
// *
// * @author daijiong
// * @version $Id: RpcClientHandler.java, v 0.1 18-10-16 下午5:19 daijiong Exp $$
// */
//public class RpcClientHandler extends SimpleChannelInboundHandler<RpcResponse> {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(RpcClientHandler.class);
//
//    /** 待解码的对象的类型 */
//    private Class<?> genericClass;
//
//    public RpcClientHandler(Class<?> genericClass) {
//        this.genericClass = genericClass;
//    }
//
//    @Override
//    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RpcResponse rpcResponse) throws Exception {
//        this.rpcResponse = rpcResponse;
//
//        synchronized (obj) {
//            obj.notifyAll();
//        }
//    }
//
//    @Override
//    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//        LOGGER.error("client caught exception", cause);
//        ctx.close();
//    }
//}
