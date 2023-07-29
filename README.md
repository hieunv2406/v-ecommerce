# VM_Ecommerce project

## Getting started
This is an e-commerce web application written in Java programming language and using Spring framework, combined with new and popular libraries.

## Requirement
- Java JDK 8+
- Maven 3.x+
- Database: Mysql 8+

## Run and Deploy

```
cd existing_repo
run in command: docker-compose -f docker-database-compose.yml up -d
mvn clean install
run file: VmEcommerceApplication.java
```
## Project Structure

## Features
* Người dùng:
    - Xác thực, phân quyền:
        + Đăng ký, Đăng nhập (*)
        + Xem, Sửa thông tin User (*)
        + Lịch sử mua/bán
        + Thông báo hệ thống/ ứng dụng
        + Ds sản phẩm yêu thích (*)
    - Sản phẩm:
        + Ds sản phẩm (lọc, tìm kiếm)
        + Thông tin chi tiết sản phẩm
        + Ds danh mục
        + Ds sản theo danh mục
        + Thêm sản phẩm yêu thích (*)
    - Mua/Bán:
        + Xem giỏ (Ds sản phẩm trong giỏ) (*)
        + Thêm sản phẩm vào giỏ, thay đổi số lượng, xóa sản phẩm trong giỏ (*)
        + Tạo Order, xem chi tiết order (*)
        + kết nối tính năng thanh toán qua bên thứ 3
    - Thống kê:
        + Ds sản phẩm đã mua ( tổng tiền, danh mục thường mua hay yêu thích, sản phẩm yêu thích)

* Người quản trị:
    - User, xác thực, phân quyền:
        + Tạo Roles
        + Set Roles
        + Xem, quản lý danh sách User (Xóa, sửa User)
    - Sản phẩm, Danh mục:
        + Ds, thêm, sửa, xóa sản phẩm
        + xuất file ds sản phẩm theo các tiêu chí
        + Ds danh mục, thêm, sửa, xóa danh mục
    - Mua/Bán:
        + Ds order
        + Ds order chưa xử lý, đã xử lý, tạm dừng, đã duyệt
        + Phê duyệt, xóa, từ chối order
          -Báo cáo, thống kê:
        + Doanh thu theo từng ngày, quý, năm
        + Ds sản phẩm ưa chuộng/ được yêu thích
        + Xuất báo cáo doanh thu theo sản phẩm, danh mục
        + Sản phẩm, danh mục được yêu thích, bán chạy
***
