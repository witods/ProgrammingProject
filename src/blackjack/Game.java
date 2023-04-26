package blackjack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Contains all Game logic
 */
public class Game extends JPanel {

    // Constants
    public static final int CARD_WIDTH = 100;
    public static final int CARD_HEIGHT = 145;
    public static final String IMAGE_DIR = "blackjack/imgcards/";

    // Declare instance vars needed for Game class
    private Deck deck, discarded;
    private Dealer dealer;
    private Player player;
    private int wins, losses, pushes;
    private int credits = 0;

    // three buttons for "Hit" "Stand" and "Next Round" actions
    private JButton btnHit, btnStand, btnNext;

    // labels to show images of cards
    private JLabel[] lblDealerCards, lblPlayerCards;
    // few more labels for showing important stats
    private JLabel lblScore, lblPlayerHandVal, lblDealerHandVal, lblGameMessage, lblGameMessage2, lblGameMessage3, lblcredits;

    /**
     * Constructor for Game, creates our variables and starts the Game
     */
    public Game() {

        // Create a new deck with 52 cards
        deck = new Deck(true);
        // Create a new empty deck
        discarded = new Deck();

        // Create the People
        dealer = new Dealer();
        player = new Player();

        // Shuffle the deck and start the first round
        deck.shuffle();

        setupGUI();
        startRound();
    }

