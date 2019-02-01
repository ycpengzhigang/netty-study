package com.pzg.netty.study.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *  客户端
 * @author PENGZHIGANG
 * @date
 */
public class ClientStudy {
    public static final int count = 5;

    public static void main(String[] args) throws InterruptedException {

        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();

        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) {
                        // ch.pipeline()返回的是这条连接相关的逻辑处理链 采用了责任链模式
//                        ch.pipeline().addLast(new StringEncoder());
                        ch.pipeline().addLast(new FirstClientHandler());
                    }
                });

        // ctrl + alter + v 提取本地变量
//        Channel channel = bootstrap.connect("127.0.0.1", 1001).channel();
//        System.out.println(channel.attr(AttributeKey.newInstance("clientKey")));
//        while (true) {
//            channel.writeAndFlush(new Date() + ": hello world!");
//            Thread.sleep(2000);
//        }
//        bootstrap.connect("127.0.0.1", 1001).addListener(new GenericFutureListener<Future<? super Void>>() {
//            @Override
//            public void operationComplete(Future<? super Void> future) throws Exception {
//                if (future.isSuccess()) {
//                    System.out.println("连接成功");
//                } else {
//                    System.out.println("连接失败");
//                }
//            }
//        });
        connect(bootstrap,"127.0.0.1", 1000, count);

    }

//    private static void connect(final Bootstrap bootstrap, final String host,final int port){
//        bootstrap.connect(host, port).addListener(new GenericFutureListener<Future<? super Void>>() {
//            @Override
//            public void operationComplete(Future<? super Void> future) throws Exception {
//                if (future.isSuccess()) {
//                    System.out.println("连接成功");
//                } else {
//                    System.out.println("连接失败");
//                    connect(bootstrap,host,port);
//                }
//            }
//        });
//    }


    /**
     * 使用指数的方式来进行重新连接
     * @param bootstrap
     * @param host
     * @param port
     * ctrl + alter + l 将进行格式化代码
     * ctrl + alter + v 将提取本地变量
     * ctrl + F12 展示类中的所有方法
     * ctrl + alter + o 移除没有用的方法
     */
    private static void connect(final Bootstrap bootstrap, final String host, final int port, final int retry) {
        bootstrap.connect(host, port).addListener(future -> {
                if (future.isSuccess()) {
                    System.out.println("连接成功");
                } else if (retry == 0) {
                    System.out.println("重新连接的次数用完了，放弃连接");
                } else {
                    // 第几次重连
                    int order = (count - retry) + 1; // 每次加一
                    int delay = 1 << order;// 时间以1 2 4 8 16... 的方式进行递增
                    System.err.println(new Date() + ": 连接失败，第" + order + "次重连……");
                    bootstrap.config().group().schedule(() ->connect(bootstrap, host, port, retry - 1), delay, TimeUnit.SECONDS);
                }
        });
    }


}
