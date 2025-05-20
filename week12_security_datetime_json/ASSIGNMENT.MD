# Bài tập Java về DateTime, JSON và giao diện Console

## Bài tập thực tế: Hệ thống quản lý tài chính cá nhân (FinanceTracker)

Xây dựng một ứng dụng quản lý tài chính cá nhân với giao diện console, tập trung vào xử lý thời gian, dữ liệu JSON, và tương tác người dùng.

### Mục tiêu
1. Xử lý thời gian với Java DateTime API (múi giờ, lập lịch, tính toán)
2. Quản lý dữ liệu JSON (đọc/ghi, query, transformation)
3. Xây dựng giao diện console tương tác với người dùng
4. Áp dụng bảo mật cơ bản cho dữ liệu tài chính

### Cấu trúc dự án
```
finance_tracker/
├── data/
│   ├── accounts.json
│   ├── transactions.json
│   ├── categories.json
│   ├── budgets.json
│   └── recurring_transactions.json
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── financetracker/
│   │   │   │   ├── Main.java
│   │   │   │   ├── model/
│   │   │   │   │   ├── Account.java
│   │   │   │   │   ├── Transaction.java
│   │   │   │   │   ├── Category.java
│   │   │   │   │   ├── Budget.java
│   │   │   │   │   ├── RecurringTransaction.java
│   │   │   │   │   ├── Currency.java
│   │   │   │   │   └── User.java
│   │   │   │   ├── service/
│   │   │   │   │   ├── AccountService.java
│   │   │   │   │   ├── TransactionService.java
│   │   │   │   │   ├── BudgetService.java
│   │   │   │   │   ├── SchedulerService.java
│   │   │   │   │   ├── ReportService.java
│   │   │   │   │   ├── CurrencyService.java
│   │   │   │   │   ├── BackupService.java
│   │   │   │   │   └── NotificationService.java
│   │   │   │   ├── repository/
│   │   │   │   │   ├── JsonRepository.java
│   │   │   │   │   ├── AccountRepository.java
│   │   │   │   │   ├── TransactionRepository.java
│   │   │   │   │   ├── CategoryRepository.java
│   │   │   │   │   ├── BudgetRepository.java
│   │   │   │   │   └── RecurringTransactionRepository.java
│   │   │   │   ├── ui/
│   │   │   │   │   ├── ConsoleUI.java
│   │   │   │   │   ├── Menu.java
│   │   │   │   │   ├── ColoredConsole.java
│   │   │   │   │   ├── Table.java
│   │   │   │   │   └── InputHandler.java
│   │   │   │   ├── util/
│   │   │   │   │   ├── JsonUtils.java
│   │   │   │   │   ├── DateTimeUtils.java
│   │   │   │   │   ├── ValidationUtils.java
│   │   │   │   │   ├── SecurityUtils.java
│   │   │   │   │   └── Logger.java
│   │   ├── resources/
│   │   │   ├── config.properties
│   │   │   └── exchange_rates.json
└── exports/
    ├── reports/
    └── backups/
```

### Yêu cầu chi tiết

#### 1. Giao diện Console

1. **Menu tương tác**:
    - Menu chính với các tùy chọn rõ ràng
    - Menu con cho từng chức năng (quản lý tài khoản, giao dịch, báo cáo)
    - Hỗ trợ điều hướng (quay lại, thoát)

2. **Hiển thị dữ liệu**:
    - Bảng dữ liệu có căn chỉnh (sử dụng thư viện như JLine hoặc tự xây dựng)
    - Màu sắc cho thông tin quan trọng (số dư âm, vượt ngân sách)
    - Biểu đồ đơn giản dạng ASCII/Unicode cho báo cáo

3. **Nhập liệu**:
    - Xác thực đầu vào (kiểu số, định dạng ngày tháng)
    - Gợi ý hoàn thành (danh mục, tài khoản)
    - Xử lý lỗi nhập liệu thân thiện

4. **Hiệu ứng và trải nghiệm**:
    - Thông báo thành công/lỗi với màu sắc
    - Hiệu ứng loading đơn giản
    - Xóa màn hình, làm mới nội dung

#### 2. Quản lý thời gian (Java DateTime)

1. **Quản lý giao dịch theo thời gian**:
    - Lưu trữ thời gian giao dịch với múi giờ (ZonedDateTime)
    - Hiển thị theo múi giờ người dùng
    - Lọc giao dịch theo khoảng thời gian (ngày, tuần, tháng, quý, năm)

