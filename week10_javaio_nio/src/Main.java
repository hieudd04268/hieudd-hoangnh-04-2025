import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;


/**
 * Ứng dụng giám sát log file realtime
 * - Sử dụng Java NIO WatchService để giám sát thư mục logs/
 * - Sử dụng Java IO BufferedReader/Writer để đọc/ghi file
 * - Phân tích và lọc các dòng ERROR từ log files
 */
public class Main {
}