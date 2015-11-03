package com.xpcomrade.socket.nio;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;

/**
 * Created by xpcomrade on 2015/10/30.
 */
public class MultiplexerTimeServer implements Runnable {

    private Selector selector;

    private ServerSocketChannel channel;

    private volatile boolean stop;

    public MultiplexerTimeServer (String host, int port) {

        try {
            selector = Selector.open();
            channel = ServerSocketChannel.open();
            channel.configureBlocking(false);
            channel.socket().bind(new InetSocketAddress(host, port), 1024);
            channel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("The time server is start in port :" + port);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void stop(){
        this.stop = true;
    }


    @Override
    public void run() {
        while (!stop) {
            try {
                selector.select(10000);
                Iterator<SelectionKey> it =  selector.selectedKeys().iterator();
                SelectionKey key = null;
                while (it.hasNext()) {
                    key = it.next();
                    it.remove();
                    try {
                        handleKey(key);
                    } catch (IOException e) {
                        if (key != null) {
                            key.cancel();
                            if (key.channel() != null) {
                                key.channel().close();
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (selector != null) {
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleKey(SelectionKey key) throws IOException{
        if (key.isValid()) {
            if (key.isAcceptable()) {
                SocketChannel socketChannel = channel.accept();
                socketChannel.configureBlocking(false);
                socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
            }

            if (key.isReadable()) {
                SocketChannel socketChannel = (SocketChannel)key.channel();
                socketChannel.setOption(StandardSocketOptions.TCP_NODELAY, true);
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                readBuffer.clear();
                int readBytes = socketChannel.read(readBuffer);
                if (readBytes > 0) {
                    readBuffer.flip();
                    byte[] bytes = new byte[readBuffer.remaining()];
                    readBuffer.get(bytes);

                    String body = new String(bytes, "UTF-8");
                    System.out.println("The time server receive order：" + body);
                    String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new Date(System.currentTimeMillis()).toString() : "BAD ORDER";
                    doWrite(socketChannel, currentTime);
                } else if (readBytes < 0) {
                    key.cancel();
                    socketChannel.close();
                }
            }
        }
    }

    private void doWrite(SocketChannel channel, String response) throws IOException{
        byte[] bytes = response.getBytes();
        ByteBuffer byteBuffer = ByteBuffer.allocate(bytes.length);
        byteBuffer.clear();
        byteBuffer.put(bytes);
        byteBuffer.flip();
        channel.write(byteBuffer);
    }
}
