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
    private String projectileType;

    public Projectile(int x, int y){
        this.projectileX = x;
        this.projectileY = y;
        this.projectileWidth = 3;
        this.projectileHeight = 12;
        this.hitbox = new Rectangle(projectileX,projectileY,projectileWidth,projectileHeight);
        this.damage = 1;
        color = Color.cyan;
    }
    public Projectile(int x, int y,String s){
        this(x,y);
        projectileType = s;
        setupProjectile();
        this.hitbox = new Rectangle(projectileX,projectileY,projectileWidth,projectileHeight);
    }
    public int getProjectileX() {return projectileX;}
    public int getProjectileY() {return projectileY;}
    public int getDamage(){return damage;}
    public Rectangle getHitbox(){return hitbox;}
    public String getProjectileType(){return projectileType;}

   public void setupProjectile(){
        switch(projectileType){
            case "cannon" : color = new Color(6,63,185); projectileWidth = 18; projectileHeight = 18; damage = 3;
            break;
            case "enemy" : color = Color.yellow; projectileWidth = 2; projectileHeight = 8;
            break;
        }
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
