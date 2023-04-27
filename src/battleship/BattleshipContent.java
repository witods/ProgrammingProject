package battleship;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * This class contains the graphical content, specific for Battleship
 */

public class BattleshipContent {

    private ArrayList<JLabel> labels;

    private JLabel gameTextLabel;
    private JButton restartBtn;
    private JLabel movesLabel;
    private JLabel winLose;

    /**
     * Constructor
     */
    public BattleshipContent() {
        createBattleShipContent();
    }

    /**
     * Getters
     */
    public ArrayList<JLabel> getLabels() {
        return labels;
    }

    public JLabel getGameTextLabel() {
        return gameTextLabel;
    }

    public JButton getRestartBtn() {
        return restartBtn;
    }

    public JLabel getMovesLabel() {
        return movesLabel;
    }

    public JLabel getWinLose() {
        return winLose;
    }

    /**
     * Design graphic part, specific for Battleship
     */
    public JPanel createBattleShipContent() {
        labels = new ArrayList<>();
        JPanel battleshipPanel = new JPanel();
        battleshipPanel.setBounds(0, 100, 1000, 800);
        battleshipPanel.setBackground(new Color(66, 96, 112));
        battleshipPanel.setLayout(null);

        gameTextLabel = new JLabel();
        gameTextLabel.setBounds(250,50 , 500, 35);
        gameTextLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gameTextLabel.setFont(new Font("OCR A Extended", 1, 18));
        gameTextLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        gameTextLabel.setVerticalTextPosition(SwingConstants.CENTER);
        gameTextLabel.setForeground(new Color(160, 235, 160));
        gameTextLabel.setBackground(new Color(66, 96, 112 ));
        gameTextLabel.setOpaque(true);

        battleshipPanel.add(gameTextLabel);

        winLose = new JLabel("GEWONNEN!");
        winLose.setBounds(351,251, 300, 200);
        winLose.setHorizontalAlignment(SwingConstants.CENTER);
        winLose.setFont(new Font("OCR A Extended", 1, 30));
        winLose.setHorizontalTextPosition(SwingConstants.CENTER);
        winLose.setVerticalTextPosition(SwingConstants.CENTER);
        winLose.setForeground(new Color(160, 235, 160));
        winLose.setBackground(new Color(5, 90, 112 ));
        winLose.setBorder(BorderFactory.createDashedBorder(new Color(160, 235, 160),5,5));
        winLose.setOpaque(true);
        winLose.setVisible(false);

        battleshipPanel.add(winLose);

        JPanel fieldPanel = new JPanel();
        fieldPanel.setBounds(250,100, 500, 500);
        fieldPanel.setPreferredSize(new Dimension(500,500));
        fieldPanel.setBackground(new Color(66, 96, 112 ));
//

        // Labels
        for(int i=0; i<100; i++) {
            JLabel lbl = new JLabel();
            lbl.setName("lbl" + i);
            lbl.setMinimumSize(new Dimension(45,45));
            lbl.setPreferredSize(new Dimension(45,45));
            lbl.setBackground(new Color(5, 90, 112 ));
            lbl.setOpaque(true);
            lbl.setFont(new Font("Courier", 0, 60));
            lbl.setHorizontalAlignment(SwingConstants.CENTER);
            lbl.setVerticalAlignment(SwingConstants.CENTER);
            // TO DO alignment 'X' in the label


            labels.add(lbl);
            fieldPanel.add(lbl);
        }

        battleshipPanel.add(fieldPanel);

        restartBtn = new JButton("Restart");
        restartBtn.setBounds(800, 310, 125, 75);
        restartBtn.setFont(new Font("OCR A Extended", 1, 20));
        restartBtn.setHorizontalTextPosition(SwingConstants.CENTER);
        restartBtn.setVerticalTextPosition(SwingConstants.CENTER);
        restartBtn.setForeground(new Color(160, 235, 152));
        restartBtn.setBackground(new Color(66, 96, 112 ));
        restartBtn.setBorderPainted(false);
        restartBtn.setFocusable(false);
        restartBtn.setContentAreaFilled(false); //button doesn't change color when clicking

        battleshipPanel.add(restartBtn);

        movesLabel = new JLabel("Aantal zetten:   ");
        movesLabel.setBounds(25,310, 200, 75);
        movesLabel.setFont(new Font("OCR A Extended", 1, 18));
        movesLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        movesLabel.setVerticalTextPosition(SwingConstants.CENTER);
        movesLabel.setForeground(new Color(160, 235, 160));
        movesLabel.setBackground(new Color(66, 96, 112 ));
        movesLabel.setOpaque(true);

        battleshipPanel.add(movesLabel);

        return battleshipPanel;
    }


}
