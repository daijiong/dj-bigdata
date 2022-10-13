package org.djflying.bigdata.corejava.netty.sendobject.coder;

import java.util.List;

import org.djflying.bigdata.corejava.netty.sendobject.bean.Person;
import org.djflying.bigdata.corejava.netty.sendobject.utils.SerializationUtil;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

/**
 * Person对象反序列化handler
 *
 * @author daijiong
 * @version $Id: PersonDecoder.java, v 0.1 18-10-10 下午5:52 daijiong Exp $$
 */
public class PersonDecoder extends ByteToMessageDecoder {

    /**
     * Person对象反序列化方法
     *
     * @param channelHandlerContext
     * @param byteBuf
     * @param list
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {

        // 将byteBuf转变为byte数组
        byte[] bytes = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(bytes);
        // 使用SerializationUtil工具类将byte数组反序列化成为Person对象
        Person person = SerializationUtil.deserialize(bytes, Person.class);
        // 将Person添加到list中，传递到下一个handler中处理
        list.add(person);
    }
}
