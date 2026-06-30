package config;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCUtil {

    private static final String URL =
            "jdbc:postgresql://localhost:5435/phone_store";

    private static final String USER =
            "nguyentungduong";

    private static final String PASSWORD =
            "";

    public static Connection getConnection() {

        try {
            return DriverManager.getConnection(
                    URL,
                    USER,
                    PASSWORD
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}