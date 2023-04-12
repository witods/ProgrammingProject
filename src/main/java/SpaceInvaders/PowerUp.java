package SpaceInvaders;

import java.awt.*;

public class PowerUp {
    private String powerUpType;
    private Rectangle hitbox;
    private int powerX;
    private int powerY;
    private int powerWidth;
    private int powerHeight;
    public PowerUp(int x, int y){
        getPowerUpType();
        this.powerX = x;
        this.powerY = y;
        this.powerWidth = 15;
        this.powerHeight = 15;
        hitbox = createHitbox(x,y,powerWidth,powerHeight);
    }
    private void getPowerUpType(){
        int randomNumber = 2;
                //(int) (Math.random()*3+1);
        switch (randomNumber){
            case 1 : powerUpType = "cannon";
            break;
            case 2 : powerUpType = "rapidfire";
            break;
            case 3 : powerUpType = "blackhole";
        }
    }
    public void draw(Graphics2D g){
        g.setColor(Color.MAGENTA);
        g.draw(this.hitbox);
    }
    private Rectangle createHitbox(int x, int y, int width, int height) {
        Rectangle h = new Rectangle(x,y,width,height);
        return h;
    }
    public Rectangle getHitbox(){return this.hitbox;}
    public void setPowerUpPlayer(Player p) {
        p.setPowerUp(this.powerUpType);
    }
}
