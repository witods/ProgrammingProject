package SpaceInvaders;

import java.awt.*;

public abstract class PowerUp {
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
    public void draw(Graphics2D g){
        g.setColor(Color.MAGENTA);
        g.draw(this.hitbox);
    }
    private Rectangle createHitbox(int x, int y, int width, int height) {
        Rectangle h = new Rectangle(x,y,width,height);
        return h;
    }
    public abstract void activatePower();
    public Rectangle getHitbox(){return this.hitbox;}
    public String getPowerUpType(){return this.powerUpType;}

}
