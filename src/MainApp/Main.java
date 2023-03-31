package MainApp;

import Pages.HangMan;
import Pages.LoginPage;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        //LoginPage.showLoginPage();
        HangMan hm = new HangMan();
        hm.setTitle("Hang Man");
        hm.setContentPane(new HangMan().mainPanel);
        hm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        hm.pack();
        hm.setVisible(true);
    }
}
