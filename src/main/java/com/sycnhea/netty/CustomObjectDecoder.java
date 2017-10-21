package com.sycnhea.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.MessageToMessageEncoder;
import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2017/9/26.
 */
public class CustomObjectDecoder extends MessageToMessageDecoder<ByteBuf> {
    /**
     *
     * @param ctx
     * @param msg :ByteBuf
     * @param out :每一个元素 输出的一帧数据
     * @throws Exception
     */
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        System.out.println("解码...");
        byte[] bytes=new byte[msg.readableBytes()];
        msg.readBytes(bytes);//将buffer的内容读入到byte数组里
        Object o = SerializationUtils.deserialize(bytes);
        out.add(o);
    }
}
