package org.djflying.bigdata.corejava.netty.sendorder.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

/**
 * 第2个OutboundHandler
 *
 * @author dj4817
 * @version $Id: EchoOutHandler2.java, v 0.1 2018/3/28 9:31 dj4817 Exp $$
 */
public class EchoOutHandler2 extends ChannelOutboundHandlerAdapter {

    /**
     * 取channel中的数据进行处理
     *
     * @param ctx
     * @param msg
     * @param promise
     * @throws Exception
     */
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {

        System.out.println("服务端数据流转到out2");
        //System.out.println("当前channel中的数据为：" + msg);
        super.write(ctx, msg, promise);
    }
}
