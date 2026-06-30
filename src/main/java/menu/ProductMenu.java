package menu;

import entity.Product;
import service.ProductService;
import util.InputUtil;

import java.util.List;

public class ProductMenu {

    private ProductService productService = new ProductService();

    // ===============================
    // Hiển thị danh sách
    // ===============================
    public void showAllProducts() {

        List<Product> products = productService.getAllProducts();

        if (products.isEmpty()) {
            System.out.println("Danh sách sản phẩm trống!");
            return;
        }

        System.out.println("==========================================================================");
        System.out.printf("%-5s %-25s %-15s %-15s %-10s%n",
                "ID", "Tên", "Hãng", "Giá", "Kho");
        System.out.println("==========================================================================");

        products.forEach(System.out::println);

    }

    // ===============================
    // Thêm sản phẩm
    // ===============================
    public void insertProduct() {

        System.out.println("\n===== THÊM SẢN PHẨM =====");

        Product product = new Product();

        product.setName(
                InputUtil.inputString("Tên sản phẩm: "));

        product.setBrand(
                InputUtil.inputString("Hãng: "));

        product.setPrice(
                InputUtil.inputDouble("Giá: "));

        product.setStock(
                InputUtil.inputInt("Số lượng: "));

        boolean result = productService.insertProduct(product);

        if (result) {
            System.out.println("\n>>> Thêm sản phẩm thành công!");
        } else {
            System.out.println("\n>>> Thêm sản phẩm thất bại!");
        }

    }

    // ===============================
    // Cập nhật
    // ===============================
    public void updateProduct() {

        System.out.println("\n===== CẬP NHẬT SẢN PHẨM =====");

        Product product = new Product();

        product.setProductId(
                InputUtil.inputInt("Nhập ID sản phẩm: "));

        product.setName(
                InputUtil.inputString("Tên mới: "));

        product.setBrand(
                InputUtil.inputString("Hãng mới: "));

        product.setPrice(
                InputUtil.inputDouble("Giá mới: "));

        product.setStock(
                InputUtil.inputInt("Số lượng mới: "));

        boolean result = productService.updateProduct(product);

        if (result) {
            System.out.println("\n>>> Cập nhật thành công!");
        } else {
            System.out.println("\n>>> ID sản phẩm không tồn tại!");
        }

    }

    // ===============================
    // Xóa
    // ===============================
    public void deleteProduct() {

        System.out.println("\n===== XÓA SẢN PHẨM =====");

        int id =
                InputUtil.inputInt("Nhập ID sản phẩm cần xóa: ");

        boolean confirm =
                InputUtil.confirm("Bạn có chắc chắn muốn xóa?");

        if (!confirm) {

            System.out.println(">>> Đã hủy thao tác.");

            return;

        }

        boolean result =
                productService.deleteProduct(id);

        if (result) {

            System.out.println(">>> Xóa thành công!");

        } else {

            System.out.println(">>> Không tìm thấy sản phẩm!");

        }

    }

    // ===============================
    // Tìm kiếm
    // ===============================
    public void searchProduct() {

        System.out.println("\n===== TÌM KIẾM SẢN PHẨM =====");

        System.out.println("1. Theo tên");
        System.out.println("2. Theo hãng");
        System.out.println("3. Theo khoảng giá");
        System.out.println("4. Sản phẩm còn hàng");

        int choice =
                InputUtil.inputInt("Chọn: ");

        List<Product> products = null;

        switch (choice) {

            case 1:

                String keyword =
                        InputUtil.inputString("Nhập tên: ");

                products =
                        productService.searchByName(keyword);

                break;

            case 2:

                String brand =
                        InputUtil.inputString("Nhập hãng: ");

                products =
                        productService.searchByBrand(brand);

                break;

            case 3:

                double min =
                        InputUtil.inputDouble("Giá nhỏ nhất: ");

                double max =
                        InputUtil.inputDouble("Giá lớn nhất: ");

                products =
                        productService.searchByPrice(min, max);

                break;

            case 4:

                products =
                        productService.searchByStock();

                break;

            default:

                System.out.println("Lựa chọn không hợp lệ!");
                return;

        }

        if (products == null || products.isEmpty()) {

            System.out.println("\nKhông tìm thấy sản phẩm.");

            return;

        }

        System.out.println("\n==========================================================================");
        System.out.printf("%-5s %-25s %-15s %-15s %-10s%n",
                "ID", "Tên", "Hãng", "Giá", "Kho");
        System.out.println("==========================================================================");

        products.forEach(System.out::println);

    }
    public void start() {

        while (true) {

            System.out.println();
            System.out.println("==================================================");
            System.out.println("             QUẢN LÝ SẢN PHẨM");
            System.out.println("==================================================");
            System.out.println("1. Xem danh sách");
            System.out.println("2. Thêm sản phẩm");
            System.out.println("3. Cập nhật sản phẩm");
            System.out.println("4. Xóa sản phẩm");
            System.out.println("5. Tìm kiếm sản phẩm");
            System.out.println("0. Quay lại");
            System.out.println("==================================================");
            System.out.print("Chọn: ");

            int choice = InputUtil.inputInt("");

            switch (choice) {

                case 1:
                    showAllProducts();
                    break;

                case 2:
                    insertProduct();
                    break;

                case 3:
                    updateProduct();
                    break;

                case 4:
                    deleteProduct();
                    break;

                case 5:
                    searchProduct();
                    break;

                case 0:
                    return;

                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

}