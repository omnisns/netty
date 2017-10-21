package com.sycnhea.netty;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

/**
 * Created by TYRANT on 2017/9/26.
 */
public   class ServerChannelHandlerAdapter extends ChannelInboundHandlerAdapter{
    /**
     * 接收和发送消息
     * @param ctx
     * @param msg 接收到的数据
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("channelRead:"+msg);
        ChannelFuture future = ctx.writeAndFlush(new Date());
        //关闭和客户端的通信
//        future.addListener(ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE);
//        future.addListener(ChannelFutureListener.CLOSE);

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