2. **Giao dịch định kỳ**:
    - Thiết lập giao dịch tự động theo lịch (hàng ngày, hàng tuần, hàng tháng)
    - Xử lý các trường hợp đặc biệt (ngày cuối tháng, năm nhuận)
    - Tính toán ngày thực hiện tiếp theo

3. **Ngân sách theo thời gian**:
    - Thiết lập ngân sách theo kỳ (tuần, tháng)
    - So sánh chi tiêu thực tế với ngân sách
    - Dự báo chi tiêu dựa trên xu hướng hiện tại

4. **Báo cáo thời gian**:
    - Phân tích chi tiêu theo thời gian (giờ, ngày trong tuần, ngày trong tháng)
    - Tính toán xu hướng chi tiêu theo thời gian
    - So sánh giữa các kỳ (tháng này vs tháng trước, năm nay vs năm trước)

#### 3. Quản lý dữ liệu JSON

1. **Lưu trữ dữ liệu**:
    - Sử dụng JSON để lưu tất cả dữ liệu (tài khoản, giao dịch, danh mục, ngân sách)
    - Thiết kế schema JSON tối ưu và dễ mở rộng
    - Đảm bảo tính nhất quán dữ liệu

2. **Thao tác với JSON**:
    - Đọc/ghi file JSON với Jackson hoặc Gson
    - Truy vấn và lọc dữ liệu (JSONPath hoặc tương tự)
    - Cập nhật một phần dữ liệu (JSON Patch)

3. **Xuất/Nhập dữ liệu**:
    - Xuất báo cáo dạng JSON
    - Nhập dữ liệu từ file JSON bên ngoài
    - Tạo backup dữ liệu tự động

4. **Chuyển đổi dữ liệu**:
    - Chuyển đổi giữa các đơn vị tiền tệ
    - Tạo báo cáo tổng hợp từ dữ liệu chi tiết
    - Chuyển đổi giữa các định dạng thời gian

#### 4. Chức năng tài chính

1. **Quản lý tài khoản**:
    - Thêm, sửa, xóa tài khoản (tiền mặt, ngân hàng, thẻ tín dụng)
    - Theo dõi số dư từng tài khoản
    - Chuyển tiền giữa các tài khoản

2. **Quản lý giao dịch**:
    - Thêm chi tiêu và thu nhập
    - Phân loại giao dịch theo danh mục
    - Tìm kiếm và lọc giao dịch

3. **Quản lý ngân sách**:
    - Thiết lập ngân sách cho từng danh mục
    - Theo dõi tiến độ chi tiêu
    - Cảnh báo khi vượt ngân sách

4. **Báo cáo tài chính**:
    - Báo cáo thu chi
    - Phân tích chi tiêu theo danh mục
    - Dự báo tài chính tương lai

### Nghiệp vụ chi tiết

#### A. Quản lý tài khoản

1. **Cấu trúc tài khoản (Account)**:
   ```json
   {
     "id": "acc123",
     "name": "Tiền lương ACB",
     "type": "BANK",
     "currency": "VND",
     "balance": 15000000,
     "openDate": "2023-01-15T00:00:00+07:00",
     "interestRate": 0.05,
     "notes": "Tài khoản lương chính"
   }
   ```

2. **Thao tác tài khoản**:
    - Tạo tài khoản mới với loại và tiền tệ
    - Cập nhật số dư (tự động hoặc thủ công)
    - Tính lãi cho tài khoản tiết kiệm

#### B. Quản lý giao dịch

1. **Cấu trúc giao dịch (Transaction)**:
   ```json
   {
     "id": "trx456",
     "accountId": "acc123",
     "type": "EXPENSE",
     "amount": 500000,
     "currency": "VND",
     "categoryId": "cat789",
     "description": "Mua đồ ăn",
     "date": "2023-08-15T19:30:45+07:00",
     "location": "Siêu thị Vinmart",
     "tags": ["food", "groceries"]
   }
   ```

2. **Thao tác giao dịch**:
    - Thêm chi tiêu/thu nhập
    - Tự động cập nhật số dư tài khoản
    - Thêm thẻ (tag) và vị trí cho giao dịch

3. **Giao dịch định kỳ (RecurringTransaction)**:
   ```json
   {
     "id": "rec789",
     "baseTransactionId": "trx456",
     "frequency": "MONTHLY",
     "dayOfMonth": 15,
     "startDate": "2023-08-15T00:00:00+07:00",
     "endDate": "2024-08-15T00:00:00+07:00",
     "nextExecutionDate": "2023-09-15T00:00:00+07:00",
     "status": "ACTIVE"
   }
   ```

