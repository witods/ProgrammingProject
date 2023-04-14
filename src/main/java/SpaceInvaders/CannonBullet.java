package SpaceInvaders;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class CannonBullet extends PowerUp{
    private MainGame game;
    public CannonBullet(int x, int y,MainGame m) {
        super(x,y);
        game = m;
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
                game.getPlayer().setRapidFireActive(true);
            }
        };
        t.schedule(timer,20000);
    }
}
