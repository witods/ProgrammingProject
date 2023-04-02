package Pages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ForgotPassword {

    private MainFrame frame;
    private JPanel mainPanel;
    private JTextField inputEmail;
    private JLabel emailMessage;
    private JTextField inputUserName;
    private JButton submitButton;
    private JLabel sendRecoveryMessage;
    private JLabel userNameMessage;
    private JPanel cardLayoutPanel;
    private JPasswordField codeInput;
    private JLabel recoveryMessage;
    private JButton changePasswordButton;
    private JPasswordField inputNewPassword;
    private JPasswordField confirmNewPassword;
    private JButton codeButton;
    private JLabel changePasswordMessage;
    private JButton loginPageReturn;
    CardLayout card = (CardLayout) cardLayoutPanel.getLayout();
    public ForgotPassword(MainFrame f){
        this.frame = f;
        submitButton.addActionListener(new submitActionListener());
        codeButton.addActionListener(new codeActionListener());
        changePasswordButton.addActionListener(new changePasswordListener());
        loginPageReturn.addActionListener(new returnToLoginPageListener());
    }

    public JPanel getMainPanel(){return this.mainPanel;}


    public void validateInputs() {

        String selectSQL = "SELECT * FROM `USERS` WHERE `userName` = ? AND `email` = ? ";


        try {
            PreparedStatement statement = frame.databaseConnection.getConnection().prepareStatement(selectSQL,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            statement.setString(1,inputUserName.getText());
            statement.setString(2,inputEmail.getText());
            ResultSet rs = statement.executeQuery();

                if(rs.first()){
                   frame.databaseConnection.getUserFromDatabase(rs.getString("userName"));
                   setCode();
                } else emailMessage.setText("Wrong username or mail adress");


        } catch (SQLException e) {
           e.printStackTrace();
            throw new RuntimeException(e);
        }


    }
    public void setCode(){
        int code = (int) (Math.random() * 10000000);
        String updateSQL = "UPDATE USERS SET recoveryCode = ? WHERE userName = ?";
        String getCode = "SELECT recoveryCode FROM USERS WHERE userName= ?";

        PreparedStatement statement;

        try {
            statement = frame.databaseConnection.getConnection().prepareStatement(updateSQL,ResultSet.CONCUR_UPDATABLE);
            statement.setInt(1,code);
            statement.setString(2, inputUserName.getText());
            statement.executeUpdate();

            statement = frame.databaseConnection.getConnection().prepareStatement(getCode,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            statement.setString(1,inputUserName.getText());
            ResultSet codeSet = statement.executeQuery();

            if(codeSet.first()){
                frame.getCurrentUser().setRecoverCode(codeSet.getInt("recoveryCode"));
                card.show(cardLayoutPanel,"enterCodePanel");
                frame.revalidate();
            }else sendRecoveryMessage.setText("Couldnt fetch a recovery code");
        } catch (SQLException e) {
            e.printStackTrace();
            emailMessage.setText("Could not send mail, please check your connection");
        }
    }
    public void validateCode(){
        String inputCode = new String(codeInput.getPassword());
        int userCode = Integer.parseInt(inputCode);

        if (userCode==frame.getCurrentUser().getRecoverCode()){
            card.show(cardLayoutPanel,"changePasswordPanel");
            frame.revalidate();
        }else recoveryMessage.setText("Recovery code doesn't match!");
    }
    public void setNewPassword(){
        String newPass = new String(inputNewPassword.getPassword());
        String confirmNewPass = new String(confirmNewPassword.getPassword());
        String setPassSQL = "UPDATE USERS SET password = ? WHERE userName = ?";
        if(newPass.equals(confirmNewPass)) {
            try {
                PreparedStatement statement = frame.databaseConnection.getConnection().prepareStatement(setPassSQL,ResultSet.CONCUR_UPDATABLE);
                statement.setString(1,newPass);
                statement.setString(2,frame.getCurrentUser().getUserName());
                if(statement.executeUpdate()!=0){
                    changePasswordMessage.setForeground(new Color(160,235,152));
                    changePasswordMessage.setText("Password succesfully changed, return to Login page.");
                }
            } catch (SQLException e) {
                changePasswordMessage.setText("Couldnt connect to the database, please try again");
                throw new RuntimeException(e);
            }
        }
    }
    public class submitActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            validateInputs();
        }
    }
    public class codeActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            validateCode();
        }
    }
    public class changePasswordListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {setNewPassword();}
    }
    public class returnToLoginPageListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {frame.getLoginPage();}
    }
}
