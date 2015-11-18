package com.xpcomrade.push.netty.service;

import io.netty.channel.ChannelHandler;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by wangzp
 * Date: 2015/11/18 20:18
 * Copyright (c) 2015, xpcomrade@gmail.com All Rights Reserved.
 * Description: 服务抽象实现. <br/>
 */
public abstract class AbstractService implements Service {
    /**
     * @Fields handlers : 存放ChannelHandler列表
     */
    private LinkedHashMap<String, ChannelHandler> handlers = new LinkedHashMap<String, ChannelHandler>();
    /**
     * @Fields options : 存放Option列表
     */
    private Map<String, Object> options = new HashMap<String, Object>();

    @Override
    public Map<String, Object> getOptions() {
        return options;
    }

    @Override
    public Object getOption(String opName) {
        return options.get(opName);
    }

    @Override
    public void setOptions(Map<String, Object> options) {
        if (options == null) {
            return;
        }

        for (Map.Entry<String, Object> entry : options.entrySet()){
            setOption(entry.getKey(), entry.getValue());
        }

    }

    @Override
    public void setOption(String opName, Object opValue) {
        if (this.options == null) {
            this.options = new LinkedHashMap<String, Object>();
        }
        this.options.put(opName, opValue);
    }

    @Override
    public LinkedHashMap<String, ChannelHandler> getHandlers() {
        return handlers;
    }

    @Override
    public void setHandlers(LinkedHashMap<String, ChannelHandler> handlers) {
        this.handlers = handlers;
    }
}
