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
    private boolean cannonActive;
    private boolean rapidFireActive;
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
        this.hitpoints -= p.getDamage();
        if(hitpoints <=0);
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
    public void setCannonActive(boolean cannonActive) {
        this.cannonActive = cannonActive;
    }
    public void setRapidFireActive(boolean rapidFireActive) {
        this.rapidFireActive = rapidFireActive;
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
    public void shootProjectile(ArrayList a){
        if(cannonActive){
            shootCannon(a);
            return;
        }
        if(rapidFireActive){
            shootRapidFire(a);
            return;
        }
        else shootNormalBullet(a);
    }
    public void shootNormalBullet(ArrayList a){
        if(switchCannons){
            a.add(new Projectile(getPlayerX()+10,getPlayerY()-15,1));
            switchCannons = false;
        }else {
            a.add(new Projectile(getPlayerX()+getPlayerWidth()-13,getPlayerY()-15,1));
            switchCannons = true;
        }
    }
    public void shootCannon(ArrayList a){
        a.add(new Projectile(getPlayerX()+getPlayerWidth()/2,getPlayerY()-27,"cannon",3));
    }
    public void shootRapidFire(ArrayList a){
        a.add(new Projectile(getPlayerX()+(playerWidth/2),getPlayerY()-25,1));
        a.add(new Projectile(getPlayerX()+6,getPlayerY()-15,1));
        a.add(new Projectile(getPlayerX()+getPlayerWidth()-6,getPlayerY()-15,1));
    }
}
