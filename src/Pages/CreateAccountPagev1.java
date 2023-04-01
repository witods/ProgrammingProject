package Pages;

import MainApp.Main;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CreateAccountPage extends JFrame{
    private JPanel mainPanel;
    private JButton AlreadyHaveAccountButton;
    private JButton CreateAccountConfirmButton;
    private JTextField inpEmail;
    private JTextField inpFirstName;
    private JTextField inpLastName;
    private JPasswordField inpConfirmPassword;
    private JPasswordField inpPassword;
    private JTextField inpUsername;

    public CreateAccountPage (){
        super();

        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Create Account");
        this.setBounds(0,0, 1000,800);
        setLocationRelativeTo(null);
        this.setVisible(true);


        /*only enable the create account button when all required fields are filled in
        * - create a list for all the required fields
        * - create a documentlistener to listen for changes in the document and each time an update happensgo through the
        * textfields to see if they all have been filled.
        * - and add that listener to each tf in the list
        * - */
        List<JTextField> list = new ArrayList<>();
            list.add(inpUsername);
            list.add(inpEmail);
            list.add(inpPassword);
            list.add(inpConfirmPassword);

        DocumentListener listener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                changedUpdate(e);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                changedUpdate(e);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                boolean enabled = true;
                for (JTextField tf : list) {
                    if (tf.getText().isEmpty()) {
                        enabled = false;
                    }
                }
                CreateAccountConfirmButton.setEnabled(enabled);
            }
        };

        for (JTextField tf : list) {
            tf.getDocument().addDocumentListener(listener);
        }

//        Actions when click on CreateAccountConfirmButton
        CreateAccountConfirmButton.addActionListener(new ActionListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: 10/03/2023 check of email al in db zit
                if (true) {
                    //show dialog
                } else {
                    // TODO: 10/03/2023 check of username al in db zit
                    if (true) {
                        //show dialog
                    } else {
                        // TODO: 10/03/2023 check of passwords matchen
                        if (!inpPassword.getPassword().equals(inpConfirmPassword.getPassword())) {
                            //show dialog
                        } else {
                            //indien alle checks ok zijn
                            // TODO: 10/03/2023 schrijf data weg naar database

                            //toon confirmatie dialog
                            //Main.Dialog_AccountCreated.setVisible(true);
                        }
                    }
                }
            }

        }
        );


        AlreadyHaveAccountButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                disposeCreateAccount();
                LoginPage.showLoginPage();
            }
        });
    }


    public void disposeCreateAccount(){
        dispose();
    }

    public static void showCreateAccountPage(){
        CreateAccountPage ca = new CreateAccountPage();
    }



}
