package com.pzg.netty.study.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/**
 *  服务端
 * @author PENGZHIGANG
 * @date
 */
public class ServiceStudy {
//    public static void main(String[] args) {
//       final ServerBootstrap serverBootstrap = new ServerBootstrap();
//
//        // 主要负责创建新连接
//        NioEventLoopGroup boss = new NioEventLoopGroup();
//        // 主要负责读取数据
//        NioEventLoopGroup worker = new NioEventLoopGroup();
//        serverBootstrap
//                .group(boss, worker)
//                .channel(NioServerSocketChannel.class)
                // handler()用于指定在服务端启动过程中的一些逻辑
//                .handler(new ChannelInitializer<NioSocketChannel>() {
//                    @Override
//                    protected void initChannel(NioSocketChannel channel) throws Exception {
//                        System.out.println("服务端启动中");
//                    }
//                })

                // 用于指定连接数据的处理逻辑
//                .childHandler(new ChannelInitializer<NioSocketChannel>() {
//                    protected void initChannel(NioSocketChannel ch) {
//                        ch.pipeline().addLast(new StringDecoder());
//                        ch.pipeline().addLast(new SimpleChannelInboundHandler<String>() {
//                            @Override
//                            protected void channelRead0(ChannelHandlerContext ctx, String msg) {
//                                System.out.println(msg);
//                            }
//                        });
//                    }
//                });
        // 这个方法是一个异步方法 直到从这个端口绑定成功
//        serverBootstrap.bind(8088).addListener(new GenericFutureListener<Future<? super Void>>() {
//            @Override
//            public void operationComplete(Future<? super Void> future) throws Exception {
//                if (future.isSuccess()) {
//                    System.out.println("端口绑定成功");
//                } else {
//                    System.out.println("端口绑定失败");
//                }
//            }
//        });
        // 给channel设置一些属性
//        serverBootstrap.attr(AttributeKey.newInstance("serverName"), "nettyServer");
        // 给每条连接自定义一些属性 channel.attr()可以取出
//        serverBootstrap.childAttr(AttributeKey.newInstance("clientKey"), "clientValue");
        // 设置一些TCP底层属性 设置心跳机制
//        serverBootstrap.childOption(ChannelOption.SO_KEEPALIVE,true);

//        bind(serverBootstrap,1000);
//    }

    // 自动绑定递增端口
    private static void bind(final ServerBootstrap serverBootstrap, final int port){
            // 返回的是 ChannelFuture
            serverBootstrap.bind(port).addListener(new GenericFutureListener<Future<? super Void>>() {
                @Override
                public void operationComplete(Future<? super Void> future) throws Exception {
                    if (future.isSuccess()) {
                        System.out.println("端口绑定成功 port:" + port);
                    } else {
                        System.out.println("端口绑定失败");
                        bind(serverBootstrap, port + 1);
                    }
                }
            });
    }

    public static void main(String[] args) {
        ServerBootstrap bootstrap = new ServerBootstrap();


        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();

        bootstrap.group(boss, worker).channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<NioSocketChannel>() {
            protected void initChannel(NioSocketChannel ch) {
                ch.pipeline().addLast(new FirstServerHandler());
            }
        });

        bind(bootstrap,1000);

    }


}

