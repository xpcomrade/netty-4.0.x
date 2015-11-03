package com.xpcomrade.socket.bio;

import com.xpcomrade.socket.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by xpcomrade on 2015/10/30.
 */
public class TimeServer {

    static Logger logger = LoggerFactory.getLogger(TimeServer.class);

    public static void main(String[] args) throws IOException{

        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(Constant.PORT);
            System.out.println("The time server is start in port " + Constant.PORT);

            Socket socket = null;
            while (true) {
                socket = serverSocket.accept();
                new Thread(new TimeServerHandler(socket)).start();
            }
        } finally {
            if (serverSocket != null) {
                System.out.println("The time server close");
                serverSocket.close();
                serverSocket = null;
            }
        }

    }
}
