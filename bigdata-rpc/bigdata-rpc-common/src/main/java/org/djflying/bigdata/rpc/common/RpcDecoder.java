package org.djflying.bigdata.rpc.common;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * RPC请求decode
 *
 * @author dj4817
 * @version $Id: RpcDecoder.java, v 0.1 2018/3/27 16:19 dj4817 Exp $$
 */
public class RpcDecoder extends ByteToMessageDecoder {

    /** 待解码的对象的类型 */
    private Class<?> genericClass;

    public RpcDecoder(Class<?> genericClass) {
        this.genericClass = genericClass;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        // 小于4个字节表示没有内容
        if (in.readableBytes() < 4) {
            return;
        }
        in.markReaderIndex();
        int dataLength = in.readInt();
        if (dataLength < 0) {
            ctx.close();
        }
        // 处理TCP包不全的情况
        if (in.readableBytes() < dataLength) {
            in.resetReaderIndex();
        }
        //将ByteBuf转换为byte[]
        byte[] data = new byte[dataLength];
        in.readBytes(data);
        //将byte[]转换成object
        Object obj = SerializationUtil.deserialize(data, genericClass);
        out.add(obj);
    }
}
