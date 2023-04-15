package SpaceInvaders;

import java.awt.*;

public class Obstacle {
    private int originalX;
    private int obstacleWidth;
    private int obstacleHeight;
    private int obstacleX;
    private int obstacleY;
    private Rectangle hitbox;
    private int maxHitpoints;
    private int hitPoints;
    public Obstacle(int width, int height, int x, int y){
        this.originalX = x;
        this.obstacleWidth = width;
        this.obstacleHeight = height;
        this.obstacleX = x;
        this.obstacleY = y;
        this.maxHitpoints = 100;
        hitPoints = maxHitpoints;
        this.hitbox = new Rectangle(obstacleX,obstacleY,obstacleWidth,obstacleHeight);
    }
    public int getObstacleWidth() {return obstacleWidth;}
    public int getObstacleHeight() {return obstacleHeight;}
    public int getObstacleX() {return obstacleX;}
    public int getObstacleY() {return obstacleY;}
    public  int getHitPoints(){return hitPoints;}
    public Rectangle getHitbox(){return hitbox;}
    public void setHitbox(int x,int y,int w,int h){
        hitbox.setLocation(x,y);
        hitbox.setSize(w,h);
    }
    public void setObstacleSize(int x,int y) {

    }
    public void setObstacleXandY(int x,int y){

    }
    public void hitTaken(){
        hitPoints--;
        if(hitPoints>=30&&hitPoints%3==0){
            obstacleWidth-=5;
            obstacleHeight-=5;
            obstacleX+=2;
            setHitbox(obstacleX,obstacleY,obstacleWidth,obstacleHeight);
        }
    }

    public void repair(){
        hitPoints = maxHitpoints;
        obstacleWidth=55;
        obstacleHeight=30;
        obstacleX=originalX;
        obstacleY = 400;
        setHitbox(obstacleX,obstacleY,obstacleWidth,obstacleHeight);
    }
    public Color setObstacleColor(){
        if(hitPoints>=75) {
            return new Color(0,177,30);
        }else if(hitPoints<80&&hitPoints>=50){
            return new Color(120,177,35);
        }else if(hitPoints<50&&hitPoints>=25){
            return new Color(226,255,0);
        }
        else{
            return new Color(207,0,3);
        }
    }
    public void drawObstacle(Graphics2D g){
        g.setColor(setObstacleColor());
        g.fillRect(obstacleX,obstacleY,obstacleWidth,obstacleHeight);
    }
}
