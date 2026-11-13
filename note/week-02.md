# Ghi chú tuần 02: Maven Phase, Goal & Dependency Mediation

---

## Phần B: Phân biệt Phase và Goal

### Câu 1: Khác nhau giữa chạy một Phase (`mvn test`) và chạy một Goal (`mvn surefire:test`)
* **Chạy Phase (`mvn test`):** Maven thực thi theo quy trình tích lũy, tự động chạy toàn bộ các phase đứng trước nó trong Default Lifecycle (`validate` → `compile` → `test-compile` → `test`). Điều này đảm bảo source code và test code được biên dịch mới nhất trước khi chạy test.
* **Chạy Goal (`mvn surefire:test`):** Chỉ thực thi duy nhất goal đó một cách độc lập. Maven **không** tự động compile lại code; nếu thư mục `target/classes` hoặc `target/test-classes` chưa có hoặc code chưa cập nhật, lệnh có thể báo lỗi hoặc test trên file `.class` cũ.

---

## Phần C: Dependency bắc cầu & Quy tắc "Gần gốc nhất thắng"

### 1. Hai quy tắc giải quyết xung đột (Dependency Mediation)
Khi xảy ra xung đột phiên bản giữa các dependency, Maven áp dụng theo thứ tự ưu tiên:
1. **Nearest-Wins (Gần gốc nhất thắng - Ưu tiên cao nhất):** Dependency nào có độ sâu nhỏ hơn (gần file `pom.xml` gốc hơn trong cây dependency tree) sẽ **luôn thắng**, bất kể phiên bản cao hay thấp, khai báo trước hay sau.
2. **First-Declaration-Wins (Khai báo trước thắng - Quy tắc phụ):** Chỉ áp dụng khi các dependency xung đột nằm ở **cùng một độ sâu**. Nhánh nào được khai báo trước trong `pom.xml` thì phiên bản của nhánh đó sẽ được chọn.

### 2. Khi nào cần dùng `<exclusion>`?
* Sử dụng thẻ `<exclusion>` khi muốn **loại bỏ hoàn toàn** một dependency bắc cầu (transitive dependency) không mong muốn (ví dụ: thư viện dính lỗi bảo mật CVE, thư viện quá nặng không dùng tới, hoặc xung đột trùng lặp class do khác `groupId:artifactId` như trong hệ thống Logging).

---

## Các câu hỏi mở rộng

### Câu 2: Vì sao bản `checker-qual` khai báo trực tiếp lại thắng bản do `guava` kéo theo?
* **Giải thích:**
  * Bản `checker-qual` khai báo trực tiếp trong `pom.xml` là **Direct Dependency** (độ sâu = 1).
  * Bản `checker-qual` do `guava` kéo theo là **Transitive Dependency** (độ sâu = 2).
  * Theo quy tắc **Nearest-Wins** (độ sâu 1 < độ sâu 2), bản `checker-qual` khai báo trực tiếp luôn được Maven chọn mà không bắt buộc phải dùng `<exclusion>`.

### Câu 3: `mvn install` khác `npm install` ở điểm nào?
* **`npm install` (Node.js):** Cài đặt các package từ registry bên ngoài **vào** thư mục cục bộ của dự án (`node_modules/`).
* **`mvn install` (Maven):** Đóng gói sản phẩm của chính dự án này (`.jar` / `.war`) và đem cài **vào** kho lưu trữ dùng chung trên máy tính cá nhân (`~/.m2/repository`) để các dự án khác trên cùng máy có thể sử dụng làm dependency.
