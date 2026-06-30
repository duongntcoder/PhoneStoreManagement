package dao;

import config.JDBCUtil;
import entity.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.mindrot.jbcrypt.BCrypt;

public class AdminDAO {

    public Admin login(String email, String password) {

        String sql = """
        SELECT *
        FROM admin
        WHERE email = ?
        """;

        try {

            Connection connection = JDBCUtil.getConnection();

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                String hashPassword = rs.getString("password_hash");

                if (BCrypt.checkpw(password, hashPassword)) {

                    Admin admin = new Admin();

                    admin.setAdminId(rs.getInt("admin_id"));
                    admin.setFullName(rs.getString("full_name"));
                    admin.setEmail(rs.getString("email"));
                    admin.setPasswordHash(hashPassword);
                    admin.setRole(rs.getString("role"));

                    connection.close();

                    return admin;

                }

            }

            connection.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

        return null;

    }
    public boolean existsEmail(String email) {

        String sql = "SELECT 1 FROM admin WHERE email = ?";

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
    public boolean register(Admin admin){

        String sql =
                "INSERT INTO admin(full_name,email,password_hash,role) VALUES(?,?,?,?)";

        try{

            Connection connection =
                    JDBCUtil.getConnection();

            PreparedStatement ps =
                    connection.prepareStatement(sql);

            ps.setString(1, admin.getFullName());

            ps.setString(2, admin.getEmail());

            String hashPassword =
                    BCrypt.hashpw(admin.getPasswordHash(), BCrypt.gensalt());

            ps.setString(3, hashPassword);

            ps.setString(4, admin.getRole());

            int result =
                    ps.executeUpdate();

            connection.close();

            return result > 0;

        }catch(Exception e){

            e.printStackTrace();

        }

        return false;

    }

}