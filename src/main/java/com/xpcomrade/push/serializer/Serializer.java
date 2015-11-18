package com.xpcomrade.push.serializer;

/**
 * Created by wangzp
 * Date: 2015/11/18 15:48
 * Copyright (c) 2015, xpcomrade@gmail.com All Rights Reserved.
 * Description: 序列化接口. <br/>
 */
public interface Serializer {

    /**
     * 初始化
     * @throws Exception
     */
    public void init() throws Exception;

    /**
     * 序列化
     * @param object
     * @return
     * @throws Exception
     */
    public byte[] serialize(Object object) throws Exception;

    /**
     * 反序列化
     * @param bytes
     * @return
     * @throws Exception
     */
    public Object deserialize(byte[] bytes) throws Exception;

    /**
     * 注册类型
     * @param class1
     */
    public void register(Class<?> class1);
}
