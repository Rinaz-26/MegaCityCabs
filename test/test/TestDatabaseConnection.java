package test;

import util.DatabaseConnection;
import java.sql.Connection;
import java.sql.SQLException;

public class TestDatabaseConnection {

    public static void main(String[] args) {
        // Get database connection
        Connection connection = DatabaseConnection.getConnection();

        // Test if connection is successful
        if (connection != null) {
            System.out.println("Database connection successful!");
        } else {
            System.out.println("Failed to connect to the database.");
        }

        // Close the connection (optional)
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
