package org.djflying.bigdata.corejava.netty.sendobject.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.djflying.bigdata.corejava.netty.sendobject.bean.Person;

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
     * @param ctx handler上下文d
     * @param msg 客户端数据
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        Person person = (Person) msg;
        System.out.println("服务端读取数据为：" + person);
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
