package blackjack;

import java.util.Scanner;

public class Speler extends Persoon {
    Scanner input = new Scanner(System.in);

    //Nieuwe speler aanmaken
    public Speler() {
        super.setNaam("Speler");

    }

    /**
     * De speler heeft de keuze tussen hit en stand
     * @param boekKaarten - boek kaarten waar uit getrokken wordt
     * @param verwijderkaart - kaart verwijderen
     */
    public void maakBeslissing(BoekKaarten boekKaarten, BoekKaarten verwijderkaart) {

        //beslissing houd keuze 1 of 2
        int  beslissing = 0;

        boolean getNummer = true;

        while(getNummer){
            //try
            try{
                System.out.println(" 1) Hit of 2) Stand");
                beslissing = input.nextInt();
                getNummer = false;

            }
            catch(Exception e){
                System.out.println("Foutief");
                input.next();
            }
        }

        //als keuze ==1
        if (beslissing == 1) {
            this.hit(boekKaarten, verwijderkaart);
            if(this.getHand().berekenWaarde()>20){
                return;
            }
            else{
                this.maakBeslissing(boekKaarten, verwijderkaart);
            }

        } else {
            System.out.println("STAND!");
        }


    }
}
