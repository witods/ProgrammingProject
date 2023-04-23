package Pages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginPage {

    //Declareren van de Instance fields
    private JPanel loginPanel;
    private JTextField inpUsername;
    private JPasswordField inpPassword;
    private JButton loginButton;
    private JButton createAccountButton;
    private JButton forgotPasswordButton;
    private JLabel loginLabel;
    private JLabel userNameMessage;
    private JLabel passwordMessage;
    private JButton backButton;
    private MainFrame frame;

    //Mainframe wordt doorgegeven om aan de user en database connectie te kunnen aanroepen
    public LoginPage(MainFrame f) {
        loginButton.addActionListener(new LoginListener());
        createAccountButton.addActionListener(new CreateAccountListener());
        forgotPasswordButton.addActionListener(new ForgotPasswordListener());
        this.frame = f;

    }

    //Getter voor de mainPanel om als contentpane in Main Frame te zetten
    public JPanel getMainPanel() {
        return  this.loginPanel;
    }

    public int checkUsername(String s) {
        String userNamesSQL = "SELECT userID,userName FROM USERS WHERE userName = '" + s +"'";
        PreparedStatement statement;
        ResultSet resultSet;
        try {
            statement = this.frame.databaseConnection.getConnection().prepareStatement(userNamesSQL);
            resultSet = statement.executeQuery();

            if(resultSet.first()){

                    userNameMessage.setForeground(Color.green);
                    userNameMessage.setText("Existing username");
                    return resultSet.getInt("userID");

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
    //Controleer of het ingegeven wachtwoord overeenkomt met de currentUser
    public void checkPassword(int i, String p) {
        String getPasswordSQL = "SELECT encryptedPassword FROM ENCRYPTEDDATA WHERE userID = ?";
        PreparedStatement statement;
        ResultSet resultSet;

        try {
            statement = this.frame.databaseConnection.getConnection().prepareStatement(getPasswordSQL);
            statement.setInt(1,i);
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                if(PasswordEncryption.checkPassword(p,resultSet.getString("encryptedPassword"))) {
                    frame.databaseConnection.getUserFromDatabase(inpUsername.getText());
                    frame.getHomePage();
                }
                else passwordMessage.setText("Wrong password, try again");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public class LoginListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            userNameMessage.setText("");
            passwordMessage.setText("");
            String usernameInput = inpUsername.getText();
            String passwordInput = new String(inpPassword.getPassword());
            int userID = checkUsername(usernameInput);
            if (userID > 0) {
                checkPassword(userID, passwordInput);
            } else {
                userNameMessage.setForeground(new Color(116, 15, 0));
                userNameMessage.setText("Invalid username");
            }
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
        public void actionPerformed(ActionEvent e) {frame.getForgotPassword();}
    }
    public class backActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {}
    }
}
