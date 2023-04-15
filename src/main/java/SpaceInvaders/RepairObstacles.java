package SpaceInvaders;


import java.util.ArrayList;

public class RepairObstacles extends PowerUp{

    public RepairObstacles(int x, int y,MainGame g,String s){
        super(x,y,g,s);

    }
    public void activatePower(){
        for(Obstacle o : super.game.getObstacles()) {
            o.repair();
        }
    }
}
