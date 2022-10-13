package org.djflying.bigdata.rpc.common;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * RPC请求encode
 *
 * @author daijiong
 * @version $Id: RpcEncoder.java, v 0.1 18-10-12 下午4:27 daijiong Exp $$
 */
public class RpcEncoder extends MessageToByteEncoder {

    private Class<?> generiClass;

    public RpcEncoder(Class<?> generiClass) {
        this.generiClass = generiClass;
    }

    /**
     * 将返回对象序列化
     * 
     * @param ctx
     * @param in
     * @param out
     * @throws Exception
     */
    @Override
    protected void encode(ChannelHandlerContext ctx, Object in, ByteBuf out) throws Exception {

        if (generiClass.isInstance(in)) {
            byte[] bytes = SerializationUtil.serialize(in);
            out.writeInt(bytes.length);
            out.writeBytes(bytes);
        }
    }
}
