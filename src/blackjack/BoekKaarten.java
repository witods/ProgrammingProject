package blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class BoekKaarten {
    //arraylist die onze boek kaarten bijhoudt
    private ArrayList<Kaart> boekKaarten;


    /**
     * Lege deck kaarten creeeren
     */
    public BoekKaarten(){
        boekKaarten = new ArrayList<Kaart>();
    }

    /**
     * Copy Constructor
     * @param c de boek wordt gecopieerd.
     */
    public BoekKaarten(BoekKaarten c){
        Collections.copy(this.boekKaarten, c.getCards());
    }

    /**
     * Maakt een standaard deck kaarten
     * @param maakBoek indien true, maakt een boek kaarten aan
     */
    public BoekKaarten(boolean maakBoek){
        boekKaarten = new ArrayList<Kaart>();
        if(maakBoek){
            //Voor alle kleuren(schoppes, ruiten, harten, klavers)
            for(Kleur KLEUR : Kleur.values()){
                //Alle nummers van het kaartspel (Aas,2,3,...,Koningin, koning,)
                for(Nummer nummer : Nummer.values()){
                    //nieuwe kaart toevoegen voor elke kleur en nummer
                    boekKaarten.add(new Kaart(KLEUR, nummer));
                }
            }
        }
    }

    /**
     *
     * @param kaart Kaart aan boek toevoegen
     */
    public void addCard(Kaart kaart){
        boekKaarten.add(kaart);
    }

    /**
     *
     * @param kaarten een array list van toe te voegen kaarten
     */
    public void addCards(ArrayList<Kaart> kaarten){
        boekKaarten.addAll(kaarten);
    }

    public String toString(){
        String output = "";

        for(Kaart kaart : boekKaarten){
            output += kaart;
            output += "\n";
        }
        return output;
    }

    /**
     * De kaarten random plaatsen
     */
    public void shuffle(){
        Collections.shuffle(boekKaarten, new Random());
    }

    /**
     *
     * @return 1 kaart nemen en deze returnen
     */
    public Kaart neemKaart(){

        //Een copie van de 1e kaart nemen
        Kaart kaartToTake = new Kaart(boekKaarten.get(0));
        //De kaart verwijderen van de boek
        boekKaarten.remove(0);
        //De kaart nemen
        return kaartToTake;

    }

    /**
     *
     * @return trua als er nog kaarten in de boek zitten
     */
    public boolean hasCards(){
        if (boekKaarten.size()>0){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     *
     * @return Het aantal kaarten die over blijven
     */
    public int kaartenOver(){

        return boekKaarten.size();
    }

    /**
     *
     * @return de arraylist die alle kaarten in de boek bevat
     */
    public ArrayList<Kaart> getCards() {

        return boekKaarten;
    }

    /**
     * Maakt de boek leeg
     */
    public void maakBoekLeeg(){

        boekKaarten.clear();
    }


    /**
     * Alle verwijderde kaarten in de boek plaatsen en opnieuw de boek delen
     * Oude deck verwijderen
     * @param verwijderdeKaart - de verwijderde kaarten waarmee we een nieuwe boek maken.
     */
    public void nieuweBoekMaken(BoekKaarten verwijderdeKaart){
        this.addCards(verwijderdeKaart.getCards());
        this.shuffle();
        verwijderdeKaart.maakBoekLeeg();
        System.out.println("Kaarten zijn op, we maken een nieuwe boek aan!");
    }
}
