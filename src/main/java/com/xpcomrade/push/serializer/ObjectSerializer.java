package com.xpcomrade.push.serializer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by wangzp
 * Date: 2015/11/18 15:48
 * Copyright (c) 2015, xpcomrade@gmail.com All Rights Reserved.
 * Description: JDK序列化和反序列化. <br/>
 */
public class ObjectSerializer implements Serializer {

    @Override
    public void init() throws Exception {

    }

    @Override
    public byte[] serialize(Object object) throws Exception {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream outputStream = null;
        try {
            outputStream = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(outputStream);
            oos.writeObject(object);

            return outputStream.toByteArray();
        } finally {
            if (oos != null) {
                oos.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }

    @Override
    public Object deserialize(byte[] bytes) throws Exception {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new ByteArrayInputStream(bytes));

            return ois.readObject();
        } finally {
            if (ois != null) {
                ois.close();
            }
        }
    }

    @Override
    public void register(Class<?> class1) {

    }
}
