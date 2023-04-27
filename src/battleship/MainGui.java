package battleship;

import javax.swing.*;
import java.awt.*;

/**
 * This class contains the main graphical content
 */
public class MainGui extends JFrame {

    public JPanel mainPanel;

    public MainGui(String title, JPanel content) {
        JFrame frame = new JFrame(title);
        JPanel mainPanel = new JPanel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setContentPane(mainPanel);



        mainPanel.setLayout(null);
        mainPanel.setPreferredSize(new Dimension(1000, 800));

        mainPanel.setBackground(new Color(66,96,112));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setBounds(250, 30, 500, 35);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("OCR A Extended", 1, 28));
        titleLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        titleLabel.setVerticalTextPosition(SwingConstants.CENTER);
        titleLabel.setForeground(new Color(160, 235, 152));
        titleLabel.setBackground(new Color(66, 96, 112 ));
        titleLabel.setOpaque(true);
        mainPanel.add(titleLabel);

        JButton menuButton = new JButton("MENU");
        menuButton.setBounds(10, 30, 125, 35);
        menuButton.setHorizontalAlignment(SwingConstants.LEFT);
        menuButton.setFont(new Font("OCR A Extended", 1, 22));
        menuButton.setVerticalTextPosition(SwingConstants.CENTER);
        menuButton.setForeground(new Color(160, 235, 152));
        menuButton.setBackground(new Color(66, 96, 112 ));
        menuButton.setBorderPainted(false);
        menuButton.setFocusable(false);
        mainPanel.add(menuButton);

//        JLabel creditLabel = new JLabel("XXXX");
//        creditLabel.setBounds(855, 30, 125, 35);
//        creditLabel.setHorizontalAlignment(SwingConstants.RIGHT);
//        creditLabel.setFont(new Font("OCR A Extended", 1, 22));
//        creditLabel.setVerticalTextPosition(SwingConstants.CENTER);
//        creditLabel.setForeground(new Color(160, 235, 152));
//        creditLabel.setBackground(new Color(66, 96, 112 ));
//        creditLabel.setOpaque(true);
//        mainPanel.add(creditLabel);

        mainPanel.add(content);


        frame.pack();
        frame.setVisible(true);

    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}

