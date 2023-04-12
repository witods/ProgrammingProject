package blackjack;

public abstract class Persoon {
    //hand houd de kaarten van de speler of deler bij
    private Hand hand;
    //houdt bij of het de kaarten van speler of deler zijn
    private String naam;

    /**
     * Nieuwe persoon aanmaken
     */
    public Persoon(){
        //Geven hem een hand en naam
        this.hand = new Hand();
        this.naam = "";
    }


    //Setters and Getters
    public Hand getHand(){

        return this.hand;
    }
    public void setHand(Hand hand){

        this.hand = hand;
    }
    public String getNaam(){

        return this.naam;
    }
    public void setNaam(String naam){

        this.naam = naam;
    }

    /**
     * Print het hand van de persoon
     */
    public void printHand(){
        //System.out.println(this.naam + "'s hand looks like this: ");
        System.out.println("Het hand van de " + this.naam + ": ");
        System.out.println(this.hand + "Waarde= " + this.hand.berekenWaarde());
    }

    /**
     * De speler neemt een kaart van de boek
     * @param boekKaarten - de boek me kaarten
     * @param discard - de boek waar de kaarten naar weg gaan
     */
    public void hit(BoekKaarten boekKaarten, BoekKaarten discard){

        //If there's no cards left in the deck
        if (!boekKaarten.hasCards()) {
            //reload the deck from the discard pile
            boekKaarten.nieuweBoekMaken(discard);
        }
        //take a card from the deck
        this.hand.neemkaartvanboek(boekKaarten);
        System.out.println(this.naam + " gets a card");
        //print out the hand
        this.printHand();
        //pause for a moment
        Game.pause();

    }

    /**
     * Controleert of persoon 21 heeft
     * @return True als de persoon 21 heeft
     */
    public boolean heeftBlackJack(){
        if(this.getHand().berekenWaarde() == 21){
            return true;
        }
        else{
            return false;
        }
    }
}
