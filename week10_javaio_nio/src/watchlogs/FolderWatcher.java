package watchlogs;


import java.io.IOException;
import java.nio.file.*;

import static java.nio.file.StandardWatchEventKinds.*;

public class FolderWatcher {
    public static void main(String[] args){
        try {
            //Khởi tạo service
            WatchService watcher = FileSystems.getDefault().newWatchService();

            //Đăng ký thư mục logs/ để theo dõi các sự kiện CREATE và MODIFY
            Path dir = Paths.get("logs");
            dir.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);

            System.out.println("👀 Đang giám sát thư mục " + dir.toAbsolutePath());

            //vòng lặp để theo dõi sự kiện
            while(true){
                WatchKey key;
                try {
                    // ⏳ Block cho đến khi có sự kiện
                    key = watcher.take();

                } catch (InterruptedException e) {
                    System.out.println("😤 Bị gián đoạn");
                    return;
                }

                //Duyệt qua các sự kiện xảy ra
                for (WatchEvent<?> event : key.pollEvents()) {
                    WatchEvent.Kind<?> kind = event.kind();

                    //⏭️ Bỏ qua nếu là sự kiện overflow
                    if (kind == OVERFLOW) continue;

                    //🫳 Lấy tên file từ context
                    WatchEvent<Path> ev = (WatchEvent<Path>) event;
                    Path name = ev.context();

                    //📢 In ra thông tin sự kiện
                    System.out.printf("📢 %s: %s📌%n", kind.name(), name);
                }

                //🆕 Reset key để tiếp tục theo dõi
                boolean valid = key.reset();
                if(!valid) {
                    System.out.println("WatchKey không còn hợp lệ❗");
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Lỗi khi khởi tạo WatchService " + e.getMessage() + "❗");
        }
    }
}
