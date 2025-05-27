
# Week 3: Java basic

1. Sự khác biệt giữa dữ liệu primitive và non-primitive trong Java là gì?

- Answer:
  Primitive type:
    - Là kiểu dữ liệu cơ bản: byte, short, int, long, float, double, boolean, char
    - Lưu trữ giá trị trực tiếp trong bộ nhớ stack
    - Có giá trị mặc định (0, 0.0, false, '\u0000')
    - Không có methods
    - Nhẹ và nhanh hơn

  Non-primitive type (Reference type):
    - Là các objects: String, Array, Class, Interface,...
    - Lưu trữ địa chỉ tham chiếu đến giá trị trong heap memory
    - Giá trị mặc định là null
    - Có các methods
    - Tốn bộ nhớ hơn do phải lưu thêm thông tin object

2. tại sao String là non-primitive type trong khi có vẻ nó chỉ chứa kí tự?

- Answer:
  - String là một class trong Java, không phải kiểu dữ liệu cơ bản
  - String có nhiều methods để xử lý chuỗi (length(), substring(), replace(),...)
  - String object quản lý một mảng các ký tự char[] bên trong
  - String có các tính chất của object như immutability
  - String được tối ưu đặc biệt bởi JVM thông qua String Pool

3. Sự khác biệt giữa int và Integer là gì? khi nào nào dùng cái nào?

- Answer:
  - int:
  - Là primitive type
  - Nhẹ và nhanh hơn
  - Không thể null
  - Không có methods

- Integer:
  - Là wrapper class (non-primitive)
  - Có thể null
  - Có nhiều methods hữu ích
  - Dùng trong collections (List, Set,...)

Khi dùng:
- Dùng int cho các biến số đơn giản, tính toán cơ bản
- Dùng Integer khi:
  - Cần lưu giá trị null
  - Làm việc với collections
  - Cần dùng các methods của Integer
  - Trong generic types

4. Auto-boxing và unboxing là gì? Khi nào chúng xảy ra?

- Answer:
  + Auto-boxing: Tự động chuyển đổi từ primitive type sang wrapper class
  int a = 10;
  Integer b = a; // auto-boxing
  + Unboxing: Tự động chuyển đổi từ wrapper class sang primitive type
  Integer a = new Integer(10);
  int b = a; // unboxing
  + Xảy ra khi:
    - Gán giá trị primitive cho wrapper class và ngược lại
    - Truyền tham số vào method
    - Trong các phép tính
    - Khi làm việc với collections

5. Sự khác biệt giữa mảng tĩnh (Array) và danh sách động (ArrayList) trong Java?- Answer:
- Answer:
  - Array:
    - Kích thước cố định khi khởi tạo
    - Có thể chứa primitive và object
    - Truy cập phần tử nhanh hơn
    - Ít tốn bộ nhớ hơn
    - Không có sẵn các methods tiện ích

  - ArrayList:
    - Kích thước có thể thay đổi động
    - Chỉ chứa được object
    - Có nhiều methods tiện ích
    - Tốn bộ nhớ hơn do phải duy trì cấu trúc động
    - Thích hợp khi cần thêm/xóa phần tử thường xuyên

6. Sự khác biệt giữa String, StringBuffer, và StringBuilder?
- Answer:
  - String:

  - Immutable (không thể thay đổi)
  - Thread-safe
  - Hiệu suất thấp khi nối chuỗi nhiều
  - StringBuffer:

  - Mutable (có thể thay đổi)
  - Thread-safe (synchronized)
  - Hiệu suất tốt hơn String khi thao tác
  - Phù hợp môi trường đa luồng
  - StringBuilder:

  - Mutable
  - Không thread-safe
  - Hiệu suất tốt nhất
  - Phù hợp môi trường đơn luồng

7. Giải thích về String Pool trong Java? Tại sao Java sử dụng String Pool?
- Answer:
  - String Pool là một khu vực bộ nhớ đặc biệt trong heap memory
  - Lưu trữ các String literals để tái sử dụng
  - Giúp tiết kiệm bộ nhớ bằng cách dùng chung các String giống nhau
  String s1 = "hello"; // tạo trong pool
  String s2 = "hello"; // tái sử dụng từ pool
  String s3 = new String("hello"); // tạo object mới trong heap

8. String là immutable, điều này có nghĩa gì? Tại sao String được thiết kế theo cách này?
- Answer:
  - Immutable nghĩa là không thể thay đổi sau khi tạo
  - Khi "thay đổi" String, thực chất là tạo String mới
  String s = "hello";
  s = s + " world"; // tạo String mới, không thay đổi String cũ
  Lý do:
  - Thread-safe
  - Bảo mật (không lo String bị thay đổi)
  - Tối ưu bộ nhớ qua String Pool
  - Hash code không đổi, tốt cho HashMap/HashSet

9. intern() method trong String làm gì?
- Answer:
  - Đưa String vào String Pool
  - Nếu String đã tồn tại trong pool, trả về reference đến String đó
  - Nếu chưa có, thêm vào pool và trả về reference
  String s1 = new String("hello").intern();
  String s2 = "hello";
  System.out.println(s1 == s2); // true vì cùng reference trong pool

10. Chuyện gì xảy ra nếu thay đổi một giá trị của String được tạo bằng String literal?
- Answer:
  - String literal không thể thay đổi trực tiếp
  - Khi "thay đổi", một String mới được tạo ra
  - String cũ vẫn giữ nguyên trong pool
  - Garbage collector sẽ thu hồi String không còn được tham chiếu

11. Toán tử == và .equals() khác nhau thế nào khi so sánh Object?
- Answer: 
  - == so sánh reference (địa chỉ bộ nhớ)
  - .equals() so sánh nội dung
  String s1 = new String("hello");
  String s2 = new String("hello");
  System.out.println(s1 == s2); // false (khác reference)
  System.out.println(s1.equals(s2)); // true (cùng nội dung)

12. Tại sao 
double a = 0.1
double b = 0.2
boolean check = (a + b) == 0.3
tại sao check lại bằng false?
Nếu double có nhược điểm như vậy tại sao nó vẫn tồn tại
- Answer: 
  - Nguyên nhân:
  - Số thực trong máy tính được lưu dưới dạng nhị phân
  - Một số thập phân không thể biểu diễn chính xác trong hệ nhị phân
  - 0.1 và 0.2 trong nhị phân là số vô hạn tuần hoàn
  - Khi cộng lại, có sai số: 0.1 + 0.2 = 0.30000000000000004

- Vẫn dùng double vì:
  - Đủ chính xác cho hầu hết ứng dụng
  - Nhanh hơn so với BigDecimal
  - Tiết kiệm bộ nhớ
  - Khi cần độ chính xác cao, có thể dùng BigDecimal
  Cách xử lý khi so sánh số thực:
  double epsilon = 0.000001;
  boolean isEqual = Math.abs((0.1 + 0.2) - 0.3) < epsilon;