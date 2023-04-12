package blackjack;

public enum Kleur {
    KLAVER("Klaveren"),
    RUIT("Ruiten"),
    HART("Harten"),
    SCHOP("SCHOPPES");

    String kleurSoort;

    Kleur(String kleurSoort) {

        this.kleurSoort = kleurSoort;
    }

    public String toString(){

        return kleurSoort;
    }
}
