package Pages;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.*;

public class HangMan extends JFrame {
    private String wordToGuess;
    private String hint;
    private String credits;
    private int guessesLeft;
    private char[] lettersGuessed;
    private ArrayList<JButton> btns = new ArrayList<JButton>();
    public JPanel mainPanel;
    private JButton btn1;
    private JButton btn2;
    private JButton btn3;
    private JButton btn4;
    private JButton btn5;
    private JButton btn6;
    private JButton btn7;
    private JButton btn8;
    private JButton btn9;
    private JButton btn10;
    private JButton btn11;
    private JButton btn12;
    private JButton btn13;
    private JButton btn14;
    private JButton btn15;
    private JButton btn16;
    private JButton btn17;
    private JButton btn18;
    private JButton btn19;
    private JButton btn20;
    private JButton btn21;
    private JButton btn22;
    private JButton btn23;
    private JButton btn24;
    private JButton btn25;
    private JButton btn26;
    private JButton btnRestart;
    private JPanel firstRow;
    private JPanel lastRow;
    private JPanel secondRow;
    private JPanel centralPanel;
    private JPanel imgPanel;
    private JPanel descriptionPanel;
    private JLabel imgHangMan;
    private JLabel lblWordToGuess;
    private JLabel lblEncryptedWord;
    private JLabel lblGuesses;
    private JLabel lblCredits;

    private void createUIComponents() {
        imgHangMan = new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("img/hm6.png")));
    }

    public HangMan() {
        String[] wordAndHint = getRandomWord();
        wordToGuess = wordAndHint[0];
        hint = wordAndHint[1];
        credits = getCredits();
        guessesLeft = 6;
        lettersGuessed = new char[wordToGuess.length()];
        btns.addAll(Arrays.asList(btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9,
                btn10, btn11, btn12, btn13, btn14, btn15, btn16, btn17, btn18,
                btn19, btn20, btn21, btn22, btn23, btn24, btn25, btn26));

        lblWordToGuess.setText("Hint: " + hint);
        lblCredits.setText("Aantal credits: " + credits);

        for (int i = 0; i < wordToGuess.length(); i++) {
            lettersGuessed[i] = '_';
            lblEncryptedWord.setText(lblEncryptedWord.getText() + "_ ");
        }

        btnRestart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startNewGame();
            }
        });

        for (JButton btn : btns) {
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    char letter = btn.getText().toLowerCase().charAt(0);
                    btn.setEnabled(false);
                    updateWord(letter);
                }
            });
        }
    }

    private void startNewGame() {
        String[] wordAndHint = getRandomWord();
        wordToGuess = wordAndHint[0];
        hint = wordAndHint[1];
        guessesLeft = 6;
        lettersGuessed = new char[wordToGuess.length()];

        lblWordToGuess.setText("Hint: " + hint);

        for (int i = 0; i < wordToGuess.length(); i++) {
            lettersGuessed[i] = '_';
            lblEncryptedWord.setText(lblEncryptedWord.getText() + "_ ");
        }

        updateWordLabel();
        imgHangMan.setIcon(new ImageIcon(getClass().getClassLoader().getResource("img/hm6.png")));
        lblGuesses.setText("Kies een letter.");

        for (JButton btn : btns) {
            btn.setEnabled(true);
        }
    }

    private boolean hasWon() {
        for (char letter : lettersGuessed) {
            if (letter == '_') {
                return false;
            }
        }
        return true;
    }

    private void updateWord(char letter) {
        boolean letterFound = false;
        for (int i = 0; i < wordToGuess.length(); i++) {
            if (wordToGuess.charAt(i) == letter) {
                lettersGuessed[i] = letter;
                letterFound = true;
            }
        }

        if (!letterFound) {
            guessesLeft--;
            if (guessesLeft == 0) {
                lblGuesses.setText("Helaas, u bent verloren. Het woord was " + wordToGuess);
                imgHangMan.setIcon(new ImageIcon(getClass().getClassLoader().getResource("img/hm0.png")));
                for (JButton btn : btns) {
                    btn.setEnabled(false);
                }
                updateCredits(-10);
                lblCredits.setText("Aantal credits: " + getCredits());
            } else {
                lblGuesses.setText("Letter niet gevonden. " + guessesLeft + " aantal resterende kansen.");
                imgHangMan.setIcon(new ImageIcon(getClass().getClassLoader().getResource("img/hm" + guessesLeft + ".png")));
            }
        } else {
            if (hasWon()) {
                lblGuesses.setText("Proficiat, u heeft het woord geraden!");
                for (JButton btn : btns) {
                    btn.setEnabled(false);
                }
                updateCredits(20);
                lblCredits.setText("Aantal credits: " + getCredits());
            } else {
                lblGuesses.setText("Letter gevonden!");
            }
        }

        updateWordLabel();
    }

    private void updateWordLabel() {
        StringBuilder sb = new StringBuilder();

        for (char letter : lettersGuessed) {
            sb.append(letter).append(" ");
        }
        lblEncryptedWord.setText(sb.toString());
    }

    private String getCredits() {
        String credits = "";

        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT amount FROM CREDITS WHERE userID = 5");

            while (rs.next()) {
                credits = Integer.toString(rs.getInt("amount"));
            }
        } catch (SQLException ex) {
            System.out.println("Failed to execute query!");
        } finally {
            databaseConnection.close();
        }

        return credits;
    }

    private void updateCredits(int amount) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();

        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("UPDATE CREDITS SET amount = amount + " + amount + " WHERE userID = 5");
        } catch (SQLException ex) {
            System.out.println("Failed to execute query!");
        } finally {
            databaseConnection.close();
        }
    }

    private String[] getRandomWord() {
        String[] wordAndHint = new String[2];

        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT word, hint FROM WORDS ORDER BY RAND() LIMIT 1");

            while (rs.next()) {
                wordAndHint[0] = rs.getString("word");
                wordAndHint[1] = rs.getString("hint");
            }
        } catch (SQLException ex) {
            System.out.println("Failed to execute query!");
        } finally {
            databaseConnection.close();
        }
        return wordAndHint;
    }
}
