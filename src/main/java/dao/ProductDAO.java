package dao;

import config.JDBCUtil;
import entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    // Lấy danh sách tất cả sản phẩm
    public List<Product> getAllProducts() {

        List<Product> products = new ArrayList<>();

        String sql = "SELECT * FROM product ORDER BY product_id";

        try {

            Connection connection = JDBCUtil.getConnection();

            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Product product = new Product();

                product.setProductId(rs.getInt("product_id"));
                product.setName(rs.getString("product_name"));
                product.setBrand(rs.getString("brand"));
                product.setPrice(rs.getDouble("price"));
                product.setStock(rs.getInt("stock_quantity"));
                products.add(product);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return products;
    }


    public boolean insertProduct(Product product) {

        try {

            Connection connection = JDBCUtil.getConnection();

            String sql =
                    "INSERT INTO product(product_name,brand,price,stock_quantity) VALUES(?,?,?,?)";

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, product.getName());
            ps.setString(2, product.getBrand());
            ps.setDouble(3, product.getPrice());
            ps.setInt(4, product.getStock());

            int result = ps.executeUpdate();

            connection.close();

            return result > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    // Cập nhật sản phẩm
    public boolean updateProduct(Product product) {

        try {

            Connection connection = JDBCUtil.getConnection();

            String sql = """
                UPDATE product
                SET product_name = ?,
                    brand = ?,
                    price = ?,
                    stock_quantity = ?
                WHERE product_id = ?
                """;

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, product.getName());
            ps.setString(2, product.getBrand());
            ps.setDouble(3, product.getPrice());
            ps.setInt(4, product.getStock());
            ps.setInt(5, product.getProductId());

            int result = ps.executeUpdate();

            connection.close();

            return result > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    // Xóa sản phẩm
    public boolean deleteProduct(int productId){

        try{

            Connection connection = JDBCUtil.getConnection();

            String sql = "DELETE FROM product WHERE product_id=?";

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, productId);

            int result = ps.executeUpdate();

            connection.close();

            return result > 0;

        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }
    public Product findById(int productId) {

        try {

            Connection connection = JDBCUtil.getConnection();

            String sql =
                    "SELECT * FROM product WHERE product_id=?";

            PreparedStatement ps =
                    connection.prepareStatement(sql);

            ps.setInt(1, productId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Product product = new Product();

                product.setProductId(rs.getInt("product_id"));
                product.setName(rs.getString("product_name"));
                product.setBrand(rs.getString("brand"));
                product.setPrice(rs.getDouble("price"));
                product.setStock(rs.getInt("stock_quantity"));

                connection.close();

                return product;
            }

            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    public boolean updateStock(int productId, int quantitySold) {

        try {

            Connection connection = JDBCUtil.getConnection();

            String sql =
                    "UPDATE product SET stock_quantity = stock_quantity - ? WHERE product_id=?";

            PreparedStatement ps =
                    connection.prepareStatement(sql);

            ps.setInt(1, quantitySold);
            ps.setInt(2, productId);

            int result = ps.executeUpdate();

            connection.close();

            return result > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}