
# Week 4: Java Collections

1. Sự khác biệt giữa HashSet, LinkedHashSet và TreeSet? Trong trường hợp nào thì nên dùng cái nào?

- Answer:
  - HashSet:
    - Lưu trữ phần tử không theo thứ tự
    - Hiệu suất tốt nhất O(1) cho các thao tác thêm/xóa/tìm kiếm
    - Sử dụng bảng băm (hash table) để lưu trữ

  - LinkedHashSet:
    - Duy trì thứ tự thêm vào của phần tử
    - Hiệu suất tốt O(1), nhưng tốn bộ nhớ hơn HashSet
    - Kết hợp hash table và linked list

  - TreeSet:
    - Sắp xếp phần tử theo thứ tự tự nhiên
    - Hiệu suất O(log n) cho các thao tác
    - Sử dụng cấu trúc Red-Black Tree

  Khi nào dùng:
    - HashSet: Khi không cần duy trì thứ tự, cần hiệu suất cao nhất
    - LinkedHashSet: Khi cần duy trì thứ tự thêm vào
    - TreeSet: Khi cần dữ liệu luôn được sắp xếp

2. Làm thế nào để HashSet đảm bảo các phần tử không trùng lặp?

- Answer:
  - Sử dụng phương thức hashCode() để tạo mã băm cho mỗi phần tử
  - Khi thêm phần tử mới:
    1. Tính hashCode của phần tử
    2. So sánh với hashCode của các phần tử hiện có
    3. Nếu hashCode trùng, dùng equals() để so sánh nội dung
    4. Chỉ thêm vào nếu không trùng

3. Sự khác biệt giữa HashMap, LinkedHashMap và TreeMap? Trong trường hợp nào thì nên dùng cái nào?

- Answer:

4. Từ sau Java 8, HashMap xử lý collisions như thế nào?

- Answer:
  - Trước Java 8: Dùng linked list để xử lý collision
  - Từ Java 8:
    - Khi số phần tử trong bucket < 8: Dùng linked list
    - Khi số phần tử ≥ 8: Chuyển sang balanced tree (Red-Black Tree)
    - Khi số phần tử < 6: Chuyển lại linked list
  - Lý do: Cải thiện hiệu suất từ O(n) xuống O(log n) khi có nhiều collision

5. Tại sao String và Integer thường được dùng làm key trong HashMap?
- Answer:
  - Là immutable (không thể thay đổi sau khi tạo)
  - Có hashCode() và equals() được implement tốt
  - String:
    - Dễ đọc, dễ nhớ
    - Được cache trong String Pool
    - hashCode được cache
  - Integer:
    - Đơn giản, hiệu quả
    - hashCode() trả về chính giá trị
    - Được cache các giá trị phổ biến (-128 đến 127)

6. Cách hoạt động của Red-black Tree khi thêm, xoá phần tử?
- Answer:
  Đặc điểm:
    - Mỗi node có màu đỏ hoặc đen
    - Root luôn màu đen
    - Không có 2 node đỏ liền kề
    - Mọi đường đi từ root đến null có cùng số node đen
  Khi thêm:
    1. Thêm node mới (màu đỏ)
    2. Kiểm tra và sửa vi phạm bằng:
      - Đổi màu
      - Xoay trái/phải
  Khi xóa:
    1. Xóa node
    2. Kiểm tra và sửa vi phạm
    3. Tái cân bằng cây nếu cần

7. AVL Tree là gì? Có ưu điểm và nhược điểm gì?
- Answer:
  Đặc điểm:
    - Là cây nhị phân tìm kiếm cân bằng
    - Độ chênh lệch chiều cao giữa cây con trái và phải ≤ 1
  Ưu điểm:
    - Cân bằng hoàn hảo hơn Red-black Tree
    - Tìm kiếm nhanh hơn
    - Độ phức tạp O(log n)
  Nhược điểm:
    - Tốn nhiều phép xoay khi thêm/xóa
    - Tốn bộ nhớ để lưu thông tin chiều cao
    - Chậm hơn Red-black Tree khi thêm/xóa

8. B-Tree là gì? Được sử dụng như thế nào?
- Answer:
  - Là cây cân bằng đa nhánh (mỗi node có nhiều con)
  - Được thiết kế cho hệ thống lưu trữ ngoài (disk)
  - Mỗi node chứa nhiều khóa và con trỏ
  Sử dụng:
    - Trong cơ sở dữ liệu
    - Hệ thống file
    - Khi cần tối ưu I/O operations
    - Khi dữ liệu quá lớn không thể lưu hết trong RAM
  Ưu điểm:
    - Giảm số lần truy cập disk
    - Phù hợp với dữ liệu lớn
    - Duy trì cân bằng tốt
