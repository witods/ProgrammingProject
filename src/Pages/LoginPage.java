package Pages;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Dialogs.Dialog_login_requiredFields;


public class LoginPage extends JFrame{
    private JPanel loginPanel;
    private JTextField inpUsername;
    private JPasswordField inpPassword;
    private JButton loginButton;
    private JButton createAccountButton;
    private JButton forgotPasswordButton;
    private JLabel loginLabel;


//    constructor + actions of buttons
    public LoginPage(String pageTitle) {
        super(pageTitle);

//        general settings of JFrame
            this.setContentPane(this.loginPanel);
            this.setTitle(pageTitle);
            this.setBounds(0,0,1000,800);
            setLocationRelativeTo(null);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setVisible(true);


        loginButton.addActionListener(new ActionListener() {
            /**
             * @param e Wanneer de user op de Login button klikt zal XYZ gebeuren
             */
            @Override
            public void actionPerformed(ActionEvent e) {

                //controleer of Username en paswoord zijn ingevuld
                if (inpUsername.getText().equals("") || inpPassword.getText().equals("")) {
                    Dialog_login_requiredFields.showDialog();
                    //JOptionPane.showMessageDialog(LoginPage.this, "Please fill in both username and password.");
                }

                // TODO: 10/03/2023 controleer of user en paswoord in de db zitten
                    //indien niet in db geef foutmelding en keer terug naar pagina --> Dialog name = Dialog_
                    if (false){
                        JOptionPane.showMessageDialog(LoginPage.this, "Username and or password are not correct. Please try again. \n If you have not yet created an account, please click on the create account button.");
                    }

                    // TODO: 10/03/2023 indien in de database, ga naar home pagina


            }
        });
        createAccountButton.addActionListener(new ActionListener() {
            /**
             * @param e the event to be processed when the create account button is clicked
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                // set login page visible to false
                disposeLoginPage();

                // display create account page
                CreateAccountPage.showCreateAccountPage();
            }
        });

        //TODO add logic for forgot password button
        forgotPasswordButton.addActionListener(new ActionListener() {
            /**
             * Invoked when the forgot password button is clicked.
             */
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    /**
     * This method will dispose the login page
     */
    public void disposeLoginPage (){
        this.dispose();
    }


    /**
     * this public method will create a new login screen
     */
    public static void showLoginPage() {
        JFrame loginPage = new LoginPage("Login");
    }

}
