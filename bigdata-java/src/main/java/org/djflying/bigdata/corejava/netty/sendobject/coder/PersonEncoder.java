package org.djflying.bigdata.corejava.netty.sendobject.coder;

import org.djflying.bigdata.corejava.netty.sendobject.bean.Person;
import org.djflying.bigdata.corejava.netty.sendobject.utils.SerializationUtil;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Person对象序列化handler
 *
 * @author dj4817
 * @version $Id: PersonEncoder.java, v 0.1 2018/3/28 13:47 dj4817 Exp $$
 */
public class PersonEncoder extends MessageToByteEncoder<Person> {

    /**
     * Person对象序列化方法
     *
     * @param ctx handler上下文
     * @param person                待序列化对象
     * @param byteBuf
     * @throws Exception
     */
    @Override
    protected void encode(ChannelHandlerContext ctx, Person person, ByteBuf byteBuf) throws Exception {

        // 将person对象序列化成字节数组
        byte[] bytes = SerializationUtil.serialize(person);
        // 将字节数组写入ByteBuf中
        byteBuf.writeBytes(bytes);
        ctx.flush();
    }
}
