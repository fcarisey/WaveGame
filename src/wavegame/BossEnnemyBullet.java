package wavegame;

import java.awt.*;
import java.util.Random;

public class BossEnnemyBullet extends GameObject {

    private Handler handler;
    Random r = new Random();

    public BossEnnemyBullet(int x, int y, ID id, Handler handler){
        super(x, y, id);

        this.handler = handler;

        velX = (r.nextInt(5 - -5) + -5);
        velY = 5;
    }

    public Rectangle getBounds(){
        return new Rectangle((int) x, (int) y, 16, 16);
    }

    public void tick(){
        x += velX;
        y += velY;

        if (y >= WaveGame.HEIGHT) handler.removeObject(this);

        handler.add(new Trail(x, y, ID.TRAIL, Color.blue, 16, 16, 0.20f, handler));
    }

    public void render(Graphics g){
        g.setColor(Color.CYAN);
        g.fillRect((int) x, (int) y, 16, 16);
    }
}
