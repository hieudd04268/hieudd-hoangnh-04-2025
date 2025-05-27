# Week 7: Hardware / OS

1. What are the two main components of a CPU?

- Answer:
  - Control Unit (CU - Đơn vị điều khiển):
    - Điều khiển luồng thực thi của chương trình
    - Giải mã và thực thi các lệnh
    - Điều phối các thành phần khác của CPU
  - Arithmetic Logic Unit (ALU - Đơn vị số học logic):
    - Thực hiện các phép tính số học
    - Thực hiện các phép toán logic
    - Xử lý dữ liệu

2. What is the purpose of the ALU?

- Answer:
  - Thực hiện các phép tính số học: cộng, trừ, nhân, chia
  - Thực hiện các phép toán logic: AND, OR, NOT, XOR
  - So sánh các giá trị
  - Xử lý bit và shift operations
  - Là "bộ não tính toán" của CPU

3. What is the purpose of the memory hierarchy?

- Answer:
  - Cân bằng giữa tốc độ và chi phí
  - Tổ chức theo thứ tự từ nhanh đến chậm:
    1. Registers (nhanh nhất, nhỏ nhất)
    2. Cache (L1, L2, L3)
    3. RAM
    4. Storage (HDD/SSD)
  - Tối ưu hiệu suất hệ thống
  - Giảm độ trễ truy cập dữ liệu

4. What is the difference between a hard drive and a solid state drive?

- Answer:
  - Hard Disk Drive (HDD):
    - Sử dụng đĩa từ quay
    - Chậm hơn do có bộ phận cơ học
    - Rẻ hơn, dung lượng lớn
    - Dễ hỏng do va đập
  - Solid State Drive (SSD):
    - Không có bộ phận chuyển động
    - Nhanh hơn 5-10 lần HDD
    - Đắt hơn, dung lượng thường nhỏ hơn
    - Bền hơn, ít tiêu thụ điện

5. What is the purpose of the operating system?

- Answer:
  - Quản lý tài nguyên phần cứng
  - Cung cấp giao diện người dùng
  - Quản lý processes và threads
  - Quản lý bộ nhớ và file system
  - Đảm bảo an ninh và quyền truy cập
  - Điều phối các ứng dụng

6. What is the difference between a process and a thread?

- Answer:
  - Process:
    - Chương trình độc lập đang chạy
    - Có không gian bộ nhớ riêng
    - Tốn nhiều tài nguyên hơn
    - Khó chia sẻ dữ liệu
  - Thread:
    - Đơn vị thực thi trong process
    - Chia sẻ bộ nhớ với threads khác
    - Nhẹ hơn, tạo nhanh hơn
    - Dễ chia sẻ dữ liệu

7. How does the operating system handle thread synchronization?

- Answer:
  - Sử dụng các cơ chế:
    - Mutex (mutual exclusion):
      -> Là cơ chế khóa đơn giản nhất
      -> Chỉ cho phép 1 thread truy cập tài nguyên tại một thời điểm
      -> Hoạt động như "chìa khóa":
      -> Thread muốn truy cập phải "lấy khóa"
      -> Thread khác phải đợi đến khi "khóa được trả lại"
      Object mutex = new Object();
      synchronized(mutex) {
          // Critical section - chỉ 1 thread được thực thi
      }
    - Semaphores:
      -> Giống như "bộ đếm permits"
      -> Cho phép N thread truy cập đồng thời
      -> Có 2 loại:
      -> Binary Semaphore (N=1): giống Mutex
      -> Counting Semaphore (N>1): cho phép nhiều thread
      Semaphore sem = new Semaphore(5); // cho phép 5 thread
      sem.acquire(); // lấy permit
      try {
          // Critical section
      } finally {
          sem.release(); // trả permit
      }
    - Monitors
      -> Cơ chế cao cấp hơn Mutex
      -> Kết hợp mutex và condition synchronization
      -> Trong Java, mọi object đều có monitor
      -> Sử dụng từ khóa synchronized
      synchronized void method() {
          // Chỉ 1 thread được thực thi method này
      }
    - Locks
      - Linh hoạt hơn synchronized
      - Có thể tự quản lý việc khóa/mở khóa
      - Hỗ trợ timeout, interruptible
      Lock lock = new ReentrantLock();
      lock.lock();
      try {
          // Critical section
      } finally {
          lock.unlock();
      }
  - Giải quyết các vấn đề:
    1. Race Condition:
      - Xảy ra khi nhiều thread cùng truy cập/thay đổi dữ liệu
      - Kết quả phụ thuộc vào thứ tự thực thi
      - Giải pháp: Sử dụng synchronization để đảm bảo atomic operation
      // Có thể xảy ra race condition
      counter++;

      // An toàn
      synchronized(this) {
          counter++;
      }
    2. Deadlock:
      - Xảy ra khi các thread chờ đợi lẫn nhau
      - Thread A giữ resource 1, đợi resource 2
      - Thread B giữ resource 2, đợi resource 1
      - Giải pháp:
        - Tránh nested locks
        - Sử dụng timeout
        - Đặt thứ tự lock cố định
    3. Critical Section:
      - Đoạn code truy cập tài nguyên dùng chung
      - Cần đảm bảo mutual exclusion
      - Giải pháp: Sử dụng synchronization mechanisms
      // Critical section protection
      synchronized(sharedResource) {
          // Critical section code
      }
    4. Producer-Consumer:
      - Producer tạo data, Consumer sử dụng data
      - Cần đồng bộ hóa để:
        - Producer không overload buffer
        - Consumer không đọc khi buffer rỗng

      - Giải pháp: Sử dụng wait/notify
      // Producer
      synchronized(buffer) {
          while(buffer.isFull()) {
              buffer.wait();
          }
          buffer.add(item);
          buffer.notify();
      }

      // Consumer
      synchronized(buffer) {
          while(buffer.isEmpty()) {
              buffer.wait();
          }
          item = buffer.remove();
          buffer.notify();
      }

      Các cơ chế này giúp đảm bảo:
        - Thread safety
        - Data consistency
        - Tránh race conditions
        - Hiệu suất tối ưu
        - Tài nguyên được chia sẻ an toàn

8. What is the purpose of the scheduler?

- Answer:
  - Phân phối CPU time cho các processes
  - Tối ưu hóa hiệu suất hệ thống
  - Đảm bảo công bằng
  - Quản lý độ ưu tiên
  - Tránh deadlock và starvation

9. What is the purpose of the file system?

- Answer:
  - Tổ chức và quản lý files/folders
  - Quản lý không gian lưu trữ
  - Cung cấp interface truy cập files
  - Đảm bảo an toàn dữ liệu
  - Kiểm soát quyền truy cập
  - Tối ưu hiệu suất đọc/ghi

10. What is the purpose of the file system?

- Answer:

11. How does the file system handle file access control?

- Answer:
  - Sử dụng permissions:
    - Read (đọc)
    - Write (ghi)
    - Execute (thực thi)
  - Phân quyền theo:
    - User (người dùng)
    - Group (nhóm)
    - Others (người khác)
  - Access Control Lists (ACL)
  - File ownership
  - Security attributes 
