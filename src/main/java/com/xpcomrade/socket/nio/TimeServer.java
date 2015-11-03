package com.xpcomrade.socket.nio;

import com.xpcomrade.socket.Constant;

import java.io.IOException;

/**
 * Created by xpcomrade on 2015/10/30.
 */
public class TimeServer {

    public static void main(String[] args) throws IOException{
        MultiplexerTimeServer timeServer = new MultiplexerTimeServer(Constant.HOST, Constant.PORT);
        new Thread(timeServer, "NIO-MultiplexerTimeServer-001").start();
    }
}
