package org.djflying.bigdata.corejava.netty.sendorder.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * InboundHandler
 *
 * @author dj4817
 * @version $Id: EchoClientHandler.java, v 0.1 2018/3/28 10:07 dj4817 Exp $$
 */
public class EchoClientHandler extends ChannelInboundHandlerAdapter {

    /**
     * channel成功建立，用于客户端发送数据
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        System.out.println("客户端连接服务器，开始发送数据……");
        // 消息
        byte[] req = "hello, I'm the client".getBytes();
        // 发送类
        ByteBuf firstMessage = Unpooled.buffer(req.length);
        // 发送
        firstMessage.writeBytes(req);
        // flush
        ctx.writeAndFlush(firstMessage);
    }

    /**
     * channel中有服务端数据之后，用于接受服务端发送的数据
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        System.out.println("客户端读取服务端数据");
        // 服务端返回消息后
        ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body = new String(req, "UTF-8");
        System.out.println("服务端数据为 :" + body);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        cause.printStackTrace();
        ctx.close();
    }
}
