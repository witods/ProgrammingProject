package SpaceInvaders;

import MainApp.Main;
import com.google.common.util.concurrent.Runnables;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Blackhole extends PowerUp implements Runnable{

    private int blackholeX;
    private int blackholeY;
    private int blackHoleWidth;
    private int blackHoleHeight;
    private MainGame game;
    private Rectangle blackholeHitbox;
    public Blackhole(int x, int y,MainGame g){
        super(x,y);
        game = g;
        setBlackholeXandY();
        blackHoleWidth = 50;
        blackHoleHeight = 50;
    }
    public Rectangle getBlackholeHitbox(){return blackholeHitbox;}
    public void setBlackholeXandY(){
        blackholeX = game.getCurrentWidth()/2-25;
        blackholeY = ((game.getCurrentHeight()-((400-game.getCurrentHeight())*-1))/2)-25;
    }

    public void setBlackholeHitbox(int x, int y) {
        blackholeHitbox.setSize(x,y);
    }

    public void activatePower() {
        blackholeHitbox = new Rectangle(blackholeX-10,blackholeY-10,blackHoleWidth+20,blackHoleHeight+20);
        game.setBlackhole(this);
        game.setBlackholeActive(true);
        game.setBlackholeCenter(blackholeX+(blackHoleWidth/2),blackholeY-(blackHoleHeight/2));
        game.getEnemyBullets().clear();
        game.getPlayerBullets().clear();
    }

    public void drawBackhole(Graphics2D g) {
        g.setColor(Color.white);
        g.setStroke(new BasicStroke(5));
        g.fillOval(blackholeX,blackholeY,blackHoleWidth,blackHoleHeight);
        game.repaint();
    }

    @Override
    public void run() {
        int i = 0;
        Thread.currentThread().setPriority(4);
        while (i<5){
            blackholeX -= 30;
            blackholeY -= 30;
            blackHoleWidth += 60;
            blackHoleHeight += 60;
            setBlackholeHitbox(blackHoleWidth+20,blackHoleHeight+20);

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            blackholeX += 10;
            blackholeY += 10;
            blackHoleWidth -= 20;
            blackHoleHeight -= 20;
            setBlackholeHitbox(blackHoleWidth+20,blackHoleHeight+20);

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
        }
        game.getEnemies().clear();
        game.setBlackholeActive(false);
    }
}
