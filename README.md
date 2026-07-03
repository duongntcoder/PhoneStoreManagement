# 📱 Phone Store Management System

Hệ thống quản lý cửa hàng điện thoại được phát triển bằng **Java Console** kết hợp **PostgreSQL** theo mô hình **3-Layer Architecture (Menu - Service - DAO)**.

---

## 👨‍💻 Tác giả

- Nguyễn Tùng Dương
- Java Console Project
- Trường Đại học Phenikaa
- Khoá Java Backend
---

# 📖 Giới thiệu

Hệ thống hỗ trợ quản lý toàn bộ hoạt động của một cửa hàng điện thoại.

Sau khi đăng nhập bằng tài khoản Admin, người dùng có thể:

- Quản lý sản phẩm
- Quản lý khách hàng
- Quản lý hóa đơn
- Thống kê doanh thu
- Tìm kiếm dữ liệu bằng Stream API

---

# 🛠 Công nghệ sử dụng

- Java 21
- PostgreSQL
- JDBC
- Maven
- BCrypt
- Java Stream API
- IntelliJ IDEA

---

# 🏗 Kiến trúc hệ thống

```
Main
   │
LoginMenu
   │
MainMenu
   │
Menu Layer
   │
Service Layer
   │
DAO Layer
   │
PostgreSQL
```

Dự án được xây dựng theo mô hình 3 lớp:

- Presentation Layer (Menu)
- Business Layer (Service)
- Data Access Layer (DAO)

---

# 📂 Cấu trúc thư mục

```
src
 ├── config
 │      JDBCUtil.java
 │
 ├── dao
 │      AdminDAO.java
 │      CustomerDAO.java
 │      ProductDAO.java
 │      InvoiceDAO.java
 │
 ├── entity
 │      Admin.java
 │      Customer.java
 │      Product.java
 │      Invoice.java
 │      InvoiceDetail.java
 │
 ├── menu
 │      LoginMenu.java
 │      MainMenu.java
 │      ProductMenu.java
 │      CustomerMenu.java
 │      InvoiceMenu.java
 │
 ├── service
 │      AdminService.java
 │      CustomerService.java
 │      ProductService.java
 │      InvoiceService.java
 │
 └── utils
        InputUtil.java
        FormatUtil.java
```

---

# 🔐 Chức năng đăng nhập

- Đăng ký Admin
- Đăng nhập Admin
- Mã hóa mật khẩu bằng BCrypt
- Kiểm tra Email
- Kiểm tra Password
- Không cho phép nhập rỗng

---

# 📱 Quản lý sản phẩm

✔ Xem danh sách

✔ Thêm sản phẩm

✔ Cập nhật sản phẩm

✔ Xóa sản phẩm

✔ Tìm kiếm

- Theo tên
- Theo hãng
- Theo khoảng giá
- Sản phẩm còn hàng

---

# 👤 Quản lý khách hàng

✔ Xem danh sách

✔ Thêm khách hàng

✔ Cập nhật khách hàng

✔ Xóa khách hàng

✔ Tìm kiếm

- Theo tên
- Theo Email
- Theo số điện thoại

✔ Kiểm tra Email trùng

---

# 🧾 Quản lý hóa đơn

✔ Xem danh sách

✔ Tạo hóa đơn

✔ Tìm kiếm hóa đơn

- Theo khách hàng
- Theo ngày

✔ Tự động giảm số lượng tồn kho

---

# 💰 Thống kê doanh thu

Thống kê theo

- Ngày
- Tháng
- Năm

Hiển thị doanh thu theo định dạng tiền Việt Nam.

Ví dụ

```
Doanh thu: 100.000.000 VNĐ
```

---

# ✅ Validation

Hệ thống kiểm tra:

- Không nhập rỗng
- Email đúng định dạng
- Password tối thiểu 6 ký tự
- Giá > 0
- Không nhập chữ thay số
- Xác nhận khi xóa
- Kiểm tra Email trùng
- Kiểm tra tồn kho

---

# 🔒 Bảo mật

- BCrypt Password Hashing
- JDBC PreparedStatement
- Không lưu Plain Text Password

---

# 🗄 Database

Các bảng sử dụng

- admin
- customer
- product
- invoice
- invoice_detail

---

# 🚀 Hướng dẫn chạy

## Clone project

```bash
git clone https://github.com/USERNAME/PhoneStoreManagement.git
```

## Import Maven

```
Open Project
↓
Maven Reload
```

## Tạo Database PostgreSQL

Import file SQL.

## Cập nhật JDBC

Trong

```
config/JDBCUtil.java
```

sửa

- URL
- Username
- Password

theo PostgreSQL của bạn.

## Chạy chương trình

Run

```
Main.java
```

---

# 📸 Giao diện

## Đăng nhập


## Quản lý sản phẩm


## Quản lý khách hàng


## Quản lý hóa đơn


## Thống kê doanh thu


---

# ⭐ Một số điểm nổi bật

- 3 Layer Architecture
- Stream API
- JDBC
- BCrypt
- Maven
- PostgreSQL
- Validation đầy đủ
- Console UI đẹp
- Hệ thống CRUD hoàn chỉnh

---

# 📄 License

Project phục vụ mục đích học tập.
