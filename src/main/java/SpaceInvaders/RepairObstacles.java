package SpaceInvaders;


import java.util.ArrayList;

public class RepairObstacles extends PowerUp{
    private MainGame game;
    public RepairObstacles(int x, int y,MainGame g){
        super(x,y);
    }
    public void activatePower(){
        for(Obstacle o : game.getObstacles()) {
            o.repair();
        }
    }
}
