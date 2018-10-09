package dao;

//This class is used to make a database connection.

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LogDataBaseConnection

{
    public static Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:db//logger.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}