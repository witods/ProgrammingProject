package blackjack;

import javax.swing.JFrame;

public class MainGame {

    public void startGame(){
        //Create game window
        JFrame frame = new JFrame("BlackJack");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set its size
        frame.setSize(1000, 800);

        //Make a new blackjack Game object/JPanel
        Game blackjack = new Game();

        //Add it to the frame and make it visible
        frame.add(blackjack);
        frame.setVisible(true);

    }

    public static void main(String[] args) {

        MainGame test = new MainGame();
        test.startGame();

    }
}
