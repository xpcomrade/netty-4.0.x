package com.xpcomrade.socket.netty.discard;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

import java.util.Date;

/**
 * Created by wangzp on 2015/11/3.
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter {

    public DiscardServerHandler() {
        super();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            ByteBuf buf = (ByteBuf)msg;
            /*byte[] result = new byte[buf.capacity()];
            buf.getBytes(0, result);
            System.out.println("Discard server received msg：" + new String(result, "utf-8")); */
            System.out.println("[" + new Date().toLocaleString() + "] Discard server received msg：" + buf.toString(CharsetUtil.UTF_8));
            System.out.flush();

            ctx.channel().writeAndFlush("hi");
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
