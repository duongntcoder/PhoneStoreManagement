package dao;

import config.JDBCUtil;
import entity.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    // ===== Lấy danh sách khách hàng =====
    public List<Customer> getAllCustomers() {

        List<Customer> customers = new ArrayList<>();

        String sql = "SELECT * FROM customer ORDER BY customer_id";

        try {

            Connection connection = JDBCUtil.getConnection();

            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Customer customer = new Customer();

                customer.setCustomerId(rs.getInt("customer_id"));
                customer.setFullName(rs.getString("customer_name"));
                customer.setPhone(rs.getString("phone"));
                customer.setEmail(rs.getString("email"));
                customer.setAddress(rs.getString("address"));

                customers.add(customer);

            }

            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return customers;
    }

    // ===== Thêm khách hàng =====
    public boolean insertCustomer(Customer customer){

        try{

            Connection connection = JDBCUtil.getConnection();

            String sql =
                    "INSERT INTO customer(customer_name,phone,email,address) VALUES(?,?,?,?)";

            PreparedStatement ps =
                    connection.prepareStatement(sql);

            ps.setString(1, customer.getFullName());
            ps.setString(2, customer.getPhone());
            ps.setString(3, customer.getEmail());
            ps.setString(4, customer.getAddress());

            int result = ps.executeUpdate();

            connection.close();

            return result > 0;

        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }
    public boolean updateCustomer(Customer customer){

        try{

            Connection connection = JDBCUtil.getConnection();

            String sql =
                    "UPDATE customer SET customer_name=?, phone=?, email=?, address=? WHERE customer_id=?";

            PreparedStatement ps =
                    connection.prepareStatement(sql);

            ps.setString(1, customer.getFullName());
            ps.setString(2, customer.getPhone());
            ps.setString(3, customer.getEmail());
            ps.setString(4, customer.getAddress());
            ps.setInt(5, customer.getCustomerId());

            int result = ps.executeUpdate();

            connection.close();

            return result > 0;

        }catch (Exception e){

            e.printStackTrace();

        }

        return false;

    }
    // Xóa khách hàng
    public boolean deleteCustomer(int customerId) {

        try {

            Connection connection = JDBCUtil.getConnection();

            String sql = "DELETE FROM customer WHERE customer_id = ?";

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, customerId);

            int result = ps.executeUpdate();

            connection.close();

            return result > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    public Customer findById(int customerId){

        try{

            Connection connection = JDBCUtil.getConnection();

            String sql = "SELECT * FROM customer WHERE customer_id=?";

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, customerId);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){

                Customer customer = new Customer();

                customer.setCustomerId(rs.getInt("customer_id"));
                customer.setFullName(rs.getString("customer_name"));
                customer.setPhone(rs.getString("phone"));
                customer.setEmail(rs.getString("email"));
                customer.setAddress(rs.getString("address"));

                connection.close();

                return customer;

            }

            connection.close();

        }catch(Exception e){
            e.printStackTrace();
        }

        return null;

    }
    public boolean existsEmail(String email) {

        String sql = "SELECT 1 FROM customer WHERE email = ?";

        try {

            Connection connection = JDBCUtil.getConnection();

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            boolean exists = rs.next();

            rs.close();
            ps.close();
            connection.close();

            return exists;

        } catch (Exception e) {

            e.printStackTrace();

        }

        return false;

    }
    public boolean existsEmail(String email, int customerId) {

        String sql = """
        SELECT 1
        FROM customer
        WHERE email = ?
        AND customer_id <> ?
        """;

        try {

            Connection connection = JDBCUtil.getConnection();

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, email);
            ps.setInt(2, customerId);

            ResultSet rs = ps.executeQuery();

            boolean exists = rs.next();

            rs.close();
            ps.close();
            connection.close();

            return exists;

        } catch (Exception e) {

            e.printStackTrace();

        }

        return false;
    }

}