package com.xpcomrade.push.netty.service;

import io.netty.channel.ChannelHandler;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by wangzp
 * Date: 2015/11/18 17:48
 * Copyright (c) 2015, xpcomrade@gmail.com All Rights Reserved.
 * Description: TODO(这里用一句话描述这个类的作用). <br/>
 */
public interface Service {

    /**
     *  获取Options选项设置
     * @return
     */
    Map<String, Object> getOptions();

    /**
     * 根据key获取Options选项设置
     * @param opName
     * @return
     */
    Object getOption(String opName);

    /**
     * 设置Options选项
     * @param options 选项参数
     */
    void setOptions(Map<String, Object> options);

    /**
     *  设置Options选项
     * @param opName  key
     * @param opValue value
     */
    void setOption(String opName, Object opValue);

    /**
     * 获取自定义Handler
     * @return
     */
    LinkedHashMap<String, ChannelHandler> getHandlers();

    /**
     * 设置自定义Handler
     * @param handlers Handler集合
     */
    void setHandlers(LinkedHashMap<String, ChannelHandler> handlers);
}
