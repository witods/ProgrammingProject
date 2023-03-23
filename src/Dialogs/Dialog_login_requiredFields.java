package Dialogs;


import javax.swing.*;
import java.awt.event.*;

public class Dialog_login_requiredFields extends JFrame {
    private JLabel dialogMessage;
    private JButton buttonBlue;
    private JPanel contentPanel;


    /**
     * Constructor for dialog box that says you should fill in both a username and or password
     */
    public Dialog_login_requiredFields() {
        setContentPane(contentPanel);
        this.setTitle("Fill in username and password");

        //TODO set the window in the center of the screen
        setLocationRelativeTo(null);

        buttonBlue.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPanel.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onCancel() {
        // add your code here if necessary
        setVisible(false);
    }

    public static void showDialog(){
        Dialog_login_requiredFields dialog = new Dialog_login_requiredFields();
        dialog.pack();
        dialog.setVisible(true);
    }

}
