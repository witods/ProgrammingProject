package battleship;

import java.util.Random;

/**
 * This class contains methods to create and to manage ships
 */
public class Ship {

    private String name;
    private int startPosition;
    private int length;
    private int direction; // 0 = horizontal, 1 = vertical

    private int code; // achterliggende code voor werking game

    private int totalHits;

    /**
     * Constructor
     * @param name
     * @param length
     * @param code
     */

    public Ship(String name, int length, int code){
        this.name = name;
        Random random = new Random();
        setStartPosition();
        this.length=length;
        this.direction = random.nextInt(2);
        this.code = code;
        this.totalHits = length;
    }

    /**
     * Getters en setters
     */

    public String getName() {
        return name;
    }

    public int getStartPosition() {
        return startPosition;
    }

    public int getLength() {
        return length;
    }

    public int getDirection() {
        return direction;
    }

    public void setStartPosition() {
        Random random = new Random();
        this.startPosition = random.nextInt(100);

    }

    public int getTotalHits() {
        return totalHits;
    }

    public int getCode() {
        return code;
    }

//    public void printShip(){
//        System.out.println("Startposition: " + startPosition + ", length: " + length + ", direction: " + direction);
//    }

    /**
     * This method reduces the totalHits by 1
     */
    public void subtractHit() {
        totalHits --;
    }


}
