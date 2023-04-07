package Pages;

import javax.swing.*;
import javax.xml.crypto.Data;

public class MainFrame extends JFrame {

    //Declareren van Instance fields
    private LoginPage loginPage;
    private CreateAccount createAccount;
    //private HomePage homePage;
    private ForgotPassword recoveryPage;
    private User currentUser;
    public DatabaseConnection databaseConnection;

    //Database connectie en panels aanmaken voor de verschillende schermen weer te geven
    public MainFrame() {

        databaseConnection = new DatabaseConnection(this);
        loginPage = new LoginPage(this);
        //homePage = new HomePage(this);
        setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setResizable(false);
        this.setBounds(0, 0, 1000, 800);
        this.setContentPane(loginPage.getMainPanel());
        this.setTitle("Pixel Playtime");

        this.setVisible(true);
    }

    //Tonen van de Login Page
    public void getLoginPage() {
        createAccount = null;
        recoveryPage = null;
        loginPage = new LoginPage(this);
        this.setContentPane(loginPage.getMainPanel());
        this.revalidate();
    }

    //Tonen van de Create Account Page
    public void getCreateAccountPage() {
        loginPage = null;
        recoveryPage = null;
        createAccount = new CreateAccount(this);
        this.setContentPane(createAccount.getMainPanel());
        this.revalidate();
    }
    //Tonen van Home Page na succesvol inloggen

    //public void getHomePage(){
    //this.setContentPane(homePage.getMainPanel());
    //this.revalidate();
    //}

    //Tonen van Forgot Password Page

    public void getForgotPassword() {
        loginPage = null;
        createAccount = null;
        recoveryPage = new ForgotPassword(this);
        this.setContentPane(recoveryPage.getMainPanel());
        this.revalidate();
    }

    //Getters en setters voor de currentUser (wordt enkel hier aangemaakt)
    public void setCurrentUser(User u) {
        this.currentUser = u;
    }

    public User getCurrentUser() {
        return this.currentUser;
    }
    }

