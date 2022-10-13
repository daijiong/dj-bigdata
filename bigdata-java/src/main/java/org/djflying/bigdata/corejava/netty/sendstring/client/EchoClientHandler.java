package org.djflying.bigdata.corejava.netty.sendstring.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * netty客户端业务处理类
 *
 * @author dj4817
 * @version $Id: EchoClientHandler.java, v 0.1 2017/12/9 18:46 dj4817 Exp $$
 */
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    /**
     * channel成功建立，用于客户端发送数据
     *
     * @param channelHandlerContext handler的上下文，用于接收每个handler处理的数据
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext channelHandlerContext) throws Exception {

        System.out.println("客户端连接服务器，开始发送数据……");
        // 消息
        byte[] req = "QUERY TIME ORDER".getBytes();
        // 发送类
        ByteBuf firstMessage = Unpooled.buffer(req.length);
        // 发送
        firstMessage.writeBytes(req);
        // flush
        channelHandlerContext.writeAndFlush(firstMessage);
    }

    /**
     * 发生异常之后
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        System.out.println("client exceptionCaught..");
        // 释放资源
        ctx.close();
    }

    /**
     * channel中有服务端数据之后，用于接受服务端发送的数据
     *
     * @param channelHandlerContext handler的上下文，用于接收每个handler处理的数据
     * @param byteBuf 服务端发送过来的2进制数据
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {

        System.out.println("客户端读取服务端数据……");
        // 服务端返回消息后
        byte[] req = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(req);
        String body = new String(req, "UTF-8");
        System.out.println("服务端数据为 :" + body);
        System.out.println("客户端读取服务端数据完毕");

    }
}
