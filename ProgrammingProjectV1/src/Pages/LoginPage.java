package Pages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Arrays;

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


    //Controleer of het ingegeven wachtwoord overeenkomt met de currentUser
    public void checkPassword(User u) {

        String passwordInput = new String(inpPassword.getPassword());
        if(u.getUserPassword().equals(passwordInput)) {
            //frame.getHomePage();
            System.out.println("SUcces");
        } else passwordMessage.setText("Wrong password, try again");
    }
    public class LoginListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            userNameMessage.setText("");
            passwordMessage.setText("");
            String usernameInput = inpUsername.getText();
            frame.databaseConnection.getUserFromDatabase(usernameInput);
            if(frame.getCurrentUser()!=null){
                userNameMessage.setForeground(Color.green);
                userNameMessage.setText("Existing username");
                checkPassword(frame.getCurrentUser());
            } else {
                userNameMessage.setForeground(new Color(116,15,0));
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
}
