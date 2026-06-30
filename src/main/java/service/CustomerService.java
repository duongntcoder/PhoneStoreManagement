package service;

import dao.CustomerDAO;
import entity.Customer;

import java.util.List;
import java.util.stream.Collectors;

public class CustomerService {

    private CustomerDAO customerDAO = new CustomerDAO();

    // Hiển thị danh sách
    public List<Customer> getAllCustomers() {
        return customerDAO.getAllCustomers();
    }

    // Thêm khách hàng
    public boolean insertCustomer(Customer customer) {
        return customerDAO.insertCustomer(customer);
    }

    // Sửa khách hàng
    public boolean updateCustomer(Customer customer) {
        return customerDAO.updateCustomer(customer);
    }

    // Xóa khách hàng
    public boolean deleteCustomer(int customerId) {
        return customerDAO.deleteCustomer(customerId);
    }

    // Tìm theo tên
    public List<Customer> searchByName(String keyword) {

        return customerDAO.getAllCustomers()
                .stream()
                .filter(customer ->
                        customer.getFullName()
                                .toLowerCase()
                                .contains(keyword.toLowerCase()))
                .collect(Collectors.toList());

    }

    // Tìm theo số điện thoại
    public List<Customer> searchByPhone(String phone) {

        return customerDAO.getAllCustomers()
                .stream()
                .filter(customer ->
                        customer.getPhone().contains(phone))
                .collect(Collectors.toList());

    }

    // Tìm theo email
    public List<Customer> searchByEmail(String email) {

        return customerDAO.getAllCustomers()
                .stream()
                .filter(customer ->
                        customer.getEmail()
                                .toLowerCase()
                                .contains(email.toLowerCase()))
                .collect(Collectors.toList());

    }
    public Customer findById(int id){

        return customerDAO.findById(id);

    }
    public boolean existsEmail(String email) {

        return customerDAO.existsEmail(email);

    }
    public boolean existsEmail(String email, int customerId) {

        return customerDAO.existsEmail(email, customerId);

    }

}