package service;

import dao.AdminDAO;
import entity.Admin;

public class AdminService {

    private AdminDAO adminDAO = new AdminDAO();

    // Đăng nhập
    public Admin login(String email, String password) {

        return adminDAO.login(email, password);

    }

    // Đăng ký
    public boolean register(Admin admin) {

        return adminDAO.register(admin);

    }
    public boolean existsEmail(String email){

        return adminDAO.existsEmail(email);

    }

}