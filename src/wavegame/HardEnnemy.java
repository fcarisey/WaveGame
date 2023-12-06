package wavegame;

import java.awt.*;

public class HardEnnemy extends GameObject{
    public HardEnnemy(int x, int y, ID id){
        super(x, y, id);
        setVelX(8);
        setVelY(8);
    }

    public Rectangle getBounds(){
        return new Rectangle((int) x, (int) y, 16, 16);
    }

    public void tick(){
        x += velX;
        y += velY;

        if (y <= 0 || y >= WaveGame.HEIGHT - 32) velY *= -1;
        if (x <= 0 || x >= WaveGame.WIDTH - 16) velX *= -1;

        WaveGame.handler.addObject(new Trail(x, y, ID.TRAIL, Color.red, 16, 16, 0.1f, WaveGame.handler));
    }

    public void render(Graphics g){
        g.setColor(Color.red);
        g.fillRect((int) x, (int) y, 16, 16);
    }
}
