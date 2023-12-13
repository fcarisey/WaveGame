package wavegame;

import java.awt.*;

public class BasicEnnemy extends GameObject{
    private Handler handler;
    public BasicEnnemy(int x, int y, ID id, Handler handler){
        super(x, y, id);
        this.handler = handler;
        setVelX(5);
        setVelY(5);
    }

    public Rectangle getBounds(){
        return new Rectangle((int) x, (int) y, 16, 16);
    }

    public void tick(){
        x += velX;
        y += velY;

        if (y <= 0 || y >= WaveGame.HEIGHT - 32) velY *= -1;
        if (x <= 0 || x >= WaveGame.WIDTH - 16) velX *= -1;

        this.handler.add(new Trail(x, y, ID.TRAIL, Color.red, 16, 16, 0.1f, this.handler));
    }

    public void render(Graphics g){
        g.setColor(Color.red);
        g.fillRect((int) x, (int) y, 16, 16);
    }
}
