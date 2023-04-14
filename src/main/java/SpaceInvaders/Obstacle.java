package SpaceInvaders;

import java.awt.*;

public class Obstacle {

    private int obstacleWidth;
    private int obstacleHeight;
    private int obstacleX;
    private int obstacleY;
    private Rectangle hitbox;
    private int maxHitpoints;
    private int hitPoints;
    public Obstacle(int width, int height, int x, int y){
        this.obstacleWidth = width;
        this.obstacleHeight = height;
        this.obstacleX = x;
        this.obstacleY = y;
        this.maxHitpoints = 3;
        hitPoints = maxHitpoints;
        this.hitbox = new Rectangle(obstacleX,obstacleY,obstacleWidth,obstacleHeight);
    }
    public int getObstacleWidth() {return obstacleWidth;}
    public int getObstacleHeight() {return obstacleHeight;}
    public int getObstacleX() {return obstacleX;}
    public int getObstacleY() {return obstacleY;}
    public  int getHitPoints(){return hitPoints;}
    public Rectangle getHitbox(){return hitbox;}
    public void setObstacleSize(int x,int y) {

    }
    public void setObstacleXandY(int x,int y){

    }
    public void hitTaken(){hitPoints--;}

    public void repair(){
        hitPoints = maxHitpoints;
    }
    public void drawObstacle(Graphics2D g){
        g.setColor(new Color(0,177,30));
        g.fillRect(obstacleX,obstacleY,obstacleWidth,obstacleHeight);
    }
}
