package SpaceInvaders;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class CannonBullet extends PowerUp{
    public CannonBullet(int x, int y,MainGame g,String s) {
        super(x,y,g,s);
        game = g;
    }

    public void activatePower(){
        game.getPlayer().setCannonActive(true);
        startPowerDuration();
    }
    public void startPowerDuration(){
        Timer t = new Timer();
        TimerTask timer = new TimerTask() {
            @Override
            public void run() {
                game.getPlayer().setCannonActive(false);
            }
        };
        t.schedule(timer,20000);
    }
}
