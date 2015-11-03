package com.xpcomrade.socket.netty.discard;

import com.xpcomrade.socket.Constant;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created by wangzp on 2015/11/3.
 */
public class DiscardClient {

    public static void main(String[] args) throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap client = new Bootstrap();
            client
                    .group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new DiscardClientHandler());
                        }
                    });
            ChannelFuture future = client.connect(Constant.HOST, Constant.PORT).sync();
            future.channel().closeFuture().sync();
        } finally {
           group.shutdownGracefully();
        }

    }
}
