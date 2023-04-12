package SpaceInvaders;

import java.awt.*;

public class CannonBullet extends Projectile{
    public CannonBullet(int x, int y, int w, int h, Color c){
        super(x,y,w,h,c);
        this.setProjectileDamage(3);
    }
}