    /**
     * Set up the GUI for the Game
     * Adds buttons and labels to the JPanel
     */
    private void setupGUI() {
        // Size of JPanel
        this.setSize(800, 1000);

        // Make Buttons for "Hit" "Stand" and "Next Round" actions.
        // setBounds is used to define their locations and sizes
        btnHit = new JButton("Hit");
        btnHit.setBounds(10, 10, 50, 20);
        btnHit.setBackground(new Color(23,60,86));
        btnHit.setForeground(new Color(160,235,152));
        btnStand = new JButton("Stand");
        btnStand.setBounds(70, 10, 100, 20);
        btnStand.setBackground(new Color(23,60,86));
        btnStand.setForeground(new Color(160,235,152));
        btnNext = new JButton("Next Round");
        btnNext.setBounds(180, 10, 140, 20);
        btnNext.setBackground(new Color(23,60,86));
        btnNext.setForeground(new Color(160,235,152));
        btnNext.setVisible(false);

        // need this layout so we can use absolute positioning
        this.setLayout(null);

        // Add the buttons to the JPanel
        this.add(btnHit);
        this.add(btnStand);
        this.add(btnNext);

        // Arrays to hold the dealer and player card images
        lblDealerCards = new JLabel[11];
        lblPlayerCards = new JLabel[11];

        // The position of the first displayed card, which will be offset for each subsequent card
        int initialCardX = 10, initialCardY = 150;

        // For all the cards we're going to show (up to 11)
        for (int i = 0; i < lblDealerCards.length; i++) {

            // set them to new cards face down
            // done with JLabels and ImageIcons
            lblDealerCards[i] = new JLabel(new ImageIcon(new ImageIcon(IMAGE_DIR + "CardDown.png").getImage()
                    .getScaledInstance(CARD_WIDTH, CARD_HEIGHT, Image.SCALE_SMOOTH)));
            lblPlayerCards[i] = new JLabel(new ImageIcon(new ImageIcon(IMAGE_DIR + "CardDown.png").getImage()
                    .getScaledInstance(CARD_WIDTH, CARD_HEIGHT, Image.SCALE_SMOOTH)));

            // Use setBounds to set the width/height of each card, and their positions
            lblDealerCards[i].setBounds(initialCardX, initialCardY, CARD_WIDTH, CARD_HEIGHT);
            lblPlayerCards[i].setBounds(initialCardX, initialCardY + 250, CARD_WIDTH, CARD_HEIGHT);

            // add the JLabel to the JPanel so we can see it later
            this.add(lblDealerCards[i]);
            this.add(lblPlayerCards[i]);

            // increment the x/y values of each card by some amount, this will make them
            // appear "stacked" so users can see each one
            initialCardX += 50;
            initialCardY -= 18;

        }

        // setup all the labels and such
        // make scoreboard

        Font basicfont = new Font("OCR A Extended", 1,15);

        lblScore = new JLabel("Wins: 0   Losses: 0   Pushes: 0");
        lblScore.setBounds(450, 10, 300, 50);
        lblScore.setFont(new Font("OCR A Extended", 1,12));
        this.add(lblScore);

        lblcredits = new JLabel("credits = " + credits );
        lblcredits.setBounds(800,10,300,50);
        lblcredits.setFont(basicfont);
        this.add(lblcredits);

        // message board
        lblGameMessage = new JLabel("Starting round! Hit or Stand?");
        lblGameMessage.setBounds(450, 200, 300, 40);
        lblGameMessage.setFont(basicfont);
        this.add(lblGameMessage);

        lblGameMessage2 = new JLabel("If you win you get 50 credits!");
        lblGameMessage2.setBounds(450,250,300,40);
        lblGameMessage2.setFont(basicfont);
        this.add(lblGameMessage2);

        lblGameMessage3 = new JLabel("If you lose you lose 50 credits!");
        lblGameMessage3.setBounds(450,300,300,40);
        lblGameMessage3.setFont(basicfont);
        this.add(lblGameMessage3);


        // hand values on display
        lblDealerHandVal = new JLabel("Dealer's Hand Value:");
        lblPlayerHandVal = new JLabel("Player's Hand Value:");
        lblDealerHandVal.setBounds(20, 280, 300, 50);
        lblPlayerHandVal.setBounds(20, 530, 300, 50);
        this.add(lblDealerHandVal);
        this.add(lblPlayerHandVal);

        // make all labels white
        Color colorForeground = new Color(160,235,152);
        lblGameMessage.setForeground(colorForeground);
        lblGameMessage2.setForeground(colorForeground);
        lblGameMessage3.setForeground(colorForeground);
        lblDealerHandVal.setForeground(colorForeground);
        lblPlayerHandVal.setForeground(colorForeground);
        lblScore.setForeground(colorForeground);
        lblcredits.setForeground(colorForeground);

        // listen for button clicks
        // When someone clicks the Hit button
        btnHit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // make the player hit the deck
                player.hit(deck, discarded);
                // update screen with their new card, and their score
                updateScreen();
                checkBusts();
                checkPlayer21();

            }
        });

        // when someone clicks the "Stand" button
        btnStand.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // that means it's the dealers turn now
                dealersTurn();
                // see who won after dealer drew card
                checkWins();
                // update screen with player's cards
                updateScreen();
                // also reveal all the dealer's cards, so we can see what they drew
                dealer.printHand(lblDealerCards);

                // make only the next round button visible, they cannot hit/stand at this point
                btnHit.setVisible(false);
                btnStand.setVisible(false);
                btnNext.setVisible(true);
            }
        });

        // someone hits the next round button
        btnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // reset buttons and start next round
                btnNext.setVisible(false);
                btnHit.setVisible(true);
                btnStand.setVisible(true);
                startRound();
            }
        });
    }

    /**
     * This is called when player hits "Hit" button to see if they busted
     */
    private void checkBusts() {
        // Check if they busted
        if (player.getHand().calculatedValue() > 21) {
            // show message
            lblGameMessage.setText("You BUST - Over 21");
            // update score
            losses++;
            credits-=50;

            // make next round button only visible button
            btnHit.setVisible(false);
            btnStand.setVisible(false);
            btnNext.setVisible(true);
        }
    }

    /**
     * At the end of each round, this method is called to see who won
     */
    private void checkWins() {

        // Show value of dealers hand
        lblDealerHandVal.setText("Dealer's hand value: " + dealer.getHand().calculatedValue());

        // Check who wins and count wins or losses
        if (dealer.getHand().calculatedValue() > 21) {
            lblGameMessage.setText("Dealer Busts! You win!");
            wins++;
            credits+=50;

        } else if (dealer.getHand().calculatedValue() > player.getHand().calculatedValue()) {
            lblGameMessage.setText("Dealer wins - Higher hand");
            losses++;
            credits-=50;

        } else if (player.getHand().calculatedValue() > dealer.getHand().calculatedValue()) {
            lblGameMessage.setText("You win - Higher hand");
            wins++;
            credits+=50;

        } else {
            lblGameMessage.setText("Equal Value Hands - Push");
            pushes++;
            credits+=50;

        }
    }

    /**
     * This is called when player hits "Hit" button to see if they have 21
     * Prevents them from hitting again
     */
    private void checkPlayer21(){
        if(player.getHand().calculatedValue() == 21){
            lblGameMessage.setText("You have 21!");
            //update score
            wins++;
            credits+=50;

            //make next round button only visible button
            btnHit.setVisible(false);
            btnStand.setVisible(false);
            btnNext.setVisible(true);
        }
    }

    /**
     * Dealer draws cards until they have a hand value of 17 or higher
     */
    private void dealersTurn() {

        // Now it's the dealer's turn
        // Dealer will continue drawing until hand is valued at 17 or higher
        while (dealer.getHand().calculatedValue() < 17) {
            // dealer hits deck
            dealer.hit(deck, discarded);
            updateScreen();
        }
    }

    /**
     * Make the screen background a green color like a card table
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.decode("#426070"));
        g.fillRect(0, 0, 1000, 1000);
    }

    /**
     * Updates everything on the screen. Cards, Values, Scores, etc. except dealer
     * cards/value
     */
    private void updateScreen() {

        lblPlayerHandVal.setText("Player's Hand Value: " + player.getHand().calculatedValue());
        player.printHand(lblPlayerCards);
        // score
        lblScore.setText("Wins: " + wins + "   Losses: " + losses + "   Pushes: " + pushes + "");
        lblcredits.setText("CREDITS = " + credits);
    }

    /**
     * Start a new round, display score, give out cards, check for BlackJack, ask
     * player what they want to do
     */
    private void startRound() {
        /*
         * wins = 0; losses = 0; pushes = 1;
         * Card testCard = new Card(Suit.CLUB,Rank.NINE);
         * Card testCard2 = new Card(Suit.CLUB, Rank.TEN);
         */
        // If this isn't the first time, display the users score and put their cards
        // back in the deck
        if (wins > 0 || losses > 0 || pushes > 0) {
            System.out.println();
            System.out.println("Starting Next Round... Wins: " + wins + " Losses: " + losses + " Pushes: " + pushes);
            dealer.getHand().discardHandToDeck(discarded);
            player.getHand().discardHandToDeck(discarded);
        }

        // Check to make sure the deck has at least 4 cards left
        if (deck.cardsLeft() < 4) {
            deck.reloadDeckFromDiscard(discarded);
        }

        // Give the dealer two cards
        dealer.getHand().takeCardFromDeck(deck);
        dealer.getHand().takeCardFromDeck(deck);

        // Give the player two cards
        player.getHand().takeCardFromDeck(deck);
        player.getHand().takeCardFromDeck(deck);

        // in startRound after you give the player and dealer their two cards...
        updateScreen();
        lblDealerHandVal.setText("Dealer's hand value: " + dealer.getHand().getCard(0).getValue() + " + ?");
        lblGameMessage.setText("Starting round! Hit or Stand?");

        // Show the dealers hand with one card face down
        dealer.printHand(lblDealerCards);
        lblDealerCards[1].setIcon(new ImageIcon(new ImageIcon(IMAGE_DIR + "CardDown.png").getImage()
                .getScaledInstance(CARD_WIDTH, CARD_HEIGHT, Image.SCALE_DEFAULT)));

        // Show the player's hand
        player.printHand(lblPlayerCards);

        // Check if dealer has BlackJack to start
        if (dealer.hasBlackjack()) {
            // Show the dealer has BlackJack
            dealer.printHand(lblDealerCards);

            // Check if the player also has BlackJack
            if (player.hasBlackjack()) {
                // End the round with a push
                lblGameMessage.setText("Both 21 - Push");
                pushes++;
                credits+=50;

                // New round buttons
                btnHit.setVisible(false);
                btnStand.setVisible(false);
                btnNext.setVisible(true);
            } else {
                lblGameMessage.setText("Dealer has Blackjack!");
                dealer.printHand(lblDealerCards);
                losses++;
                credits-=50;

                // player lost, start a new round
                btnHit.setVisible(false);
                btnStand.setVisible(false);
                btnNext.setVisible(true);
            }
        }

        // Check if player has blackjack to start
        // If we got to this point, we already know the dealer didn't have blackjack
        if (player.hasBlackjack()) {
            // say player has blackjack
            lblGameMessage.setText("You have Blackjack!");
            // update score
            wins++;
            credits+=50;
            // make next round button only visible button
            btnHit.setVisible(false);
            btnStand.setVisible(false);
            btnNext.setVisible(true);

        }

    }

}
