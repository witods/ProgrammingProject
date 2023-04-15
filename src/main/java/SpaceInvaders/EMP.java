package SpaceInvaders;

import java.awt.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class EMP extends PowerUp {


    public EMP(int x, int y,MainGame g,String s) {
        super(x,y,g,s);
    }

    public void activatePower(){
        game.setEMPActive(true);
        game.getEnemyBullets().clear();
        startPowerDuration();
    }
    public void startPowerDuration(){
        Timer t = new Timer();
        TimerTask timer = new TimerTask() {
            @Override
            public void run() {
              game.setEMPActive(false);
            }
        };
        t.schedule(timer,20000);
    }
}
