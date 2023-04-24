package battleship;

/**
 * This class makes the non-graphic playfield
 */
public class Field {
    private Ship battleship1;
    private Ship battleship2;
    private Ship battleship3;
    private Ship battleship4;
    private Ship battleship5;

    private int [][] field = new int [10][10];

    /**
     * Constructor
     */
    public Field() {
        this.battleship1 = new Ship("Patrouilleschip", 2, 1);
        this.battleship2 = new Ship("Onderzeeër", 3, 2);
        this.battleship3 = new Ship("Onderzeeër", 3, 3);
        this.battleship4 = new Ship("Slagschip", 4, 4);
        this.battleship5 = new Ship("Vliegdekschip",5, 5);


        placeRandomShip(battleship1);
        placeRandomShip(battleship2);
        placeRandomShip(battleship3);
        placeRandomShip(battleship4);
        placeRandomShip(battleship5);
    }

    /**
     * Getters
     */
    public Ship getBattleship1() {
        return battleship1;
    }

    public Ship getBattleship2() {
        return battleship2;
    }

    public Ship getBattleship3() {
        return battleship3;
    }

    public Ship getBattleship4() {
        return battleship4;
    }

    public Ship getBattleship5() {
        return battleship5;
    }

//    public void printField(){
//        System.out.println("   0:1:2:3:4:5:6:7:8:9:");
//        for(int i=0; i<field.length; i++){
//            System.out.print(i + ": ");
//            for(int j=0; j<field.length; j++){
//                System.out.print(field[i][j] + " ");
//            }
//            System.out.println();
//        }
//    }

    /**
     * This method places ship at random on the field
     */
    private void placeRandomShip(Ship ship) {
//        ship.printShip();
        int startposition = ship.getStartPosition();
        int length = ship.getLength();
        int direction = ship.getDirection();
        int code = ship.getCode();
        if(direction==0){
            while(!checkHorizontal(startposition,length)){
                ship.setStartPosition();
                startposition = ship.getStartPosition();
            }
            placeHorizontal(startposition, length, code);
        } else if(direction==1){
           while(!checkVertical(startposition,length)){
                ship.setStartPosition();
                startposition = ship.getStartPosition();
            }
            placeVertical(startposition, length, code);
        }
    }


    /**
     * This method places a ship horizontal
     * @param startposition
     * @param length
     * @param code
     */
    private void placeHorizontal(int startposition, int length, int code){
        int i = startposition/10;
        int j = startposition%10;
        for (int k=0; k<length;k++){
            field[i][j+k]=code;
        }
    }

    /**
     * This method places a ship vertical
     * @param startposition
     * @param length
     * @param code
     */
    private void placeVertical(int startposition, int length, int code){
        int i = startposition/10;
        int j = startposition%10;
        for (int k=0; k<length;k++){
            field[i+k][j]=code;
        }
    }

    /**
     * This method checks if there is already a ship at the horizontal position
     * @param startposition
     * @param length
     * @return
     */
    private boolean checkHorizontal(int startposition, int length){
        int i=startposition/10;
        int j=startposition%10;
        int checkLength=j+length-1;
        if(j+length>10){
            return false;
        } else {
            while (j <= checkLength) {
                if (checkNumberPosition(i, j) > 0) {
                    return false;
                }
                j++;
            }
            return true;
        }
    }

    /**
     * This method checks if there is already a ship at the vertical position
     * @param startposition
     * @param length
     * @return
     */
    private boolean checkVertical(int startposition, int length)
    {
        int i=startposition/10;
        int j=startposition%10;
        int checkLength = i+length-1;
        if(i+length>10){
            return false;
        } else {
            while(i<=checkLength){
                if(checkNumberPosition(i,j)>0){
                    return false;
                }
                i++;
            }
            return true;
        }
    }

    /**
     * This method checks which number is assigned to a certain position
     * @param i
     * @param j
     * @return
     */
    private int checkNumberPosition(int i, int j){
        return field[i][j];
    }

    /**
     * This method returns the number of a certain position and subtracts a hit if there is a ship located
     * @param position
     * @return number of the position
     */
    public int shootShip(int position){
        /*
            O: water
            1, 2, 3, 4, 5: code ship
        */

        int i = position/10;
        int j = position%10;
        switch (checkNumberPosition(i,j)) {
            case 0: {
                return 0;
            }
            case 1: {
                battleship1.subtractHit();
                return 1;
            }
            case 2: {
                battleship2.subtractHit();
                return 2;
            }
            case 3: {
                battleship3.subtractHit();
                return 3;
            }
            case 4: {
                battleship4.subtractHit();
                return 4;
            }
            case 5: {
                battleship5.subtractHit();
                return 5;
            }
        }
        return -1;
    }
}
