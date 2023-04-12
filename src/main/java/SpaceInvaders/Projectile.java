package SpaceInvaders;

import java.awt.*;

public class Projectile {

    private int projectileX;
    private int projectileY;
    private int projectileWidth;
    private int projectileHeight;
    private int damage;
    private Color color;
    private Rectangle hitbox;
    private String projectileType = "bullet";

    public Projectile(int width,int height, int x, int y,Color c){
        this.projectileWidth = width;
        this.projectileHeight = height;
        this.projectileX = x;
        this.projectileY = y;
        this.color = c;
        this.hitbox = new Rectangle(projectileX,projectileY,projectileWidth,projectileHeight);
    }
    public int getProjectileX() {return projectileX;}
    public int getProjectileY() {return projectileY;}
    public Rectangle getHitbox(){return hitbox;}
    public String getProjectileType(){return projectileType;}
    public void setProjectileDamage(int x){this.damage = x;}
    public void setPowerUp(){

    }

    public void drawProjectile(Graphics2D g){
        g.setColor(this.color);
        g.fillRect(this.projectileX,this.projectileY,this.projectileWidth,this.projectileHeight);
    }
    public boolean movePlayerProjectile(){
        if(this.projectileY>=0) {
            this.projectileY -=10;
            hitbox.setLocation(this.projectileX,this.projectileY);
            return true;
        }else return false;
    }

    public boolean moveEnemyProjectile(int windowHeight){
        if(this.projectileY<=windowHeight){
            this.projectileY+=8;
            hitbox.setLocation(this.projectileX,this.projectileY);
            return true;
        }else return false;
    }

}