#### C. Quản lý ngân sách

1. **Cấu trúc ngân sách (Budget)**:
   ```json
   {
     "id": "bdg123",
     "name": "Chi tiêu tháng 8/2023",
     "period": "MONTHLY",
     "startDate": "2023-08-01T00:00:00+07:00",
     "endDate": "2023-08-31T23:59:59+07:00",
     "categoryBudgets": [
       {
         "categoryId": "cat789",
         "amount": 2000000
       },
       {
         "categoryId": "cat790",
         "amount": 1000000
       }
     ],
     "totalBudget": 5000000,
     "notes": "Ngân sách thường"
   }
   ```

2. **Thao tác ngân sách**:
    - Tạo ngân sách cho kỳ mới
    - Phân bổ ngân sách theo danh mục
    - Theo dõi chi tiêu thực tế so với ngân sách

#### D. Báo cáo và phân tích

1. **Báo cáo chi tiêu**:
    - Tổng hợp theo danh mục
    - Phân tích xu hướng theo thời gian
    - So sánh với kỳ trước

2. **Phân tích tài chính**:
    - Tỷ lệ chi tiêu/thu nhập
    - Dự báo dòng tiền
    - Đề xuất tiết kiệm

### Chi tiết kỹ thuật và triển khai

#### 1. Cấu trúc Model

1. **Account.java**:
   ```java
   public class Account {
       private String id;
       private String name;
       private AccountType type; // Enum: CASH, BANK, CREDIT_CARD, SAVING, INVESTMENT
       private String currency;
       private BigDecimal balance;
       private ZonedDateTime openDate;
       private BigDecimal interestRate;
       private String notes;
       
       // Getters, setters, constructors
   }
   ```

2. **Transaction.java**:
   ```java
   public class Transaction {
       private String id;
       private String accountId;
       private TransactionType type; // Enum: INCOME, EXPENSE, TRANSFER
       private BigDecimal amount;
       private String currency;
       private String categoryId;
       private String description;
       private ZonedDateTime date;
       private String location;
       private List<String> tags;
       
       // Getters, setters, constructors
   }
   ```

3. **RecurringTransaction.java**:
   ```java
   public class RecurringTransaction {
       private String id;
       private String baseTransactionId;
       private Frequency frequency; // Enum: DAILY, WEEKLY, MONTHLY, YEARLY
       private Integer dayOfMonth; // For MONTHLY
       private DayOfWeek dayOfWeek; // For WEEKLY
       private Month month; // For YEARLY
       private ZonedDateTime startDate;
       private ZonedDateTime endDate;
       private ZonedDateTime nextExecutionDate;
       private Status status; // Enum: ACTIVE, PAUSED, COMPLETED
       
       // Getters, setters, constructors
   }
   ```

#### 2. Cấu trúc UI

1. **ConsoleUI.java**:
   ```java
   public class ConsoleUI {
       private Scanner scanner;
       private AccountService accountService;
       private TransactionService transactionService;
       // Other services
       
       public void start() {
           // Hiển thị menu chính
       }
       
       private void showMainMenu() {
           // In ra các tùy chọn menu
       }
       
       private void handleAccountMenu() {
           // Xử lý menu tài khoản
       }
       
       // Các phương thức menu khác
   }
   ```

2. **Table.java**:
   ```java
   public class Table {
       private List<String> headers;
       private List<List<String>> rows;
       private List<Integer> columnWidths;
       
       public Table(List<String> headers) {
           this.headers = headers;
           this.rows = new ArrayList<>();
           this.columnWidths = new ArrayList<>();
           
           // Khởi tạo độ rộng cột dựa trên headers
           for (String header : headers) {
               columnWidths.add(header.length());
           }
       }
       
       public void addRow(List<String> row) {
           // Thêm hàng và cập nhật độ rộng cột nếu cần
       }
       
       public String render() {
           // Tạo chuỗi để hiển thị bảng
       }
   }
   ```

#### 3. Các Service chính

1. **TransactionService.java**:
   ```java
   public class TransactionService {
       private TransactionRepository repository;
       private AccountService accountService;
       
       public Transaction addTransaction(Transaction transaction) {
           // Thêm giao dịch và cập nhật số dư tài khoản
       }
       
       public List<Transaction> getTransactionsByDateRange(ZonedDateTime start, ZonedDateTime end) {
           // Lấy giao dịch trong khoảng thời gian
       }
       
       public Map<Category, BigDecimal> summarizeByCategory(List<Transaction> transactions) {
           // Tổng hợp theo danh mục
       }
       
       // Các phương thức khác
   }
   ```

