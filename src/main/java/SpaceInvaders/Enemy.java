package SpaceInvaders;

import java.awt.*;
import java.util.ArrayList;

public class Enemy {

    private Rectangle hitbox;
    private int enemyX;
    private int enemyY;
    private int enemyWidth;
    private int enemyHeight;
    private int hitpoints;
    private boolean isDead;
    public Enemy(int x, int y, int width, int height,int h){
        this.enemyX = x;
        this.enemyY = y;
        this.enemyWidth = width;
        this.enemyHeight = height;
        this.hitpoints = h;
        hitbox = new Rectangle(enemyX,enemyY,enemyWidth,enemyHeight);
    }
    public int getEnemyX(){return enemyX;}
    public int getEnemyY(){return enemyY;}
    public int getEnemyWidth(){return enemyWidth;}
    public int getEnemyHeight(){return enemyHeight;}
    public Rectangle getHitbox(){return hitbox;}
    public int getHitpoints(){return hitpoints;}
    public boolean enemyDead(){return isDead;}

    public void drawEnemy(Graphics2D g){
        g.setColor(Color.yellow);
        g.fillRect(enemyX,enemyY,enemyWidth,enemyHeight);
    }
    public void moveEnemy(int screenWidth, int screenHeight){
        enemyX += 5;
        if(enemyX >= screenWidth-20){
            enemyX = 20;
            enemyY += 45;
        }
        hitbox.setLocation(enemyX,enemyY);
    }
    public void shootProjectile(ArrayList a){
        int random = (int) (Math.random()*300);
        System.out.println(random);
        if(random==1){
            a.add(new Projectile(2,8,enemyX+(enemyWidth/2),enemyY+enemyHeight, Color.yellow));
        }
    }
    public boolean enemyHit(int x){
        hitpoints -= x;
        if(hitpoints<=0) isDead = true;
        return isDead;
    }
}
