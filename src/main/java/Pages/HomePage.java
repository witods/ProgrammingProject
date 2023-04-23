package Pages;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

public class HomePage {

    private JPanel mainPanel;
    private JButton profileButton;
    private JButton playGameButton;
    private JButton rankingButton;
    private JLabel Pictureprofile;
    private JLabel Pictureplaygame;
    private JLabel Pictureranking;
    private JLabel welcomeUser;
    private JLabel pictureCredits;
    private JLabel creditsUser;

    //TO DO get username from database
    private String userName;

    //TO DO get user credits from database
    private int userCredits;
    private MainFrame frame;

    public HomePage(MainFrame f){
        // TO DO replace by values from database
        this.userName = "Wouter";
        this.userCredits=1000;
        this.frame = f;
        welcomeUser.setText("WELCOME " + userName.toUpperCase(Locale.ROOT));
        creditsUser.setText("CREDITS=" + userCredits);



        //actionbuttons
        profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        playGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        rankingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public JPanel getMainPanel(){return this.mainPanel;}

    private void createUIComponents() {
        // TODO: place custom component creation code here

        // Following code creates an ICON and resizes it to the correct size.
        ImageIcon iconprofile = new ImageIcon("src/img/imghomepage/profile.png");
        Image imageprofile = iconprofile.getImage();
        Image newimageprofile = imageprofile.getScaledInstance(200,200, Image.SCALE_SMOOTH);
        iconprofile=new ImageIcon(newimageprofile);

        ImageIcon iconplaygame = new ImageIcon("src/img/imghomepage/playgame.png");
        Image imageplaygame = iconplaygame.getImage();
        Image newimageplaygame = imageplaygame.getScaledInstance(200,200, Image.SCALE_SMOOTH);
        iconplaygame=new ImageIcon(newimageplaygame);

        ImageIcon iconranking = new ImageIcon("src/img/imghomepage/ranking.png");
        Image imageranking = iconranking.getImage();
        Image newimageranking = imageranking.getScaledInstance(200,200, Image.SCALE_SMOOTH);
        iconranking=new ImageIcon(newimageranking);

        ImageIcon iconcredits = new ImageIcon("src/img/icon_Credits.png");
        Image imagecredits = iconcredits.getImage();
        Image newimagecredits = imagecredits.getScaledInstance(100,100, Image.SCALE_SMOOTH);
        iconcredits=new ImageIcon(newimagecredits);



        //Following codes adds a picture to a jLabel
        Pictureprofile= new JLabel(iconprofile);
        Pictureplaygame=new JLabel(iconplaygame);
        Pictureranking=new JLabel(iconranking);
        pictureCredits=new JLabel(iconcredits );

        //
       profileButton=new JButton();
       profileButton.setBorder(new LineBorder(Color.GREEN));
    }

}
