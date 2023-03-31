package Pages;

import javax.swing.*;
import javax.xml.crypto.Data;

public class MainFrame extends JFrame {

    private LoginPage loginPage;
    private CreateAccount createAccount;
    private User currentUser;
    public DatabaseConnection databaseConnection;
    MainFrame() {

        databaseConnection = new DatabaseConnection();
        loginPage = new LoginPage(this);
        createAccount = new CreateAccount(this);
        setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setBounds(0,0,1000,800);
        this.setContentPane(loginPage.getMainPanel());
        this.setTitle("Pixel Playtime");

        this.setVisible(true);
    }

    public void getLoginPage() {
        this.setContentPane(loginPage.getMainPanel());
        this.revalidate();
    }
    public void getCreateAccountPage() {
        this.setContentPane(createAccount.getMainPanel());
        this.revalidate();
    }
    public void setCurrentUser(User u) {
        this.currentUser = u;
    }

    public User getCurrentUser() {
        return this.currentUser;
    }
}

