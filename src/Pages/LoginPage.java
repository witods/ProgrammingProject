package Pages;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Arrays;

public class LoginPage {
    private JPanel loginPanel;
    private JTextField inpUsername;
    private JPasswordField inpPassword;
    private JButton loginButton;
    private JButton createAccountButton;
    private JButton forgotPasswordButton;
    private JLabel loginLabel;
    private MainFrame frame;

    public LoginPage(MainFrame f) {

        loginButton.addActionListener(new LoginListener());
        createAccountButton.addActionListener(new CreateAccountListener());
        forgotPasswordButton.addActionListener(new ForgotPasswordListener());
        this.frame = f;

    }
    public JPanel getMainPanel() {
        return  this.loginPanel;
    }

    public User getUserFromDataBase(String n) {
        String DBusername, DBuserPassword, DBuserFirstName, DBuserLastName,DBuserEmail;
        int DBuserAge = 18, DBuserCredits = 1000;
        try {
            Statement statement = frame.databaseConnection.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet result = statement.executeQuery("SELECT * FROM USERS WHERE userName = \"" + n + "\";");
           if(result.first()==true) {
               result.first();
              DBusername = result.getString("userName");
               DBuserPassword = result.getString("password");
               DBuserFirstName = result.getString("firstName");
               DBuserLastName = result.getString("lastName");
               DBuserEmail = result.getString("email");
               System.out.println("Got user succesfully");
              return new User(DBusername,DBuserFirstName,DBuserLastName,DBuserPassword,DBuserAge,DBuserEmail,DBuserCredits);
            }else {
               System.out.println("Username does not exist");
               return null;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Couldnt connect to database");
            return null;
        }
    }
    public void checkPassword(User u) {
        String passwordInput = new String(inpPassword.getPassword());
        System.out.println(passwordInput);
        if(u.getUserPassword().equals(passwordInput)) {
            System.out.println("Login Succesfull");
        }
    }
    public class LoginListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            String usernameInput = inpUsername.getText();
            frame.setCurrentUser(getUserFromDataBase(usernameInput));
            checkPassword(frame.getCurrentUser());
        }
    }

    public class CreateAccountListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            frame.getCreateAccountPage();
        }
    }

    public class ForgotPasswordListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}
