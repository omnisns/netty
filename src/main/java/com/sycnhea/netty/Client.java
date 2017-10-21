package com.sycnhea.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created by TYRANT on 2017/9/26.
 */
public class Client {
    public static void main(String[] args) throws  Exception{

        //创建bootstrap
        Bootstrap bootstrap = new Bootstrap();
        //创建线程EventLoopGroup boss 请求work IO处理
        EventLoopGroup work = new NioEventLoopGroup();
        //关联线程池
        bootstrap.group(work);
        //设置通道
        bootstrap.channel(NioSocketChannel.class);
        //初始化通道
        bootstrap.handler(new ClientChannelInitializer());
        //绑定监听并启动
        ChannelFuture channelFuture = bootstrap.connect("192.168.0.235",10000).sync();
        //等待服务关闭
        channelFuture.channel().closeFuture().sync();
        //关闭线程资源
        work.shutdownGracefully();

    }


}
