package blackjack;

public class Game {


    //Variablen voor de klasse game
    private BoekKaarten boekKaarten, verwijderdeKaarten;

    private Deler deler;
    private Speler speler;
    private int winst, verlies, gelijkspel;



    /**
     * Constructor voor Game
     */
    public Game(){

        //maak een boek van 52 kaarten aan
        boekKaarten = new BoekKaarten(true);
        //Maakt een lege boek aan
        verwijderdeKaarten = new BoekKaarten();

        //aanmaken spelers
        deler = new Deler();
        speler = new Speler();


        //kaarten delen + start 1e ronde
        boekKaarten.shuffle();
        startRonde();
    }


    /**
     * Nieuwe ronde starten
     */
    private void startRonde(){

        //Toon het volgende indien meer als 1 spel gespeeld.
        if(winst >0 || verlies >0 || gelijkspel > 0){
            System.out.println();
            System.out.println("Volgende ronde wordt gestart Winst= " + winst + " Verlies= "+ verlies + " Gelijkspel= "+ gelijkspel);
            deler.getHand().verwijderHandNaarBoek(verwijderdeKaarten);
            speler.getHand().verwijderHandNaarBoek(verwijderdeKaarten);
        }

        // Indien te weinig kaarten over, wordt een nieuwe speelboek aangemaakt.
        if(boekKaarten.kaartenOver() < 4){
            boekKaarten.nieuweBoekMaken(verwijderdeKaarten);
        }

        //geef de deler 2 kaarten
        deler.getHand().neemkaartvanboek(boekKaarten);
        deler.getHand().neemkaartvanboek(boekKaarten);

        //Geef de speler 2 kaarten
        speler.getHand().neemkaartvanboek(boekKaarten);
        speler.getHand().neemkaartvanboek(boekKaarten);

        //Toon het hand van de deler (1 kaart is geheim)
        deler.printEersteHand();

        //Toon het hand van de speler
        speler.printHand();

        //Controleer of de deler 21 punten heeft
        if(deler.heeftBlackJack()){
            deler.printHand();

            if(speler.heeftBlackJack()){
                //End the round with a push
                System.out.println("Allebei 21. Het is gelijkspel");
                gelijkspel++;
                //nieuwe ronde starten
                startRonde();
            }
            else{
                System.out.println("De deler wint!");
                deler.printHand();
                verlies++;
                //nieuwe ronde starten
                startRonde();
            }
        }

        //Als de speler blackjack heeft, jij wint!
        if(speler.heeftBlackJack()){
            System.out.println("Jij hebt blackjack, je wint!");
            winst++;
            startRonde();
        }

        //Let the player decide what to do next
        //pass the decks in case they decide to hit
        speler.maakBeslissing(boekKaarten, verwijderdeKaarten);

        //Controleer of de speler verbrand is.
        if(speler.getHand().berekenWaarde() > 21){
            System.out.println("You have gone over 21.");
            verlies++;
            startRonde();
        }

        //Beurt deler
        deler.printHand();
        while(deler.getHand().berekenWaarde()<17){
            deler.hit(boekKaarten, verwijderdeKaarten);
        }

        //controle wie er wint
        if(deler.getHand().berekenWaarde()>21){
            System.out.println("De deler is kapot!");
            winst++;
        }
        else if(deler.getHand().berekenWaarde() > speler.getHand().berekenWaarde()){
            System.out.println("Jij verliest!");
            verlies++;
        }
        else if(speler.getHand().berekenWaarde() > deler.getHand().berekenWaarde()){
            System.out.println("Jij wint!");
            winst++;
        }
        else{
            System.out.println("Gelijkspel!");
            gelijkspel++;
        }

        //Nieuwe ronde starten!
        startRonde();
    }

    /**
     * de Game wordt vertraagd. Dit om de speelbaarheid te bevorderen.
     */
    public static void pause(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
