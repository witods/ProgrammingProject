package SpaceInvaders;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

public class Player {
    private Polygon hitbox;
    private int hitpoints;
    private int playerX;
    private int playerY;
    private int playerWidth;
    private int playerHeight;
    private boolean switchCannons;
    private Rectangle playerHitbox;
    private ArrayList<Projectile>Bullets;
    private String powerUp;
    private boolean powerUpActive;

    public Player(int width, int height,int h) {
        this.playerWidth = width;
        this.playerHeight = height;
        this.hitpoints = h;
    }
    public void createHitbox(){
        this.playerHitbox = new Rectangle(playerX,playerY,playerWidth,playerHeight);
    }
    public void healthUP(){

    }
    public void healthDown(Projectile p){
        switch (p.getProjectileType()){
            case "bullet" : this.hitpoints-=1;
        }
    }
    public int getPlayerHitpoints() {return this.hitpoints;}

    public void movePlayer(int key,int maxwiDth){
        if(key == KeyEvent.VK_LEFT&&playerX>0) this.playerX -= 10;
        if(key == KeyEvent.VK_RIGHT&&playerX<maxwiDth-this.playerWidth) this.playerX += 10;
        this.playerHitbox.setLocation(playerX,playerY);
    }
    public void setPlayerSize(int x,int y){
        if(x>=75){
            this.playerWidth = x;
        }else this.playerWidth=75;
        if(y>=35){
            this.playerHeight = y;
        }else this.playerHeight=35;
    }
    public void setPlayerXandY(int x, int y){
        this.playerX = x;
        this.playerY = y;
    }
    public int getPlayerX() {return playerX;}
    public int getPlayerY() {return playerY;}
    public int getPlayerWidth(){return playerWidth;}
    public int getPlayerHeight() {return playerHeight;}
    public Rectangle getPlayerHitbox() {return playerHitbox;}

    public void drawPlayer(Graphics2D g){
        g.setColor(Color.red);
        g.fillRect(playerX,playerY,playerWidth,playerHeight);
    }
    public void shotBullet(ArrayList a){
        if(switchCannons){
            a.add(new Projectile(3,12,getPlayerX()+10,getPlayerY()-15,Color.CYAN));
            switchCannons = false;
        }else {
            a.add(new Projectile(3,12,getPlayerX()+getPlayerWidth()-13,getPlayerY()-15,Color.cyan));
            switchCannons = true;
        }
    }
    public void setPowerUp(String s){
        this.powerUp = s;
    }
}
