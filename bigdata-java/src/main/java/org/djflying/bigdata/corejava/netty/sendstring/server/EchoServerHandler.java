package org.djflying.bigdata.corejava.netty.sendstring.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

/**
 * netty服务器业务处理类
 *
 * @author dj4817
 * @version $Id: EchoServerHandler.java, v 0.1 2017/12/9 20:11 dj4817 Exp $$
 */
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 读取channel中的客户端数据并处理
     *
     * @param ctx handler上下文
     * @param msg 客户端数据
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        System.out.println("服务端读取数据……");
        // 读取数据
        ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body = new String(req, "UTF-8");
        System.out.println("接收客户端数据:" + body);
        //向客户端写数据
        System.out.println("服务端向客户端发送数据");
        String currentTime = new Date(System.currentTimeMillis()).toString();
        ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
        ctx.write(resp);
    }

    /**
     * 处理完成之后
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {

        System.out.println("服务端读取数据完毕");
        // 刷新后才将数据发出到SocketChannel
        ctx.flush();
    }

    /**
     * 发生异常
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
