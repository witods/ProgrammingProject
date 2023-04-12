package blackjack;

public class Deler extends Persoon {
    /**
     * Maakt een een persoon met naam deler
     */
    public Deler(){

        super.setNaam("Deler");

    }

    /**
     * Print de hand van de deler. 1 kaart blijft geheim.
     */
    public void printEersteHand(){
        System.out.println("Het hand van de deler: ");
        System.out.println(super.getHand().neemKaart(0));
        System.out.println("De 2e kaart is geheim.");
    }

}

