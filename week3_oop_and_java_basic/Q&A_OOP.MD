
# Week 3: OOP

1. Lập trình hướng đối tượng là gì? So sánh với hướng lập trình thủ tục

- Answer: Lập trình hướng đối tượng là lập trình xoay quanh đối tượng (objects). Mỗi đối tượng được tạo ra trên khuôn mẫu cụ thể gọi là class
- So sánh với lập trình thủ tục:
+ Lập trình thủ tục: tập trung vào các hàm xử lý dữ liệu, dữ liệu và hàm tách biệt nhau
+ OOP: gom dữ liệu và phương thức xử lý vào một đối tượng, dễ bảo trì và tái sử dụng code

2. Giải thích 4 nguyên tắc cơ bản OOP: Encapsulation, abstraction, Inheritance, Polymorphism

- Answer:
    + Encapsulation (Tính đóng gói): Che giấu thông tin và bundling code với data mà nó hoạt động. Thông qua access modifiers (private, protected, public), ta kiểm soát được việc truy cập dữ liệu.
    + Abstraction (Tính trừu tượng): Ẩn đi chi tiết phức tạp, chỉ hiển thị tính năng thiết yếu với người dùng. VD: khi lái xe, ta chỉ cần biết cách dùng vô lăng, chân ga... mà không cần biết động cơ hoạt động thế nào.
    + Inheritance (Tính kế thừa): Cho phép class con (subclass) kế thừa thuộc tính và phương thức từ class cha (superclass). Giúp tái sử dụng code và thể hiện mối quan hệ "is-a".
    + Polymorphism (Tính đa hình): Cho phép một interface có nhiều implementation khác nhau. Có 2 dạng:
    . Compile-time (overloading): Nhiều method cùng tên nhưng khác tham số
    . Runtime (overriding): Subclass có thể override method của superclass

3. Interface và abstract Class khác nhau như thế nào?

- Answer:
    - Interface:
        - Chỉ chứa abstract methods và constants
        - Một class có thể implement nhiều interface
        - Không có constructor
        - Tất cả methods mặc định là public abstract
        - Tất cả fields mặc định là public static final

    - Abstract Class:
        - Có thể chứa cả abstract và non-abstract methods
        - Một class chỉ extend được một abstract class
        - Có thể có constructor
        - Methods có thể có bất kỳ access modifier nào
        - Fields có thể có bất kỳ access modifier nào

4. Một class có thể kế thừa nhiều class cùng lúc không? nếu không thì tại sao?

- Answer: Không. Java không hỗ trợ đa kế thừa với class để tránh diamond problem (là một vấn đề phát sinh trong lập trình hướng đối tượng khi sử dụng đa kế thừa):
    + Khi một class kế thừa từ nhiều superclass có cùng tên method
    + JVM sẽ không biết nên gọi method của superclass nào
    + Gây ra sự mơ hồ và xung đột

5. Làm thế nào để đảm bảo một class tuân theo nguyên tắc Encapsulation
- Answer:
    + Đặt tất cả instance variables là private
    + Cung cấp public getter/setter methods để truy cập và thay đổi dữ liệu
    + Thêm validation logic trong setter methods để kiểm soát dữ liệu đầu vào
    + Không expose internal details của class ra bên ngoài

6. Tại sao nên sử dụng getter và setter thay vì truy cập trực tiếp biến instance?
- Answer:
    + Kiểm soát được access và modification của data
    + Có thể thêm validation logic khi set giá trị
    + Có thể thay đổi internal implementation (là cách mà một class tổ chức và thực hiện các chi tiết bên trong của nó) mà không ảnh hưởng code bên ngoài
    + Dễ dàng debug khi có vấn đề với data
    + Đảm bảo tính encapsulation của OOP

7. Một class có thể vừa là abstract class và là interface được không? Tại sao Java không hỗ trợ đa kế thừa với class, nhưng lại hỗ trợ đa kế thừa với interface?
- Answer:
    + Không thể. Một class chỉ có thể là một trong hai:
    -> Hoặc là abstract class
    -> Hoặc là interface
    + Java cho phép đa kế thừa với interface vì:
    -> Interface chỉ định nghĩa "contract", không có implementation
    -> Không xảy ra diamond problem vì implementation sẽ do class định nghĩa
    -> Tăng tính flexible trong thiết kế

8. Khi nào sử dụng abstract class thay vì interface?
- Answer:
    + Khi muốn share code giữa nhiều class có liên quan
    + Khi cần định nghĩa non-public members
    + Khi cần định nghĩa instance variables
    + Khi các class con có nhiều đặc điểm chung
    + Khi muốn update implementation mà không ảnh hưởng đến các class đã implement

9. Sự khác nhau giữa method overloading và method overriding
- Answer:
    Method Overloading (Compile time polymorphism):
        - Nhiều methods cùng tên nhưng khác parameters trong cùng class
        - Xảy ra trong cùng một class
        - Return type có thể khác nhau
        - Không liên quan đến inheritance

    Method Overriding (Runtime polymorphism):
        - Subclass định nghĩa lại method của superclass
        - Xảy ra giữa superclass và subclass
        - Return type phải giống hoặc là subtype
        - Liên quan đến inheritance
10. Có thể override một phương thức static không? về mặt ý nghĩa thì tại sao?
- Answer:
    - Không thể override static methods
    - Lý do:
    - Static methods thuộc về class, không thuộc về instance
    - Binding của static methods được thực hiện lúc compile time
    - Static methods không thể thực hiện runtime polymorphism
    - Nếu định nghĩa method cùng tên trong subclass, đó là method hiding, không phải overriding

11. Garbage Collection hoạt động như thế nào trong Java? Làm sao để buộc hệ thống thực hiện thu gom rác?
- Answer: 
    - Garbage Collection (GC) tự động quản lý bộ nhớ bằng cách:
        - Xác định objects không còn được reference
        - Thu hồi bộ nhớ của các objects này
        - Defragment memory để tối ưu hiệu suất
    - Cách buộc hệ thống thực hiện GC:
        - System.gc() - chỉ là suggestion, không đảm bảo GC sẽ chạy
        - Runtime.getRuntime().gc()
        - Tốt nhất là để JVM tự quyết định khi nào thực hiện GC
        - Không nên force GC trừ khi thực sự cần thiết vì ảnh hưởng performance
