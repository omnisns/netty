package com.sycnhea.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2017/9/26.
 */
public class CustomObjectEncoder extends MessageToMessageEncoder<Object> {
    /**
     *
     * @param ctx
     * @param msg :即将要编码的对象
     * @param out ：任意一个对象  每一个元素代表一帧数据
     * @throws Exception
     */
    protected void encode(ChannelHandlerContext ctx, Object msg, List<Object> out) throws Exception {
        System.out.println("编码...");
        byte[] bytes = SerializationUtils.serialize((Serializable) msg);
        ByteBuf buffer= Unpooled.buffer();
        buffer.writeBytes(bytes);
        out.add(buffer);

    }
}
