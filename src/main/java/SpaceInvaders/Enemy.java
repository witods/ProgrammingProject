package SpaceInvaders;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Enemy {

    private Rectangle hitbox;
    private int enemyX;
    private int enemyY;
    private int enemyWidth;
    private int enemyHeight;
    private int hitpoints;
    private MainGame game;
    public Enemy(int x, int y, int width, int height,int h, MainGame m){
        this.enemyX = x;
        this.enemyY = y;
        this.enemyWidth = width;
        this.enemyHeight = height;
        this.hitpoints = h;
        this.game = m;
        hitbox = new Rectangle(enemyX,enemyY,enemyWidth,enemyHeight);
    }
    public int getEnemyX(){return enemyX;}
    public int getEnemyY(){return enemyY;}
    public int getEnemyWidth(){return enemyWidth;}
    public int getEnemyHeight(){return enemyHeight;}
    public Rectangle getHitbox(){return hitbox;}
    public int getHitpoints(){return hitpoints;}

    public void drawEnemy(Graphics2D g){
        g.setColor(Color.DARK_GRAY);
        g.fillRect(enemyX,enemyY,enemyWidth,enemyHeight);
    }
    public void moveEnemy(int screenWidth, int screenHeight, boolean b){
        if(!b){
            enemyX += 5;
            if(enemyX >= screenWidth-20){
                enemyX = 20;
                enemyY += 45;
            }
        }
        hitbox.setLocation(enemyX,enemyY);
    }
    public void gravityPull(int x,int y){

        if(enemyX>x){
            enemyX-=15;
        }else if(enemyX<x){
           enemyX+=15;
        }
        if(enemyY>y){
            enemyY-=15;
        }else if(enemyY<y){
            enemyY+=15;
        }
        hitbox.setLocation(enemyX,enemyY);
    }
    public void setEnemyX(int x){enemyX -= x;}
    public void setEnemyY(int y){enemyY -= y;}
    public void shootProjectile(ArrayList<Projectile> a){
        int random = (int) (Math.random()*350);
        if(random==1){
            a.add(new Projectile(enemyX+(enemyWidth/2),enemyY+enemyHeight, "enemy"));
        }
    }
    public boolean enemyHit(int x,ArrayList<PowerUp> a){
        hitpoints -= x;
        if(hitpoints<=0) {
            enemyDied(a);
            return true;
        }

        return false;
    }
    public void enemyDied(ArrayList<PowerUp> a){
        int randomNumber = (int) (Math.random()*350+1);
        int powerUpX = enemyX +(enemyWidth - 15)/2;
        int powerUpY = enemyY + (enemyHeight-15)/2;
        switch (randomNumber){
            case 1 : a.add(new CannonBullet(powerUpX,powerUpY,game,"Cannon"));
            break;
            case 50 : a.add(new RapidFire(powerUpX,powerUpY,game,"Rapid fire"));
            break;
            case 100 : a.add(new EMP(powerUpX,powerUpY,game,"EMP"));
            break;
            case 150 : a.add(new Blackhole(powerUpX,powerUpY,game,"Blackhole"));
            break;
            case 200 : a.add(new RepairObstacles(powerUpX,powerUpY,game,"Repair"));
            break;
        }
    }
}
