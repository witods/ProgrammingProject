package blackjack;

public class Kaart implements Comparable<Kaart>{
    private Kleur kleur;
    private Nummer nummer;

    /**
     *
     * @param kleur  De kleur van de kaart.
     * @param nummer  De nummer vab de kaart.
     */
    public Kaart(Kleur kleur, Nummer nummer){
        this.kleur = kleur;
        this.nummer = nummer;
    }

    /**
     * Copy constructor
     * @param kaart kopie van de kaart
     */
    public Kaart(Kaart kaart){
        this.kleur = kaart.getKleur();
        this.nummer = kaart.getNummer();
    }

    /**
     *
     * @return  de waarde van de kaart
     */
    public int getValue(){
        return nummer.nummerWaarde;
    }

    /**
     *
     * @return de kleur van de kaart
     */
    public Kleur getKleur(){
        return kleur;
    }

    public Nummer getNummer(){

        return nummer;
    }

    public String toString(){
        //return ("["+ nummer +" of "+ kleur + "] ("+this.getValue()+")");
        return (kleur + " " + nummer) + "("  + (this.getValue()) + ")";

    }

    /**
     * Vergelijken van 1 kaart tegenover een andere
     * @param c the card being compared
     * @return 1 if groter, -1 if kleiner, 0 if gelijk
     */
    @Override
    public int compareTo(Kaart c) {
        if(this.getValue() > c.getValue()){
            return 1;
        }
        else if(this.getValue() < c.getValue()){
            return -1;
        }
        else{
            return 0;
        }
    }

}


