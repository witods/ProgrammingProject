package battleship;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * This class controls the game
 */
public class BattleshipGame {
    private BattleshipContent content;
    private MainGui main;
    private Field playerField;

    private ArrayList<JLabel> labels;
    private int moves;
    private final int maximumMoves = 55;

    private int remainingMoves;

    private int shipsDestroyed = 0;

    private int credits = 0;


    /**
     * Constructor
     */
    public BattleshipGame() {
        this.content = new BattleshipContent();;
        this.main = new MainGui("Zeeslag", content.createBattleShipContent());;
        this.labels = labels = content.getLabels();
        content.getRestartBtn().addActionListener(new RestartGameListener());
        startGame();
    }

    public static void main(String[] args) {
        BattleshipGame zeeslag = new BattleshipGame();
//        zeeslag.startGame();

    }

    /**
     * This method starts a (new) game
     */
    private void startGame() {
        moves = 0;
        playerField = new Field();
        clearField();

        for (int i=0; i < labels.size(); i++) {
            JLabel lbl = labels.get(i);
            int position = i;
            lbl.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    JLabel text = content.getGameTextLabel();

                    if(lbl.isEnabled()){
                        text.setText("");
                        switch (playerField.shootShip(position)) {
                            case 0:
                                lbl.setBackground(new Color(160, 160, 160));
                                lbl.setForeground(new Color(120, 120, 120));
                                lbl.setText("X");
                                break;
                            case 1:
                                Ship battleShip1 = playerField.getBattleship1();
                                lbl.setBackground(new Color(192,26, 75));
                                if (battleShip1.getTotalHits() == 0) {
                                    text.setText(battleShip1.getName() + " gezonken!");
                                    sinkShip(battleShip1.getStartPosition(), battleShip1.getLength(), battleShip1.getDirection());
                                    shipsDestroyed ++;
                                }
                                break;
                            case 2:
                                Ship battleShip2 = playerField.getBattleship2();
                                lbl.setBackground(new Color(192,26, 75));
                                if (battleShip2.getTotalHits() == 0) {
                                    text.setText(battleShip2.getName() + " gezonken!");
                                    sinkShip(battleShip2.getStartPosition(), battleShip2.getLength(), battleShip2.getDirection());
                                    shipsDestroyed ++;
                                }
                                break;
                            case 3:
                                Ship battleShip3 = playerField.getBattleship3();
                                lbl.setBackground(new Color(192,26, 75));
                                if (battleShip3.getTotalHits() == 0) {
                                    text.setText(battleShip3.getName() + " gezonken!");
                                    sinkShip(battleShip3.getStartPosition(), battleShip3.getLength(), battleShip3.getDirection());
                                    shipsDestroyed ++;
                                }
                                break;
                            case 4:
                                Ship battleShip4 = playerField.getBattleship4();
                                lbl.setBackground(new Color(192,26, 75));
                                if (battleShip4.getTotalHits() == 0) {
                                    text.setText(battleShip4.getName() + " gezonken!");
                                    sinkShip(battleShip4.getStartPosition(), battleShip4.getLength(), battleShip4.getDirection());
                                    shipsDestroyed ++;
                                }
                                break;
                            case 5:
                                Ship battleShip5 = playerField.getBattleship5();
                                lbl.setBackground(new Color(192,26, 75));
                                if (battleShip5.getTotalHits() == 0) {
                                    text.setText(battleShip5.getName() + " gezonken!");
                                    sinkShip(battleShip5.getStartPosition(), battleShip5.getLength(), battleShip5.getDirection());
                                    shipsDestroyed ++;
                                }
                                break;
                        }
                        moves ++;
                        lbl.setEnabled(false);
                        remainingMoves = maximumMoves - moves;
                        if (remainingMoves >= 0) {
                            content.getMovesLabel().setText("Aantal zetten: " + remainingMoves);
                        } else {
                            content.getMovesLabel().setText("Aantal zetten: 0");
                        }

                    }
                   winOrLose();



                }

            });
        }
    }

    /**
     * This method changes the color of a sunken ship
     * @param startPosition
     * @param length
     * @param direction
     */
    private void sinkShip(int startPosition, int length, int direction) {
        if (direction == 0) {
            for (int i = startPosition; i < startPosition+length; i++) {
                labels.get(i).setBackground(new Color(107, 15, 42));
            }
        } else if (direction == 1) {
            for (int i = startPosition; i < startPosition+(10*(length)); i+=10) {
                labels.get(i).setBackground(new Color(107, 15, 42));
            }
        }
    }

    /**
     * This method clears the field
     */
    private void clearField(){
        shipsDestroyed = 0;
        for (JLabel lbl : labels) {
            lbl.setBackground(new Color(5, 90, 112 ));
            lbl.setText("");
            lbl.setEnabled(true);
        }
        content.getMovesLabel().setText("Aantal zetten:   ");
        content.getWinLose().setVisible(false);
        content.getGameTextLabel().setText("");
    }

    /**
     * This method determines if user wins or loses the game
     */
    private void winOrLose() {
        if (shipsDestroyed == 5) {
            content.getWinLose().setText("GEWONNEN!");
            content.getWinLose().setVisible(true);
            disableLbl();
            credits = remainingMoves * 2;
            content.getGameTextLabel().setText("Credits: " + credits);
        } else if (moves >= maximumMoves){
            content.getWinLose().setText("VERLOREN!");
            content.getWinLose().setVisible(true);
            disableLbl();
            content.getGameTextLabel().setText("");
        }
    }

    /**
     * This method disables all the labels of the field
     */
    private void disableLbl (){
        for (JLabel lbl : labels) {
            lbl.setEnabled(false);
        }
    }

    /**
     * This class adds an actionlistener to the restartbutton
     */
    public class RestartGameListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            startGame();
            content.getRestartBtn().setBackground(new Color(66, 96, 112 ));
        }
    }
}


