package service;

import dao.ProductDAO;
import entity.Product;

import java.util.List;
import java.util.stream.Collectors;

public class ProductService {

    private ProductDAO productDAO = new ProductDAO();

    // Hiển thị danh sách
    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }

    // Thêm sản phẩm
    public boolean insertProduct(Product product) {
        return productDAO.insertProduct(product);
    }

    // Sửa sản phẩm
    public boolean updateProduct(Product product) {
        return productDAO.updateProduct(product);
    }

    // Xóa sản phẩm
    public boolean deleteProduct(int productId) {
        return productDAO.deleteProduct(productId);
    }

    // Tìm theo tên
    public List<Product> searchByName(String keyword) {

        return productDAO.getAllProducts()
                .stream()
                .filter(product -> product.getName()
                        .toLowerCase()
                        .contains(keyword.toLowerCase()))
                .collect(Collectors.toList());

    }

    // Tìm theo hãng
    public List<Product> searchByBrand(String brand) {

        return productDAO.getAllProducts()
                .stream()
                .filter(product -> product.getBrand()
                        .equalsIgnoreCase(brand))
                .collect(Collectors.toList());

    }

    // Tìm theo khoảng giá
    public List<Product> searchByPrice(double min, double max) {

        return productDAO.getAllProducts()
                .stream()
                .filter(product -> product.getPrice() >= min
                        && product.getPrice() <= max)
                .collect(Collectors.toList());

    }

    // Tìm sản phẩm còn hàng
    public List<Product> searchByStock() {

        return productDAO.getAllProducts()
                .stream()
                .filter(product -> product.getStock() > 0)
                .collect(Collectors.toList());

    }
    public Product findById(int productId){

        return productDAO.findById(productId);

    }
    public boolean updateStock(int productId,int quantity){

        return productDAO.updateStock(productId,quantity);

    }

}