2. **SchedulerService.java**:
   ```java
   public class SchedulerService {
       private ScheduledExecutorService scheduler;
       private RecurringTransactionRepository recurringRepo;
       private TransactionService transactionService;
       
       public void scheduleAllActiveRecurringTransactions() {
           // Lên lịch cho tất cả giao dịch định kỳ
       }
       
       public void scheduleTransaction(RecurringTransaction recurringTx) {
           // Lập lịch cho một giao dịch định kỳ
       }
       
       public ZonedDateTime calculateNextExecutionDate(RecurringTransaction recurringTx) {
           // Tính ngày thực hiện tiếp theo
       }
       
       // Các phương thức khác
   }
   ```

3. **JsonUtils.java**:
   ```java
   public class JsonUtils {
       private static ObjectMapper objectMapper = new ObjectMapper()
           .registerModule(new JavaTimeModule())
           .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
       
       public static <T> T fromJson(String json, Class<T> clazz) {
           // Chuyển JSON thành đối tượng Java
       }
       
       public static String toJson(Object object) {
           // Chuyển đối tượng Java thành JSON
       }
       
       public static <T> List<T> fromJsonArray(String json, Class<T> clazz) {
           // Chuyển JSON array thành List
       }
       
       // Các phương thức khác
   }
   ```

4. **DateTimeUtils.java**:
   ```java
   public class DateTimeUtils {
       public static ZonedDateTime nowUserTimeZone() {
           // Trả về thời gian hiện tại ở múi giờ người dùng
       }
       
       public static LocalDate getStartOfMonth(YearMonth yearMonth) {
           // Lấy ngày đầu tháng
       }
       
       public static LocalDate getEndOfMonth(YearMonth yearMonth) {
           // Lấy ngày cuối tháng
       }
       
       public static List<LocalDate> getDaysInRange(LocalDate start, LocalDate end) {
           // Lấy danh sách các ngày trong khoảng
       }
       
       // Các phương thức khác
   }
   ```

### Tình huống test

1. **Tài khoản và số dư**:
    - Tạo các loại tài khoản khác nhau
    - Cập nhật số dư
    - Chuyển tiền giữa các tài khoản

2. **Giao dịch và danh mục**:
    - Thêm giao dịch (thu nhập, chi tiêu)
    - Phân loại theo danh mục
    - Tìm kiếm và lọc giao dịch

3. **Giao dịch định kỳ**:
    - Tạo giao dịch hàng tháng
    - Kiểm tra tự động thực hiện
    - Chỉnh sửa và hủy giao dịch định kỳ

4. **Ngân sách và theo dõi**:
    - Thiết lập ngân sách theo danh mục
    - Theo dõi chi tiêu so với ngân sách
    - Nhận cảnh báo khi vượt ngân sách

5. **Báo cáo**:
    - Xem báo cáo tháng
    - So sánh với tháng trước
    - Xuất báo cáo ra file

### Output mẫu

```
===== QUẢN LÝ TÀI CHÍNH CÁ NHÂN =====
[1] Quản lý tài khoản
[2] Quản lý giao dịch
[3] Quản lý ngân sách
[4] Báo cáo & Phân tích
[5] Thiết lập
[0] Thoát
Chọn chức năng: 2

===== QUẢN LÝ GIAO DỊCH =====
[1] Thêm giao dịch mới
[2] Xem giao dịch gần đây
[3] Tìm kiếm giao dịch
[4] Quản lý giao dịch định kỳ
[0] Quay lại
Chọn chức năng: 1

----- THÊM GIAO DỊCH MỚI -----
Loại giao dịch:
[1] Chi tiêu
[2] Thu nhập
[3] Chuyển khoản
Chọn loại: 1

Tài khoản: [1] Tiền lương ACB [2] Ví tiền mặt [0] Thêm tài khoản mới
Chọn tài khoản: 1

Số tiền: 150000
Danh mục: [1] Ăn uống [2] Đi lại [3] Mua sắm [0] Thêm danh mục mới
Chọn danh mục: 1

Mô tả: Ăn trưa
Ngày (định dạng YYYY-MM-DD, nhấn Enter cho ngày hiện tại): 2023-08-15
Giờ (định dạng HH:MM, nhấn Enter cho giờ hiện tại): 12:30
Vị trí (không bắt buộc): Quán cơm gần công ty
Tags (cách nhau bởi dấu phẩy, không bắt buộc): lunch, work

Tạo giao dịch định kỳ? (y/n): y
Tần suất: [1] Hàng ngày [2] Hàng tuần [3] Hàng tháng [4] Hàng năm
Chọn tần suất: 2

Áp dụng từ ngày: 2023-08-15
Áp dụng đến ngày (để trống nếu không giới hạn): 2023-12-31

===== XÁC NHẬN THÔNG TIN =====
Loại giao dịch: Chi tiêu
Tài khoản: Tiền lương ACB
Số tiền: 150,000 VND
Danh mục: Ăn uống
Mô tả: Ăn trưa
Thời gian: 15/08/2023 12:30
Vị trí: Quán cơm gần công ty
Tags: lunch, work

Giao dịch định kỳ: Hàng tuần
Thực hiện từ: 15/08/2023 đến 31/12/2023
Lần tiếp theo: 22/08/2023

Xác nhận thêm giao dịch? (y/n): y

✓ Đã thêm giao dịch thành công! (ID: trx789)
✓ Đã tạo giao dịch định kỳ! (ID: rec456)
✓ Số dư tài khoản "Tiền lương ACB" đã cập nhật: 14,850,000 VND (-150,000)

Nhấn Enter để tiếp tục...

===== MENU CHÍNH =====
...
```

