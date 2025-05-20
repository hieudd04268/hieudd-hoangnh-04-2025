# Week 2: How to Java Work

1. JDK, JVM, và JRE khác nhau như thế nào? Vai trò của từng thành phần trong hệ sinh thái Java?

    Answer:
        + JDK: là bộ công cụ để phát triển Java (chứa compiler, debugger) bảo gồm cả JVM và JRE. Vai trò: dùng để viết và phát triển ứng dụng Java.
        + JVM: là máy ảo thực thi bytecode chuyển thành mã máy để chạy trên phần cứng. Vai trò: chạy chương trình Java.
        + JRE: là một nơi cung cấp môi trường thực thi ứng dụng Java bao gồm các thư viện cần thiết, JVM. Vai trò: Cung cấp nền tảng để chạy ứng dụng Java.
2. JDK có những công cụ nào quan trọng cho lập trình viên Java?

    Answer: 
        + javac: Biên dịch mã Java thành bytecode.
        + java: Chạy ứng dụng Java.
        + javadoc: Tạo tài liệu API từ mã nguồn.
        + jar: Đóng gói file class và tài nguyên thành file JAR.
        + jdb: Debug ứng dụng Java.

3. JVM hoạt động như thế nào khi bạn chạy một chương trình Java?

    Answer: JVM tải bytecode từ file .class, sau đó thực thi bằng cách dịch thành mã máy (qua interpreter hoặc JIT Compiler), đồng thời quản lý bộ nhớ và garbage collection.

4. Có những thành phần quan trọng nào bên trong JVM?

	Answer:
        + Class Loader: Tải lớp Java vào bộ nhớ.
        + Runtime Data Areas: Heap, Stack, Method Area, PC Registers.
        + Execution Engine: Interpreter và JIT Compiler.
        + Garbage Collector: Thu hồi bộ nhớ không dùng.

5. JVM quản lý bộ nhớ như thế nào? Các vùng nhớ quan trọng trong JVM là gì?

    Answer: JVM quản lý bộ nhớ qua các vùng:
        + Heap: Lưu đối tượng và mảng.
        + Stack: Lưu biến cục bộ và frame cho thread.
        + Method Area: Lưu thông tin lớp và phương thức.
        + PC Registers: Lưu địa chỉ lệnh đang thực thi.

6. Class Metadata (Method Area) trong JVM có vai trò gì?

    Answer: Method Area lưu thông tin lớp (tên, phương thức, trường, hằng số), chia sẻ giữa các thread, hỗ trợ thực thi chương trình.

7. PermGen và Metaspace khác nhau như thế nào? Tại sao Java chuyển từ PermGen sang Metaspace từ Java 8?

    Answer:
        + PermGen: Phần heap cố định (trước Java 8), lưu metadata lớp, dễ gây OutOfMemoryError.
        + Metaspace: Vùng nhớ native (từ Java 8), mở rộng động, không giới hạn bởi heap.
        Lý do chuyển: Tránh lỗi bộ nhớ, quản lý hiệu quả hơn.

8. Heap và Stack trong JVM khác nhau như thế nào?

    Answer:
        + Heap: Vùng nhớ chung, lưu đối tượng, quản lý bởi Garbage Collector.
        + Stack: Vùng nhớ riêng mỗi thread, lưu biến cục bộ, quản lý theo LIFO.

10. Garbage Collection trong JVM là gì? Tại sao nó quan trọng?

    Answer: Garbage Collection (GC) tự động thu hồi bộ nhớ không dùng. 
            Quan trọng: Ngăn memory leak, đảm bảo ứng dụng chạy mượt.

11. Những thuật toán chính của Garbage Collection mà JVM sử dụng là gì?

    Answer: 
        + Mark-and-Sweep: Đánh dấu đối tượng sống, xóa đối tượng không dùng.
        + Copying GC: Sao chép đối tượng sống, xóa vùng cũ.
        + Generational GC: Chia heap thành young/old, GC theo thế hệ.

12. JVM có những loại Garbage Collector nào? Khác nhau ở điểm nào?

    Answer:
        + Serial GC: 1 thread, cho ứng dụng nhỏ.
        + Parallel GC: Nhiều thread, tăng hiệu suất.
        + CMS GC: Chạy đồng thời, giảm thời gian dừng.
        + G1 GC: Chia vùng heap, tối ưu heap lớn.

13. Sự khác biệt giữa Minor GC, Major GC và Full GC là gì?

    Answer:
        + Minor GC: Thu hồi young generation.
        + Major GC: Thu hồi old generation.
        + Full GC: Thu hồi toàn bộ heap (young + old).

14. Bộ nhớ Stack có bị thu hồi bởi Garbage Collector không? Nếu không, nó được quản lý như thế nào?

    Answer: Stack không bị GC thu hồi. Nó tự quản lý theo LIFO, frame được xóa khi phương thức kết thúc.

15. Làm thế nào để tránh Memory Leak trong Java?

    Answer: 
        + Không giữ tham chiếu không cần thiết.
        + Đóng tài nguyên (stream, connection).
        + Dùng profiler kiểm tra bộ nhớ.
        + Tránh static cho đối tượng lớn.

16. JIT Compiler trong JVM là gì? Nó cải thiện hiệu năng như thế nào?

    Answer: JIT Compiler dịch bytecode thành mã máy tại runtime cho các phương thức gọi nhiều, tăng tốc độ thực thi so với interpret.

17. Khi nào JIT Compiler dịch mã bytecode sang mã máy thực thi?

    Answer: Khi phương thức được gọi thường xuyên (hot method), dựa trên ngưỡng cấu hình.

18. Khi ứng dụng Java chạy chậm, bạn sẽ kiểm tra gì đầu tiên?

    Answer:
        + CPU và bộ nhớ sử dụng.
        + Log Garbage Collection.
        + Profiler tìm bottleneck.
        + Vấn đề threading/đồng bộ.

20. Làm thế nào để debug lỗi liên quan đến bộ nhớ Heap và Stack?

    Answer:
        + Dùng VisualVM, JConsole theo dõi heap.
        + Xem stack trace tìm StackOverflowError.
        + Phân tích heap dump phát hiện memory leak.
        + Kiểm tra thông số GC.