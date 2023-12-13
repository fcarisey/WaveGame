package wavegame;

import java.awt.*;
import java.util.Random;

public class MenuParticle extends GameObject{

    private Handler handler;
    private Random r = new Random();
    private Color col;
    int dir = 0;
    public MenuParticle(int x, int y, ID id, Handler handler){
        super(x, y, id);
        this.handler = handler;
        setVelX(r.nextInt(10) - 5);
        setVelY(r.nextInt(10) - 5);

        if (velX == 0) velX = 1;
        if (velY == 0) velY = 1;

        col = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
    }

    public Rectangle getBounds(){
        return new Rectangle((int) x, (int) y, 1, 1);
    }

    public void tick(){
        x += velX;
        y += velY;

        if (y <= 0 || y >= WaveGame.HEIGHT - 32) velY *= -1;
        if (x <= 0 || x >= WaveGame.WIDTH - 16) velX *= -1;

        this.handler.add(new Trail(x, y, ID.TRAIL, col, 3, 3, 0.02f, this.handler));
    }

    public void render(Graphics g){
        g.setColor(col);
        g.fillRect((int) x, (int) y, 1, 1);
    }
}
