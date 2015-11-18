package com.xpcomrade.push.netty.service;

import io.netty.channel.ChannelFuture;

import java.net.SocketAddress;
import java.util.List;

/**
 * Created by wangzp
 * Date: 2015/11/18 17:49
 * Copyright (c) 2015, xpcomrade@gmail.com All Rights Reserved.
 * Description: 服务器端服务接口. <br/>
 */
public interface Acceptor extends Service {

    /**
     *  监听指定端口
     * @param portArray 端口数组
     * @return
     */
    List<ChannelFuture> bind(int... portArray);

    /**
     *  监听指定SocketAddress
     * @param addressArray SocketAddress数组
     * @return
     */
    List<ChannelFuture> bind(SocketAddress... addressArray);

}
