package org.djflying.bigdata.corejava.netty.sendorder.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

/**
 * 第1个OutboundHandler
 *
 * @author dj4817
 * @version $Id: EchoOutHandler1.java, v 0.1 2018/3/28 9:30 dj4817 Exp $$
 */
public class EchoOutHandler1 extends ChannelOutboundHandlerAdapter {

    /**
     * 读取channel中的数据进行处理
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {

        System.out.println("服务端数据流转到out1");
        //System.out.println("当前channel中的数据为：" + msg);
        ByteBuf resp = Unpooled.copiedBuffer("hello, I'm the server".getBytes());
        // 将数据写入channel中并流转到下一个outhandler
        ctx.write(resp);
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
