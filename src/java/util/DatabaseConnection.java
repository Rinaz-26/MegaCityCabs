package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/megacity_cab_db?zeroDateTimeBehavior=CONVERT_TO_NULL"; // replace with your DB URL
    private static final String DB_USERNAME = "root";  // your MySQL username
    private static final String DB_PASSWORD = "Rinaz//2000"; // your MySQL password
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            // Load the MySQL JDBC driver
            Class.forName(DB_DRIVER);
            // Establish the connection
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
