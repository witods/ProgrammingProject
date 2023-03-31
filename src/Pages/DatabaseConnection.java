package Pages;

import java.sql.*;
public class DatabaseConnection {
    private static final String url = "jdbc:mysql://dt5.ehb.be/2223PROGPROJGR3";
    private static final String username = "2223PROGPROJGR3";
    private static final String password = "QSUOMC";

    private Connection con;

    public DatabaseConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC driver not found!");
        } catch (SQLException e) {
            System.out.println("Failed to connect to database!");
        }
    }

    public Connection getConnection() {
        return con;
    }

    public void close() {
        try {
            con.close();
            System.out.println("Database connection closed!");
        } catch (SQLException e) {
            System.out.println("Failed to close database connection!");
        }
    }
}