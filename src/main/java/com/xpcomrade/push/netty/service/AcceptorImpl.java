package com.xpcomrade.push.netty.service;

import com.xpcomrade.socket.netty.time.TimeServerHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by wangzp
 * Date: 2015/11/18 17:52
 * Copyright (c) 2015, xpcomrade@gmail.com All Rights Reserved.
 * Description: Acceptor实现类. <br/>
 */
public class AcceptorImpl implements Acceptor {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private ServerBootstrap bootstrap;
    private AtomicBoolean alive = new AtomicBoolean(false);

    @Override
    public List<ChannelFuture> bind(SocketAddress... addressArray){
        if (addressArray == null || addressArray.length == 0) {
            throw new IllegalArgumentException("addressArray can not be null or empty.");
        }

        bootstrap
                .channel(NioServerSocketChannel.class)
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                    }
                })
                .option(ChannelOption.SO_BACKLOG, 128);

        List<ChannelFuture> retChannels = new ArrayList<ChannelFuture>();
        for(SocketAddress address : addressArray) {
            retChannels.add(bootstrap.bind(address));

            logger.warn("Server started, listen at [{}]", address);
        }

        alive.set(true);

        return retChannels;
    }

    @Override
    public List<ChannelFuture> bind(int... portArray){
        if(portArray == null || portArray.length == 0) {
            throw new IllegalArgumentException("portArray can not be null or empty.");
        }
        SocketAddress[] addressArray = new SocketAddress[portArray.length];
        for (int i = 0; i < portArray.length; i++) {
            addressArray[i] = new InetSocketAddress(portArray[i]);
        }

        return bind(addressArray);
    }
}
