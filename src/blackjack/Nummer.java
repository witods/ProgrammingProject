package blackjack;

public enum Nummer {
    AAS("Aas", 11),
    TWEE("Twee", 2),
    DRIE("Drie", 3),
    VIER("Vier", 4),
    VIJF("Vijf", 5),
    ZES("Zes", 6),
    ZEVEN("Zeven", 7),
    ACHT("Acht", 8),
    NEGEN("Negen", 9),
    TIEN("Tien", 10),
    BOER("Boer", 10),
    KONINGIN("Koningin", 10),
    KONING("Koning", 10);

    String nummerNaam;
    int nummerWaarde;

    Nummer(String nummerNaam, int nummerWaarde) {
        this.nummerNaam = nummerNaam;
        this.nummerWaarde = nummerWaarde;
    }

    public String toString() {
        return nummerNaam;

    }

}
