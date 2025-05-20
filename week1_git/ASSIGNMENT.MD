
# Week 1: Git

1. Git thực sự lưu trữ dữ liệu như thế nào? Các đối tượng (objects) như blob, tree, commit, và tag có quan hệ gì với nhau trong hệ thống lưu trữ phân tán của Git?

- Answer: 
  + Git lưu trữ dữ liệu bằng việc sử dụng SHA-1 hashes (Secure Hash Algorithm) tạo thành một đồ thị có hướng phi chu trình (Directed Acyclic Graph - DAG).
  + Git objects như blob, tree, commit, và tag có quan hệ trong hệ thống lưu trữ phân tán của Git:
    * Blob: lưu trữ lại file content (nội dung của tệp)  
    * Tree: đại diện cấu trúc của thư mục, biểu diễn tệp (blob) và thư mục (tree con) 
    * Commit: ghi lại snapshot của dự án, tham chiếu đến một tree và commit trước của nó
    * Tag: đánh dấu một commit cụ thể nào đó, thường dùng cho phiên bản phát hành
  --> quan hệ của các đối tượng: blob, tree, commit, và tag là tạo nên chuỗi lịch sử phiên bản.
  
2. Khi ta di chuyển giữa các commit, Git làm gì dưới lớp vỏ bọc của nó? HEAD thực chất là gì và nó tác động thế nào đến trạng thái hiện tại của repository?

- Answer: 
  + Khi ta di chuyển giữa các commit, Git dưới lớp vỏ bọc của nó Git cập nhật thư mục làm việc để khớp với trạng thái của commit đó
  + HEAD là con trỏ chỉ đến commit hiện tại hoặc branch đang làm việc. HEAD quyết định trạng thái của repository: nếu HEAD trỏ đến branch, commit mới sẽ thuộc về branch đó; 
  nếu trỏ trực tiếp đến commit (ditached HEAD), commit mới không thuộc branch nào và dễ mất.

3. Git có thể tạo hàng nghìn nhánh (branches) gần như tức thì mà không tốn tài nguyên. Điều này hoạt động ra sao về mặt kỹ thuật, và tại sao việc hợp nhất (merge) đôi khi lại dẫn đến xung đột?

- Answer: Git tạo nhánh nhanh vì nhánh chỉ là con trỏ đến commit, không sao chép dữ liệu, nên tốn rất ít tài nguyên và việc hợp nhất đôi khi lại xung đột bởi khi 2 nhánh đều thay đổi theo cách không tương thích thì Git không thể tự động hợp nhất được.

4. Khi ta thêm file vào staging area, thực chất Git làm gì? Điều gì thực sự thay đổi khi ta chạy lệnh git commit?

- Answer: Khi chạy git add thì git tạo hoặc cập nhật blob object cho file trong repository, đưa vào staging area và khi chạy git commit git tạo commit object mới chứa snapshot của staging area, thông tin giả và tham chiếu đến commit cha.

5. Git phân biệt working directory, staging area và repository như thế nào? Tại sao có những thay đổi được theo dõi nhưng chưa được commit?

- Answer:
  + working directory: nơi chứa thư mục dự án thực tế
  + staging area: nơi chuẩn bị thay đổi trước khi commit
  + repository: là nơi lưu trữ lịch sử commit
  - thay đổi được theo dõi nhưng chưa được commit là những thay đổi đã ghi vào staging area nhưng không được ghi vào repository.

6. Git lưu trữ thay đổi của ta ở đâu? Làm thế nào Git có thể khôi phục những thay đổi đã bị "stash" một cách chính xác?

- Answer: 
  + Git lưu trữ thay đổi của ta ở trong stash object tạm thời.
  + Khi khôi phục (git stash apply), Git áp dụng lại thay đổi từ stash vào working directory và staging area mà giữ nguyên trạng thái ban đầu.

7. Khi xảy ra xung đột hợp nhất (merge conflict), tại sao Git không thể tự động giải quyết? Git sử dụng cơ chế nào để xác định sự khác biệt và yêu cầu sự can thiệp của lập trình viên?

- Answer:
  + Git không thể tự động giải quyết khi gặp xung đột hợp nhất bởi vì khi thay đổi 2 nhánh theo cách mâu thuẫn thì Git không tự động giải quyết được.
  + Git dùng three-way diff (so sánh ba chiều) để tìm khác biệt, đánh dấu xung đột và yêu cầu lập trình viên xử lí thủ công.

8.  Git duy trì lịch sử commit như thế nào? Cấu trúc cây (tree structure) của Git giúp tối ưu hóa việc theo dõi và truy xuất lịch sử ra sao?

- Answer:
  + Git lưu lịch sử commit dưới dạng DAG, mỗi commit con sẽ trỏ đến commit cha.
  + Cấu trúc cây của Git giúp tối ưu hóa việc theo dõi và truy xuất lịch sử hiệu quả, hỗ trợ merge và bisect nhanh chóng.

9. Những lệnh này khác nhau như thế nào về bản chất? Tại sao git reset --hard có thể gây mất dữ liệu nhưng git revert thì không?

- Answer:
  + git reset --hard: Đưa HEAD và branch về commit cụ thể, xóa commit sau đó, có thể mất dữ liệu.
  + git revert: Tạo commit mới để hủy thay đổi, giữ nguyên lịch sử, không mất dữ liệu.

10. Khi dùng git commit --amend, Git thực sự làm gì? Nó có chỉnh sửa commit cũ không, hay tạo ra một commit hoàn toàn mới?

- Answer: git commit --amend tạo commit mới thay thế commit gần nhất, không chỉnh sửa commit cũ. Commit cũ bị thay thế trong lịch sử.