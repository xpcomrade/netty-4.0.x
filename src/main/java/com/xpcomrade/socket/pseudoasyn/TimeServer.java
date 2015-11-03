package com.xpcomrade.socket.pseudoasyn;

import com.xpcomrade.socket.Constant;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by xpcomrade on 2015/10/30.
 */
public class TimeServer {

    public static void main(String[] args) throws IOException{

        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(Constant.PORT);
            System.out.println("The time server is start in port " + Constant.PORT);
            Socket socket = null;

            TimeServerHandlerExecutorPool pool = new TimeServerHandlerExecutorPool(50, 10000);
            while (true) {
                socket = serverSocket.accept();
                pool.execute(new TimeServerHandler(socket));
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
