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
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Failed to connect to database!");
            e.printStackTrace();
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

    public void getUserFromDatabase(String n) {

        String currentUsername,currentUserFirstName,currentUserLastName,currentUserEmail;
        int currentUserID;
        String getUserSQL = "SELECT userID,userName,firstName,lastName,email FROM USERS WHERE userName = ?";

        PreparedStatement statement;
        ResultSet result;

        try {
            statement = frame.databaseConnection.getConnection().prepareStatement(getUserSQL, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            statement.setString(1,n);
            result = statement.executeQuery();

                    if(result.first()){
                        currentUserID = result.getInt("userID");
                        currentUsername = result.getString("userName");
                        currentUserFirstName = result.getString("firstName");
                        currentUserLastName = result.getString("lastName");
                        currentUserEmail = result.getString("email");

                        frame.setCurrentUser(new User(currentUserID,currentUsername,currentUserFirstName,currentUserLastName,currentUserEmail));
                        System.out.println("Welkom" + frame.getCurrentUser().getUserName());
                    }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}