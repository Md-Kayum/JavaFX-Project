package pkg223061008_project.techmentor;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/TechmentorFx", "root", "1234"
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
