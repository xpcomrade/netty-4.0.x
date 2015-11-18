package com.xpcomrade.push.netty.handler.inbound;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by wangzp
 * Date: 2015/11/18 20:59
 * Copyright (c) 2015, xpcomrade@gmail.com All Rights Reserved.
 * Description: 事件分发Handler. <br/>
 */
public class DispatchInboundHandler extends SimpleChannelInboundHandler {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {

    }
}
