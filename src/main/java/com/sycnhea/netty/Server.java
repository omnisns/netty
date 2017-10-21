package com.sycnhea.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by TYRANT on 2017/9/26.
 */
public class Server {
    public static void main(String[] args) throws  Exception {
        //1.创建ServerBootstrap
        ServerBootstrap sbt=new ServerBootstrap();
        //2.创建线程 EventLoopGroup boss 请求转发  work IO处理
        EventLoopGroup boss=new NioEventLoopGroup();
        EventLoopGroup work=new NioEventLoopGroup();
        //3.关联线程池 处理 网路事件
        sbt.group(boss,work);
        //4.设置通道NioServerSocketChannel
        sbt.channel(NioServerSocketChannel.class);
        //5.初始化通信管道 *
        sbt.childHandler(new ServerChannelInitializer());
        //6.绑定监听端口并启动服务。
        System.out.println("我在10000监听...");
        ChannelFuture future = sbt.bind(10000).sync();
        //7.等待服务关闭
        future.channel().closeFuture().sync();
        //8.关闭线程池资源
        boss.shutdownGracefully();
        work.shutdownGracefully();

    }


}
