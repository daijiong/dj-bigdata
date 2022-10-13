package org.djflying.bigdata.corejava.netty.sendorder.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;


/**
 * 第2个InboundHandler
 *
 * @author dj4817
 * @version $Id: EchoInHandler2.java, v 0.1 2018/3/28 9:30 dj4817 Exp $$
 */
public class EchoInHandler2 extends ChannelInboundHandlerAdapter {

    /**
     * 读取channel中的数据进行处理
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        System.out.println("客户端数据流转到in2中");
        // 读取客户端数据
        ByteBuf byteBuf = (ByteBuf) msg;
        byte[] req = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(req);
        String body = new String(req, "UTF-8");
        System.out.println("服务端读取客户端数据:" + body);
        // 向客户端写数据
        System.out.println("服务端向客户端发送数据");
        ByteBuf resp = Unpooled.copiedBuffer("hello, I'm the server".getBytes());
        // 将数据写入channel中并流转到下一个outhandler
        ctx.write(resp);
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
