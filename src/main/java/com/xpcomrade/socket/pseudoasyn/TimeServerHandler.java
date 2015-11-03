package com.xpcomrade.socket.pseudoasyn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

/**
 * Created by xpcomrade on 2015/10/30.
 */
public class TimeServerHandler implements Runnable {

    private Socket socket = null;

    public  TimeServerHandler (Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(this.socket.getOutputStream(), true);
            String body = null;
            String currentTime = null;
            while(true) {
                body = in.readLine();
                if (body == null) {
                    break;
                }
                System.out.println("The time server receive order：" + body);
                currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body)
                        ? new Date(System.currentTimeMillis()).toString() : "BAD ORDER";
                out.println(currentTime);
            }
        } catch (Exception e) {
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
            if (this.socket != null) {
                try {
                    this.socket.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                this.socket = null;
            }
        }
    }
}
