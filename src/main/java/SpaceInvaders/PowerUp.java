package SpaceInvaders;

import MainApp.Main;

import java.awt.*;

public abstract class PowerUp {
    protected String powerUpType;
    private Rectangle hitbox;
    private int powerX;
    private int powerY;
    private int powerWidth;
    private int powerHeight;
    protected MainGame game;
    public PowerUp(int x, int y,MainGame g,String s){
        this.powerX = x;
        this.powerY = y;
        powerUpType = s;
        game = g;
        this.powerWidth = 15;
        this.powerHeight = 15;
        hitbox = createHitbox(x,y,powerWidth,powerHeight);
    }
    public void draw(Graphics2D g){
        g.setColor(Color.MAGENTA);
        g.draw(this.hitbox);
    }
    public void drawString(Graphics2D g,Font f){
        System.out.println("Drawing");
        String msg = powerUpType + " activated!";
        FontMetrics metrics = g.getFontMetrics();
        int fontHeight = metrics.getHeight();
        int fontWidth = metrics.stringWidth(msg);
        int stringX = (game.getCurrentWidth()/2) - (fontWidth/2);
        int stringY = (game.getCurrentHeight()/2) - (fontHeight/2);

        g.setColor(new Color(255, 255, 255, 175));
        g.setFont(f);
        g.drawString(msg,stringX,stringY);
    }
    private Rectangle createHitbox(int x, int y, int width, int height) {
        Rectangle h = new Rectangle(x,y,width,height);
        return h;
    }
    public abstract void activatePower();
    public Rectangle getHitbox(){return this.hitbox;}
    public String getPowerUpType(){return this.powerUpType;}

}
