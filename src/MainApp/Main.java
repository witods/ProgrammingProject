package MainApp;

import Pages.HangMan;
import Pages.LoginPage;
import Pages.PasswordEncryption;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        //MainFrame frame = new MainFrame();
        //LoginPage.showLoginPage();
        /*HangMan hm = new HangMan();
        hm.setTitle("Hang Man");
        hm.setContentPane(new HangMan().mainPanel);
        hm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        hm.pack();
        hm.setVisible(true);*/

        String password = "password123";
        String hashedPassword = PasswordEncryption.hashPassword(password);
        /* PasswordEncryption.savePasswordToDatabase(hashedPassword); */

    }
}
