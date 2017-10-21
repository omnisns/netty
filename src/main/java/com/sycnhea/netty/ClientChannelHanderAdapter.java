package com.sycnhea.netty;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

/**
 * Created by TYRANT on 2017/9/26.
 */
public class ClientChannelHanderAdapter  extends ChannelInboundHandlerAdapter {
    /**
     * 发送消息
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
       /* String msg = ":你哦阿斯顿撒";

        ByteBuf byteBuf= Unpooled.buffer();
        byteBuf.writeBytes(msg.getBytes());*/
        ChannelFuture channelFuture = ctx.writeAndFlush(new Date().toLocaleString());
//        ChannelFuture future=null;
//        for(int i=0;i<100;i++){
//            future = ctx.writeAndFlush(new Date());
//        }
        channelFuture.addListener(ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE);
        channelFuture.addListener(ChannelFutureListener.CLOSE_ON_FAILURE);


    }

    /***
     * 接收消息
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //ByteBuf byteBuf= (ByteBuf) msg;
        System.out.println("客户端收到"+msg);
    }

    /**
     * 处理异常
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.err.println("错误："+cause.getMessage());
    }


}
