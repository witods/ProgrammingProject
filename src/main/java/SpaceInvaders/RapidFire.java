package SpaceInvaders;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class RapidFire extends PowerUp {

    public RapidFire(int x, int y,MainGame g,String s) {
        super(x,y,g,s);
    }

    public void activatePower(){
        game.getPlayer().setRapidFireActive(true);
        startPowerDuration();
    }
    public void startPowerDuration(){
        Timer t = new Timer();
        TimerTask timer = new TimerTask() {
            @Override
            public void run() {
                game.getPlayer().setRapidFireActive(false);
            }
        };
        t.schedule(timer,20000);
    }
}
