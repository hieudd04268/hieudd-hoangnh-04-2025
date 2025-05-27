
# Week 6: Java Reflection and Annotations

1. Reflection có thể thay đổi các thành phần final trong một lớp được không? Code ví dụ minh hoạ cách thay đổi một biến static final.

- Answer:
  - Có thể, nhưng không nên làm vì vi phạm nguyên tắc của final
  - Ví dụ thay đổi biến static final:
  public class Example {
      private static final String CONSTANT = "Original";
      
      public static void main(String[] args) throws Exception {
          Field field = Example.class.getDeclaredField("CONSTANT");
          field.setAccessible(true);
          
          // Xóa modifier final
          Field modifiersField = Field.class.getDeclaredField("modifiers");
          modifiersField.setAccessible(true);
          modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
          
          // Thay đổi giá trị
          field.set(null, "Changed");
      }
  }

2. Reflection có ảnh hưởng như thế nào đến hiệu suất của một ứng dụng Java?

- Answer:
  - Chậm hơn truy cập trực tiếp (5-10 lần)
  - Tốn bộ nhớ do tạo objects phản chiếu
  - JVM không thể tối ưu hóa
  - Tốn thời gian kiểm tra bảo mật
  - Nên hạn chế dùng trong code quan trọng về hiệu suất

3. Rủi ro về bảo mật khi sử dụng reflection? Làm thế nào để giảm thiểu rủi ro này?

- Answer:
  - Có thể truy cập private members
  - Phá vỡ tính đóng gói
  - Bỏ qua kiểm tra bảo mật
  - Thay đổi final fields
  Giảm thiểu:
    - Hạn chế quyền truy cập reflection
    - Sử dụng SecurityManager
    - Kiểm tra kỹ input
    - Chỉ cho phép reflection ở package tin cậy

4. Làm thế nào để truy cập thông tin về nested classes và anonymous classes bằng Reflection? 

- Answer:
  // Nested classes
  Class<?> outerClass = MyClass.class;
  Class<?>[] nestedClasses = outerClass.getDeclaredClasses();

  // Anonymous class
  Object anonymous = new Interface() { };
  Class<?> anonymousClass = anonymous.getClass();
  System.out.println(anonymousClass.getName()); // Tên sẽ có dạng OuterClass$1

5. Cơ chế hoạt động của setAccessible(true)?

- Answer:
  - Bỏ qua kiểm tra access control của Java
  - Cho phép truy cập private/protected members
  - Không bỏ qua SecurityManager
  - Thay đổi flag accessible trong AccessibleObject
  - Có thể bị chặn bởi module system từ Java 9

6. Giải thích cơ chế hoạt động của Annotation Processor? Làm thế nào chúng được tích hợp vào quá trình biên dịch?

- Answer:
  - Chạy trong quá trình biên dịch
  - Xử lý annotations trước khi tạo bytecode
  - Có thể tạo code mới, validate code
  - Tích hợp qua javax.annotation.processing.Processor
  - Đăng ký trong META-INF/services

7. Annotation processors có ảnh hưởng như thế nào đến quá trình biên dịch?

- Answer:
  - Thêm thời gian biên dịch
  - Có thể tạo code mới
  - Có thể báo lỗi compile
  - Có thể modify bytecode
  - Chạy theo round (nhiều vòng xử lý)

8.  Làm thế nào để tạo một annotation có thể được kế thừa?

- Answer:
  @Inherited // Cho phép annotation được kế thừa
  @Retention(RetentionPolicy.RUNTIME)
  public @interface MyAnnotation {
      String value() default "";
  }

9. Phân biệt các loại RetentionPolicy của @Retention?

- Answer:
  - SOURCE: Chỉ tồn tại trong source code
  - CLASS: Tồn tại trong .class file nhưng không load vào JVM
  - RUNTIME: Tồn tại cả lúc runtime, có thể truy cập bằng reflection  

10. Sự khác biệt giữa reflection và annotation processors trong việc xử lý annotation?

- Answer:
  Reflection:
    - Xử lý lúc runtime
    - Có thể đọc/thay đổi giá trị
    - Ảnh hưởng performance
    - Linh hoạt hơn
  Annotation Processors:
    - Xử lý lúc compile-time
    - Chỉ đọc được annotations
    - Không ảnh hưởng runtime
    - Tạo code mới được
    - An toàn hơn