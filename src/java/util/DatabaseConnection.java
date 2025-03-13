package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/megacity_cab_db?zeroDateTimeBehavior=CONVERT_TO_NULL";
    private static final String DB_USERNAME = "root"; 
    private static final String DB_PASSWORD = "Rinaz//2000"; 
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";

    public static Connection getConnection() {
         Connection connection = null;
        try {
            
            Class.forName(DB_DRIVER);
            
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            System.out.println("Database connection established successfully.");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error while connecting to the database: " + e.getMessage());
        }
        return connection;
    }
}
