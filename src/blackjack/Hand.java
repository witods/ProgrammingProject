package blackjack;

import java.util.ArrayList;

public class Hand {

    private ArrayList<Kaart> hand;

    public Hand(){
        hand = new ArrayList<Kaart>();
    }

    /**
     * neem de bovenste kaart van de boek
     * @param boekKaarten de boek waar de kaarten van genomen worden
     */
    public void neemkaartvanboek(BoekKaarten boekKaarten){
        hand.add(boekKaarten.neemKaart());
    }


    /**
     *
     * @param verwijderBoekKaarten De boek waar de kaart naar verwijderd wordt.
     */
    public void verwijderHandNaarBoek(BoekKaarten verwijderBoekKaarten){

        //copy cards from hand to discardDeck
        verwijderBoekKaarten.addCards(hand);

        //clear the hand
        hand.clear();

    }

    public String toString(){
        String output = "";
        for(Kaart kaart : hand){
            output += kaart + " - ";
        }
        return output;
    }


    /**
     *
     * @return de waarde als een integer
     *
     */
    public int berekenWaarde(){

        //variabele om het aantal azen te tellen
        int waarde = 0;
        int aantalAzen = 0;

        //Voor elke kaart in je hand
        for(Kaart kaart : hand){
            waarde += kaart.getValue();
            if (kaart.getValue() == 11){
                aantalAzen ++;
            }
        }

        //In een scenario met meerdere azen. Kan de aas de waarde 1 of 11 aannemen.
        // Indien mogelijk zet de waarde van de aas naar 1, zodat we onder de 21 blijven
        if (waarde > 21 && aantalAzen > 0){
            while(aantalAzen > 0 && waarde > 21){
                aantalAzen --;
                waarde -= 10;
            }
        }
        return waarde;

    }


    /**
     *
     * @param idx de index van de kaart die we hebben
     * @return de kaart die we hebben
     */
    public Kaart neemKaart(int idx){
        return hand.get(idx);
    }
}
