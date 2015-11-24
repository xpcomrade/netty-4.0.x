package com.xpcomrade.socket.nio;

import com.xpcomrade.socket.Constant;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by xpcomrade on 2015/10/30.
 */
public class BIOTimeClient {

    public static void main(String[] args) {
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            socket = new Socket(Constant.HOST, Constant.PORT);
            socket.setTcpNoDelay(true);
            out = new PrintWriter(socket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.println("QUERY TIME ORDER");
            out.flush();
            System.out.println("Send order to server succeed");

            String resp = in.readLine();
            System.out.println("Now is ：" + resp);
        } catch (IOException e) {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                in = null;
            }

            if (out != null) {
                out.close();
                out = null;
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                socket = null;
            }
        }


    }
}
