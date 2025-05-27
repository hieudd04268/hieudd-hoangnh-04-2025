package nio_echo_server_selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOEchoServer {
    public static void main(String[] args) {
        final int PORT = 1234;

        try (
                Selector selector = Selector.open();
                ServerSocketChannel servertChannel = ServerSocketChannel.open()
        ) {
            servertChannel.configureBlocking(false);
            servertChannel.bind(new InetSocketAddress(PORT));
            servertChannel.register(selector, SelectionKey.OP_ACCEPT);

            System.out.println("🚀 NIO Echo Server đang chạy tại cổng: " + PORT);

            while (true) {
                selector.select(); //Chờ sự kiện từ Channel
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> it = keys.iterator();

                while (it.hasNext()) {
                    SelectionKey key = it.next();
                    it.remove();

                    if (key.isAcceptable()) {
                        SocketChannel clientChannel = servertChannel.accept();
                        clientChannel.configureBlocking(false);
                        clientChannel.register(selector, SelectionKey.OP_READ);
                        System.out.println("🔧️ Kết nối mới " + clientChannel.getRemoteAddress());
                    } else if (key.isReadable()) {
                        SocketChannel clientChannel = (SocketChannel) key.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(2048);

                        int bytesRead = clientChannel.read(buffer);
                        if(bytesRead == -1) {
                            System.out.println("🛑 Client ngắt kết nối: " + clientChannel.getRemoteAddress());
                            clientChannel.close();
                            key.cancel();
                            continue;
                        }
                        buffer.flip();
                        String received = new String(buffer.array(), 0, bytesRead);
                        System.out.println("🎯 Nhận: " + received.trim());

                        //Echo lại cho client
                        buffer.rewind();
                        clientChannel.write(buffer);
                    }
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
