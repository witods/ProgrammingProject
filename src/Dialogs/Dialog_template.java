package Dialogs;

import MainApp.Main;
import Pages.CreateAccountPage;

import javax.swing.*;
import java.awt.event.*;

public class Dialog_template extends JDialog {
    private JPanel contentPane;
    private JButton buttonWhite;
    private JButton buttonBlue;
    private JLabel dialogMessage;

    public Dialog_template(String dialogMessage, String buttonWhiteText, String buttonBlueText) {
        super();

        //general settings of dialog box
        this.setContentPane(contentPane);
        this.setTitle("Template title");
        this.setSize(400,300);
       // this.setLocation((CreateAccountPage.main().getWidth() /2)-(this.getWidth()/2), (Main.CreateAccountPage.getHeight()/2)-(this.getHeight()/2));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //set content of dialog box
        this.dialogMessage.setText(dialogMessage);
        if(buttonWhiteText.equals("")){
            buttonWhite.setVisible(false);
        }else{
            this.buttonWhite.setText(buttonWhiteText);
        }
        if(buttonBlueText.equals("")){
            buttonBlue.setVisible(false);
        }else{
            this.buttonBlue.setText(buttonBlueText);
        }


        //set error message and button texts



        //set action listeners
        buttonWhite.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

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
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

}
