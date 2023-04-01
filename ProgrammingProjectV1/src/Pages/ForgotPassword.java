package Pages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

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
    private String userName = inputUserName.getText();
    private String userMail = inputEmail.getText();
    public ForgotPassword(MainFrame f){
        this.frame = f;
        submitButton.addActionListener(new submitActionListener());
        codeButton.addActionListener(new codeActionListener());
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
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        java.util.Date utilDate = new java.util.Date();
        java.sql.Timestamp dateTest = new java.sql.Timestamp(utilDate.getTime());

        String updateSQL = "UPDATE `USERS` SET `recoveryCode` = ?, `recoveryTimer` = ? WHERE `userName` = ?";

        PreparedStatement statement;

        try {
            statement = frame.databaseConnection.getConnection().prepareStatement(updateSQL);
            statement.setInt(1,code);
            statement.setTimestamp(2, dateTest);
            statement.setString(3,userName);
            System.out.println(dateTest);
            System.out.println(code);

            System.out.println(statement.executeUpdate());

            CardLayout card = (CardLayout) cardLayoutPanel.getLayout();
            card.show(cardLayoutPanel,"enterCodePanel");
            frame.revalidate();

        } catch (SQLException e) {
            e.printStackTrace();
            emailMessage.setText("Could not send mail, please check your connection");
        }
        try {
            String getCode = "SELECT `recoveryCode` FROM `USERS` WHERE `userName`= ?";
            statement = frame.databaseConnection.getConnection().prepareStatement(getCode,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            statement.setString(1,userName);
            ResultSet codeSet = statement.executeQuery();
            if(codeSet.first()){
                frame.getCurrentUser().setRecoverCode(codeSet.getInt("recoveryCode"));
                System.out.println(frame.getCurrentUser().getRecoverCode());
            }


        } catch (SQLException e) {
            e.printStackTrace();
            emailMessage.setText("Could not get code, please check your connection");
        }

    }
    public void validateCode(){
        String inputCode = new String(codeInput.getPassword());
        //inputCode.parseToInt();
    }
    public void setNewPassword(){

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
}
