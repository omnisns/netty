package com.sycnhea.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;

/**
 * Created by TYRANT on 2017/9/26.
 */
public class ClientChannelInitializer extends ChannelInitializer<SocketChannel> {
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new LengthFieldBasedFrameDecoder(65535,0,2,0,2));//数据帧解码
        pipeline.addLast(new CustomObjectDecoder());//解码器
        pipeline.addLast(new LengthFieldPrepender(2));//数据帧编码
        pipeline.addLast(new CustomObjectEncoder());//编码器
        //消息最终处理者
        pipeline.addLast(new ClientChannelHanderAdapter());

    }





}
