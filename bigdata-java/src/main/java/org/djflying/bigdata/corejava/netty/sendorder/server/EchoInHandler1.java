package org.djflying.bigdata.corejava.netty.sendorder.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 第1个InboundHandler
 *
 * @author dj4817
 * @version $Id: EchoInHandler1.java, v 0.1 2018/3/28 9:26 dj4817 Exp $$
 */
public class EchoInHandler1 extends ChannelInboundHandlerAdapter {

    /**
     * 读取channel中的数据进行处理
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        System.out.println("客户端数据流转到in1中");
        // 将channel中的数据流转给下一个inhandler执行
        ctx.fireChannelRead(msg);
    }

    /**
     * channel中的数据读取完毕
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {

        // 刷新后才将数据发出到SocketChannel
        ctx.flush();
    }

    /**
     * 读取出现异常
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        cause.printStackTrace();
        ctx.close();
    }
}
