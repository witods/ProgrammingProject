package Pages;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class CreateAccount {

    //Declareren van Instance fields
    private JPanel mainPanel;
    private JButton AlreadyHaveAccountButton;
    private JButton CreateAccountConfirmButton;
    private JTextField inpEmail;
    private JTextField inpFirstName;
    private JTextField inpLastName;
    private JPasswordField inpConfirmPassword;
    private JPasswordField inpPassword;
    private JTextField inpUsername;
    private JLabel userNameMessage;
    private JLabel passwordMessage;
    private JLabel emailMessage;
    private JLabel firstNameMessage;
    private JLabel confirmPasswordLabel;
    private JLabel lastNameMessage;
    private JLabel createAccountLabel;
    private JButton backButton;
    private MainFrame frame;

    //Mainframe wordt doorgegeven om aan de user en database connectie te kunnen aanroepen
    public CreateAccount(MainFrame f) {
        AlreadyHaveAccountButton.addActionListener(new AlreadyHaveAccountListener());
        CreateAccountConfirmButton.addActionListener(new CreateAccountListener());
        backButton.addActionListener(new backActionListener());
        this.frame = f;
    }

    //Getter voor de mainPanel om als contentpane in Main Frame te zetten
    public JPanel getMainPanel() {return mainPanel;}

    //Controleren of een ingegeven gebruikersnaam al bestaat in de database
    public boolean checkValidUsername() throws SQLException {

        String getUsersString = "SELECT userName from USERS";
        ArrayList<String> existingUsernames = new ArrayList<>();
        PreparedStatement getExistingUsers;
        ResultSet existingUsernamesResult;

        getExistingUsers = frame.databaseConnection.getConnection().prepareStatement(getUsersString);
        existingUsernamesResult = getExistingUsers.executeQuery();

        while(existingUsernamesResult.next()){
            String name = existingUsernamesResult.getString("userName");
            existingUsernames.add(name);
        }
        if (existingUsernames.contains(inpUsername.getText())&&inpUsername.getText().length() <= 3){
            userNameMessage.setText("Invalid username");
            return false;
        }else userNameMessage.setText("Valid username"); return true;
    }
    //Controleren of een ingegeven mail al bestaat in de database
    public boolean checkValidMail() throws SQLException {

            String getMailString = "SELECT email FROM USERS";
            ArrayList<String> existingMails = new ArrayList<>();
            PreparedStatement getExistingMails;
            ResultSet existingMailsResult;

            getExistingMails = frame.databaseConnection.getConnection().prepareStatement(getMailString);
            existingMailsResult = getExistingMails.executeQuery();

            while(existingMailsResult.next()){
                String name = existingMailsResult.getString("email");
                existingMails.add(name);
            }
            if (existingMails.contains(inpEmail.getText())){
                emailMessage.setText("Mail already in use");
                return false;
            }else if(inpEmail.getText().contains("@")&&inpEmail.getText().contains(".")) return true;
            else emailMessage.setText("Invalid Email adress"); return false;
        }
    public boolean checkValidPassword(){
        String pass = new String(inpPassword.getPassword());
        if(pass.equals(new String(inpConfirmPassword.getPassword()))){
            if(pass.length()>=6){
                return true;
            }else passwordMessage.setText("Pick a password of at least 6 characters"); return false;
        }
         else confirmPasswordLabel.setText("Enter the same password twice"); return false;
    }
    //Als alle velden geldig zijn user wegschrijven naar de database
    public void createAccount() throws SQLException {
        String createString;
        createString = "INSERT INTO USERS (userName, firstName, lastName, email, password) values(?,?,?,?,?)";
        PreparedStatement createUserQuery = frame.databaseConnection.getConnection().prepareStatement(createString);;

        if((new String(inpPassword.getPassword()).equals(new String(inpConfirmPassword.getPassword())))){

                createUserQuery.setString(1,inpUsername.getText());
                createUserQuery.setString(2,inpFirstName.getText());
                createUserQuery.setString(3,inpLastName.getText());
                createUserQuery.setString(4,inpEmail.getText());
                createUserQuery.setString(5,new String(inpPassword.getPassword()));

                if(createUserQuery.executeUpdate()!=0){
                    System.out.println("User aangemaakt");
                }else System.out.println("User niet aangemaakkt");
        }
        else confirmPasswordLabel.setText("Enter the same password twice");

    }
    //Inner class als Action Listener van AlreadyHaveAccount button, keert terug naar Login Page
    public class AlreadyHaveAccountListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.getLoginPage();
        }
    }
    //Inner class als Action Listener van Create Account button, start controle van alle velden
    public class CreateAccountListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (checkValidMail()&& checkValidUsername()&&checkValidPassword()){
                    createAccount();
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    public class backActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {frame.getLoginPage();}
    }
}
