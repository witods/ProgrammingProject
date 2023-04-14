package SpaceInvaders;

import java.util.Timer;
import java.util.TimerTask;

public class RapidFire extends PowerUp {
    private MainGame game;
    public RapidFire(int x, int y,MainGame m) {
        super(x,y);
        game = m;
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
                game.getPlayer().setRapidFireActive(true);
            }
        };
        t.schedule(timer,20000);
    }
}