### Báo cáo mẫu

```
====== BÁO CÁO THÁNG 8/2023 ======

TỔNG QUAN:
- Tổng thu: 20,000,000 VND
- Tổng chi: 12,500,000 VND
- Chênh lệch: +7,500,000 VND

CHI TIÊU THEO DANH MỤC:
┌────────────┬─────────────┬────────┬─────────────┐
│ Danh mục   │ Chi tiêu    │ % Tổng │ Ngân sách   │
├────────────┼─────────────┼────────┼─────────────┤
│ Ăn uống    │ 3,500,000   │ 28.0%  │ 4,000,000   │
│ Đi lại     │ 2,000,000   │ 16.0%  │ 2,500,000   │
│ Nhà cửa    │ 5,000,000   │ 40.0%  │ 5,000,000   │
│ Mua sắm    │ 1,500,000   │ 12.0%  │ 1,000,000 ! │
│ Giải trí   │ 500,000     │ 4.0%   │ 1,500,000   │
└────────────┴─────────────┴────────┴─────────────┘

BIỂU ĐỒ CHI TIÊU:
Ăn uống    [███████████████████            ] 87.5%
Đi lại     [████████████                   ] 80.0%
Nhà cửa    [████████████████████           ] 100.0%
Mua sắm    [████████████████████████████   ] 150.0% !
Giải trí   [██████                         ] 33.3%

XU HƯỚNG CHI TIÊU (3 THÁNG GẦN NHẤT):
┌────────┬────────────┬────────────┬────────────┐
│ Danh   │ Tháng 6    │ Tháng 7    │ Tháng 8    │
│ mục    │            │            │            │
├────────┼────────────┼────────────┼────────────┤
│ Ăn     │ 3,200,000  │ 3,350,000  │ 3,500,000 ↑│
│ uống   │            │            │            │
├────────┼────────────┼────────────┼────────────┤
│ Đi lại │ 2,500,000  │ 2,300,000  │ 2,000,000 ↓│
└────────┴────────────┴────────────┴────────────┘

GIAO DỊCH LỚN NHẤT:
- 5,000,000 VND - Tiền thuê nhà (01/08/2023)
- 1,200,000 VND - Mua quần áo (15/08/2023)
- 800,000 VND - Đổ xăng (10/08/2023)

Xuất báo cáo dạng JSON? (y/n): y
✓ Đã xuất báo cáo tại: exports/reports/report_2023_08.json

Nhấn Enter để tiếp tục...
```

### Yêu cầu nộp bài
1. Source code đầy đủ theo cấu trúc đã gợi ý
2. File README.md giải thích cách thiết kế và sử dụng
3. Các file JSON mẫu trong thư mục data/
4. Kết quả chạy thử các tình huống test

### Gợi ý triển khai
1. Bắt đầu với việc tạo các model và repository cơ bản
2. Xây dựng UI console và các menu chính
3. Triển khai các service cho quản lý tài khoản và giao dịch
4. Thêm chức năng lập lịch và giao dịch định kỳ
5. Xây dựng báo cáo và phân tích dữ liệu
6. Hoàn thiện chức năng tìm kiếm và lọc nâng cao