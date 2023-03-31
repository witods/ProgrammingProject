package Pages;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class CreateAccount {
    private JPanel mainPanel;
    private JButton AlreadyHaveAccountButton;
    private JButton CreateAccountConfirmButton;
    private JTextField inpEmail;
    private JTextField inpFirstName;
    private JTextField inpLastName;
    private JPasswordField inpConfirmPassword;
    private JPasswordField inpPassword;
    private JTextField inpUsername;
    private MainFrame frame;
    public CreateAccount(MainFrame f) {
        AlreadyHaveAccountButton.addActionListener(new AlreadyHaveAccountListener());
        CreateAccountConfirmButton.addActionListener(new CreateAccountListener());
        this.frame = f;
        this.getMainPanel().setRequestFocusEnabled(true);
    }

    public JPanel getMainPanel() {return mainPanel;}

    public boolean checkExistingUsers(String n) throws SQLException {

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
        if (existingUsernames.contains(n)){
            return true;
        }else return false;
    }

    public boolean checkExistingMail() throws SQLException {

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
                return true;
            }else if(inpEmail.getText().contains("@")&&inpEmail.getText().contains(".")) return false;
            else return true;
        }

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
        else System.out.print("Enter same password twice");

    }

    public class AlreadyHaveAccountListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            frame.getLoginPage();
        }
    }

    public class CreateAccountListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (!checkExistingUsers(inpUsername.getText())&&!checkExistingMail()) {
                    createAccount();
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }


        }
    }
}
