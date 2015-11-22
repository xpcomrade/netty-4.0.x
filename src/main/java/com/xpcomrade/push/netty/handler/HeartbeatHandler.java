package com.xpcomrade.push.netty.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * Created by wangzp
 * Date: 2015/11/18 20:59
 * Copyright (c) 2015, xpcomrade@gmail.com All Rights Reserved.
 * Description: 心跳检测. <br/>
 */
public class HeartbeatHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (IdleStateEvent.class.isAssignableFrom(evt.getClass())) {
            IdleStateEvent event = (IdleStateEvent)evt;
            if (event.state() == IdleState.READER_IDLE) {

            } else if (event.state() == IdleState.WRITER_IDLE) {

            } else if (event.state() == IdleState.ALL_IDLE) {

            }
        }
    }
}
