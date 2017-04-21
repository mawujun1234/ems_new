package com.mawujun.mobile.gps;

import com.mawujun.mobile.gps.model.GpsMsg;
import com.mawujun.mobile.gps.model.PushMsg;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

public class NettyServerBootstrap {
	private static final NettyServerBootstrap nettyServerBootstrap = new NettyServerBootstrap();

    public static NettyServerBootstrap getInstance() {
        return nettyServerBootstrap;
    }

    public void create(int port, final OnStartListener onStartListener) {
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(boss, worker);
        bootstrap.channel(NioServerSocketChannel.class);
        bootstrap.option(ChannelOption.SO_BACKLOG, 128);
        //通过NoDelay禁用Nagle,使消息立即发出去，不用等待到一定的数据量才发出去
        bootstrap.option(ChannelOption.TCP_NODELAY, true);
        //保持长连接状态
        bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
        bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                ChannelPipeline p = socketChannel.pipeline();
                p.addLast(new ObjectEncoder());
                p.addLast(new ObjectDecoder(ClassResolvers.cacheDisabled(null)));
                p.addLast(new NettyServerHandler(onStartListener));
            }
        });
        try {
            ChannelFuture f = bootstrap.bind(port).sync();
            if (f.isSuccess()) {
                onStartListener.onSuccess();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            onStartListener.onFailure();
        }
    }

    public interface OnStartListener {

        void onSuccess();

        void onFailure();
        
        void onGpsupload(GpsMsg gpsMsg);
    }

    public void pushAll(PushMsg pushMsg) {
        for (SocketChannel channel : NettyChannelMap.getAll()) {
            if (channel != null) {
                channel.writeAndFlush(pushMsg);
            }
        }
    }

    public void push(String clientId, PushMsg pushMsg) {
        SocketChannel channel = (SocketChannel) NettyChannelMap.get(clientId);
        if (channel != null) {
            channel.writeAndFlush(pushMsg);
        }
    }
}
