package checkuserpassword;

import Pages.DatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


public class CheckUsernameAndPassword {
    private String username="wouter";
    private String password="123456789";
    //private DatabaseConnection databaseConnection = new DatabaseConnection();
    //private Connection connection;
    //private String sqlquerry="select *from user";
    public CheckUsernameAndPassword(String username, String password){
        this.username=username;
        this.password=password;

    }

    public boolean check(){
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection=databaseConnection.getConnection();
        try {
            ResultSet r = connection.createStatement().executeQuery("SELECT * from USERS ");
                while (r.next()){
                    if (username.equals(r.getString("userName"))&&password.equals(r.getString("password")))
                        return true;
                }
        }
        catch (SQLException ex) {
            System.out.println(ex);
        }
    return false;

    }

    public static void main(String[] args) {
        CheckUsernameAndPassword checker = new CheckUsernameAndPassword("Wouter", "123456789");
        System.out.println(checker.check());

    }

}
