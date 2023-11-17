package wavegame;

import java.awt.*;

public class SmartEnnemy extends GameObject{

    private Handler handler;
    private GameObject player;
    public SmartEnnemy(int x, int y, ID id, Handler handler){
        super(x, y, id);

        this.handler = handler;

        for (int i = 0; i < this.handler.object.size(); i++)
            if (this.handler.object.get(i).getId() == ID.Player) player = this.handler.object.get(i);
    }

    public Rectangle getBounds(){
        return new Rectangle((int) x, (int) y, 16, 16);
    }

    public void tick(){
        x += velX;
        y += velY;

        if (y <= 0 || y >= WaveGame.HEIGHT - 32) velY *= -1;
        if (x <= 0 || x >= WaveGame.WIDTH - 16) velX *= -1;

        float diffX = x - player.getX() - 8;
        float diffY = y - player.getY() - 8;
        float distance = (float) Math.sqrt((x - player.getX()) * (x - player.getX()) + (y - player.getY()) * (y - player.getY()));

        this.setVelX((int) ((-1.0 / distance) * diffX * 3));
        this.setVelY((int) ((-1.0 / distance) * diffY * 3));

        if (y <= 0 || y >= WaveGame.HEIGHT - 32) velY *= -1;
        if (x <= 0 || x >= WaveGame.WIDTH - 16) velX *= -1;

        WaveGame.handler.addObject(new Trail(x, y, ID.TRAIL, Color.red, 16, 16, 0.1f, WaveGame.handler));
    }

    public void render(Graphics g){
        g.setColor(Color.red);
        g.fillRect((int) x, (int) y, 16, 16);
    }
}