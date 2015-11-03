package com.xpcomrade.socket.netty.discard;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.util.Date;

/**
 * Created by wangzp on 2015/11/3.
 */
public class DiscardClientHandler extends ChannelInboundHandlerAdapter {
    private ByteBuf content;
    private ChannelHandlerContext ctx;
    static final int SIZE = Integer.parseInt(System.getProperty("size", "256"));

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf)msg;
        System.out.println("[" + new Date().toLocaleString() + "] Discard client received msg：" + buf.toString(CharsetUtil.UTF_8));
        System.out.flush();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        this.ctx = ctx;
        content = ctx.alloc().directBuffer(SIZE).writeZero(SIZE);

        generateTraffic();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        content.release();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    private void generateTraffic() {
        ctx.writeAndFlush(content.duplicate().retain()).addListener(trafficGenerator);
    }

    private final ChannelFutureListener trafficGenerator = new ChannelFutureListener() {
        @Override
        public void operationComplete(ChannelFuture future) throws Exception {
            if (future.isSuccess()) {
                generateTraffic();
            } else {
                future.cause().printStackTrace();
                future.channel().close();
            }
        }
    };
}
