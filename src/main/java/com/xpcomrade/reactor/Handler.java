package com.xpcomrade.reactor;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * Created by wangzp
 * Date: 2015/11/23 16:53
 * Copyright (c) 2015, xpcomrade@gmail.com All Rights Reserved.
 * Description: TODO(这里用一句话描述这个类的作用). <br/>
 */
public class Handler implements Runnable {

    final SocketChannel socketChannel;
    final SelectionKey selectionKey;
    ByteBuffer input = ByteBuffer.allocate(1024);
    String clientName = "";

    public Handler(Selector selector, SocketChannel channel) throws IOException{
        socketChannel = channel;
        socketChannel.configureBlocking(false);
        selectionKey = socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
        selectionKey.attach(this);
        //selectionKey.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
        selector.wakeup();
    }

    @Override
    public void run() {
        try {
            if (selectionKey.isReadable()) {
                handler();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void handler() throws IOException{
        if (read()) {
            send();
        };
    }

    boolean read() throws IOException{
        int readCount = socketChannel.read(input);
        if (readCount > 0) {
            input.flip();
            byte[] bytes = new byte[readCount];
            System.arraycopy(input.array(), 0 , bytes, 0, readCount);
            StringBuilder sb = new StringBuilder(new String(bytes));
            input.clear();
            clientName = sb.toString().toLowerCase();
            System.out.println(String.format("client say ：%s", clientName));

            return true;
        }
        return false;
    }

    void send() throws IOException{
        ByteBuffer out = ByteBuffer.wrap(("hi, " + clientName + "\n").getBytes());
        socketChannel.write(out);
    }
}

