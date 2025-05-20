# Bài tập Java về Exception Handling và Logging

## Bài tập thực tế: Hệ thống quản lý thư viện với Exception Handling và Logging

Xây dựng một hệ thống quản lý thư viện đơn giản để thực hành kỹ năng sử dụng Exception và Logging trong Java. Ứng dụng sẽ quản lý sách, người dùng, và các hoạt động mượn/trả sách.

### Mục tiêu
1. Xây dựng hệ thống quản lý thư viện với xử lý ngoại lệ đầy đủ
2. Triển khai logging chi tiết trong toàn bộ ứng dụng
3. Xử lý các tình huống lỗi thường gặp (file không tồn tại, định dạng không hợp lệ, vv)
4. Tạo và sử dụng custom exception cho các trường hợp nghiệp vụ đặc thù
5. Thực hành kỹ thuật phục hồi lỗi và ghi log theo cấp độ phù hợp

### Cấu trúc dự án gợi ý
```
thu_vien/
├── data/
│   ├── books.csv
│   ├── users.csv
│   └── borrows.csv
├── logs/
│   └── library.log
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── thuvien/
│   │   │   │   ├── Main.java
│   │   │   │   ├── model/
│   │   │   │   │   ├── Book.java
│   │   │   │   │   ├── User.java
│   │   │   │   │   └── BorrowRecord.java
│   │   │   │   ├── service/
│   │   │   │   │   ├── BookService.java
│   │   │   │   │   ├── UserService.java
│   │   │   │   │   └── BorrowService.java
│   │   │   │   ├── repository/
│   │   │   │   │   ├── BookRepository.java
│   │   │   │   │   ├── UserRepository.java
│   │   │   │   │   └── BorrowRepository.java
│   │   │   │   ├── exception/
│   │   │   │   │   ├── BookNotFoundException.java
│   │   │   │   │   ├── UserNotFoundException.java
│   │   │   │   │   ├── BookAlreadyBorrowedException.java
│   │   │   │   │   ├── DataFormatException.java
│   │   │   │   │   ├── RepositoryException.java
│   │   │   │   │   └── ServiceException.java
│   │   │   │   └── util/
│   │   │   │       ├── LoggerFactory.java
│   │   │   │       ├── CsvParser.java
│   │   │   │       └── DateUtils.java
│   │   ├── resources/
│   │   │   └── logging.properties
└── output/
    └── reports/
```

### Yêu cầu chi tiết

#### 1. Model và Repository
- **Book**: id, title, author, yearPublished, isAvailable
- **User**: id, name, email, membershipDate
- **BorrowRecord**: id, bookId, userId, borrowDate, returnDate

- Mỗi repository sẽ đọc/ghi dữ liệu từ các file CSV tương ứng:
    - BookRepository: đọc/ghi books.csv
    - UserRepository: đọc/ghi users.csv
    - BorrowRepository: đọc/ghi borrows.csv

#### 2. Exception Handling
1. **Custom Exceptions**:
    - `BookNotFoundException`: Khi không tìm thấy sách theo ID
    - `UserNotFoundException`: Khi không tìm thấy người dùng theo ID
    - `BookAlreadyBorrowedException`: Khi cố gắng mượn sách đã được mượn
    - `DataFormatException`: Khi dữ liệu CSV không đúng định dạng
    - `RepositoryException`: Lỗi chung khi tương tác với repository
    - `ServiceException`: Lỗi xử lý nghiệp vụ

2. **Xử lý ngoại lệ**:
    - Sử dụng try-with-resources cho tất cả I/O operations
    - Bắt và xử lý các loại exception phù hợp
    - Throw lại exception với thông tin bổ sung khi cần thiết
    - Phân biệt checked và unchecked exceptions

#### 3. Logging
1. **Cấu hình Logger**:
    - Sử dụng java.util.logging hoặc Log4j
    - Ghi log ra console và file (logs/library.log)
    - Thiết lập các level log phù hợp

2. **Quy tắc logging**:
    - SEVERE: Lỗi nghiêm trọng (không đọc được file, lỗi hệ thống)
    - WARNING: Lỗi nghiệp vụ (sách không tồn tại, người dùng không tìm thấy)
    - INFO: Hoạt động bình thường (mượn sách thành công, thêm sách mới)
    - FINE/FINER: Thông tin debug chi tiết

