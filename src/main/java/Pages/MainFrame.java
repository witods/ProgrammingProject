package Pages;

import SpaceInvaders.MainGame;

import javax.swing.*;

public class MainFrame extends JFrame {

    //Declareren van Instance fields
    private LoginPage loginPage;
    private CreateAccount createAccount;
    //private HomePage homePage;
    private ForgotPassword recoveryPage;
    private HomePage homePage;
    private User currentUser;
    public DatabaseConnection databaseConnection;
    private MainGame gameCanvas;

    //Database connectie en panels aanmaken voor de verschillende schermen weer te geven
    public MainFrame() {
        databaseConnection = new DatabaseConnection(this);
        //TEST LOGIN
        loginPage = new LoginPage(this);
        this.setContentPane(loginPage.getMainPanel());

        //TEST SPACE INVADERS
        //gameCanvas = new MainGame();
       // this.getContentPane().add(gameCanvas);
        //this.setSize(1200,900);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);



        this.pack();
        this.setTitle("Pixel Playtime");

        System.out.println(this.getContentPane().getWidth());
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
    //Tonen van Forgot Password Page

    public void getForgotPassword() {
        loginPage = null;
        createAccount = null;
        recoveryPage = new ForgotPassword(this);
        this.setContentPane(recoveryPage.getMainPanel());
        this.revalidate();
    }
    public void getHomePage(){
        createAccount = null;
        recoveryPage = null;
        homePage = new HomePage(this);
        this.setContentPane(homePage.getMainPanel());
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
