package Pages;

import java.sql.*;
public class DatabaseConnection {
    private static final String url = "jdbc:mysql://dt5.ehb.be/2223PROGPROJGR3";
    private static final String username = "2223PROGPROJGR3";
    private static final String password = "QSUOMC";
    private MainFrame frame;
    private Connection con;

    public DatabaseConnection(MainFrame f) {
        this.frame = f;
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

    public User getUserFromDatabase(String n) {

        String DBusername, DBuserPassword, DBuserFirstName, DBuserLastName,DBuserEmail;
        String getUserSQL = "SELECT * FROM USERS WHERE userName = ?";

        PreparedStatement statement = null;
        try {
            statement = frame.databaseConnection.getConnection().prepareStatement(getUserSQL, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            statement.setString(1,n);
            ResultSet result = statement.executeQuery();

                    if(result.first()){
                        DBusername = result.getString("userName");
                        DBuserPassword = result.getString("password");
                        DBuserFirstName = result.getString("firstName");
                        DBuserLastName = result.getString("lastName");
                        DBuserEmail = result.getString("email");
                        frame.setCurrentUser(new User(DBusername,DBuserFirstName,DBuserLastName,DBuserPassword,DBuserEmail));
                        System.out.println("Welkom" + frame.getCurrentUser().getUserName());
                    }
                    return frame.getCurrentUser();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}