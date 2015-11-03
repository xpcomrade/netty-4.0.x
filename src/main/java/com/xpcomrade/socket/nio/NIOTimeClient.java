package com.xpcomrade.socket.nio;

import com.xpcomrade.socket.Constant;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * Created by xpcomrade on 11/2/15.
 */
public class NIOTimeClient {


    public static void main(String[] args) throws Exception{
        SocketChannel clientChannel = null;
        Selector selector = null;

        boolean connected = false;
        try {
            clientChannel = SocketChannel.open();
            selector = Selector.open();
            clientChannel.configureBlocking(false);
            connected = clientChannel.connect(new InetSocketAddress(Constant.HOST, Constant.PORT));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        if (connected) {
            clientChannel.register(selector, SelectionKey.OP_READ);
        } else {
            clientChannel.register(selector, SelectionKey.OP_CONNECT);
        }



    }
}