3. **LoggerFactory**:
    - Tạo class tiện ích để lấy logger có tên tương ứng với mỗi class

#### 4. Chức năng cần triển khai
1. **BookService**:
    - Thêm sách mới
    - Tìm kiếm sách theo ID hoặc tiêu đề
    - Cập nhật thông tin sách
    - Xóa sách

2. **UserService**:
    - Đăng ký người dùng mới
    - Tìm kiếm người dùng theo ID hoặc email
    - Cập nhật thông tin người dùng
    - Xóa người dùng

3. **BorrowService**:
    - Mượn sách: cập nhật trạng thái sách và tạo BorrowRecord
    - Trả sách: cập nhật trạng thái sách và BorrowRecord
    - Liệt kê sách đang được mượn bởi người dùng
    - Kiểm tra sách quá hạn

#### 5. Utility Classes
1. **CsvParser**:
    - Đọc/ghi file CSV
    - Chuyển đổi giữa model và dòng CSV
    - Xử lý lỗi định dạng

2. **DateUtils**:
    - Chuyển đổi giữa String và LocalDate
    - Tính số ngày giữa hai ngày (cho mục đích kiểm tra quá hạn)

#### 6. Main Application
- Menu console để tương tác với hệ thống
- Xử lý input của người dùng
- Hiển thị thông báo lỗi phù hợp
- Ghi log tất cả hoạt động

### Yêu cầu chi tiết về Exception và Logging

#### A. Xử lý Exception
1. **Repository Layer**:
    - Bắt IOException khi đọc/ghi file
    - Throw DataFormatException khi dữ liệu không hợp lệ
    - Sử dụng try-with-resources cho tất cả file operations

2. **Service Layer**:
    - Bắt RepositoryException và wrap thành ServiceException nếu cần
    - Throw BookNotFoundException, UserNotFoundException khi tìm kiếm thất bại
    - Xử lý nghiệp vụ với BookAlreadyBorrowedException

3. **Main Application**:
    - Catch tất cả exception và hiển thị thông báo phù hợp
    - Ghi log exception với stack trace đầy đủ

#### B. Logging
1. **Định dạng Log**:
    - Thời gian
    - Level
    - Class/method
    - Thông điệp
    - Stack trace (cho error/severe)

2. **Điểm logging**:
    - Bắt đầu/kết thúc mỗi operation
    - Trước/sau I/O operation
    - Khi xảy ra exception
    - Mỗi lần thay đổi dữ liệu

3. **Quy tắc an toàn**:
    - Không log thông tin nhạy cảm
    - Sử dụng parameterized logging
    - Xử lý exception khi logging thất bại

### Tình huống test
1. File data không tồn tại
2. File data có dữ liệu không hợp lệ (sai định dạng)
3. Tìm kiếm sách/người dùng không tồn tại
4. Mượn sách đã được mượn
5. Trả sách không phải do người dùng hiện tại mượn
6. Mượn sách quá số lượng cho phép
7. Xóa sách đang được mượn

### Output mẫu
```
[2023-08-15 14:30:45] [INFO] [BookService.addBook] Sách mới đã được thêm: "Clean Code" (ID: B001)
[2023-08-15 14:31:10] [INFO] [UserService.registerUser] Người dùng mới đã đăng ký: "Nguyen Van A" (ID: U001)
[2023-08-15 14:32:05] [INFO] [BorrowService.borrowBook] Sách "Clean Code" (B001) đã được mượn bởi "Nguyen Van A" (U001)
[2023-08-15 14:33:20] [WARNING] [BookService.findById] Không tìm thấy sách với ID: B999
[2023-08-15 14:34:15] [SEVERE] [BookRepository.loadData] Không thể đọc file books.csv: File không tồn tại
```

### Yêu cầu nộp bài
1. Source code đầy đủ theo cấu trúc đã gợi ý
2. File README.md giải thích cách thiết kế exception và logging
3. Các file data mẫu trong thư mục data/
4. File logging.properties hoặc tương đương

### Gợi ý triển khai
1. Bắt đầu với việc tạo các model và custom exception
2. Triển khai LoggerFactory và cấu hình logging
3. Triển khai các repository class với exception handling đầy đủ
4. Triển khai các service class sử dụng repository và xử lý nghiệp vụ
5. Tạo main application với menu console
6. Test các tình huống lỗi khác nhau