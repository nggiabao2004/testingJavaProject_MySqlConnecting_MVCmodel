package model;
import java.sql.*;

public class Database {
    private static final String URL = "jdbc:mysql://localhost:3306/databaseuser";
    private static final String USER = "root";
    private static final String PASSWORD = "02112004";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
