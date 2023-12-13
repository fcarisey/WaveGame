package wavegame;

import java.awt.*;

public class SmartEnnemy extends GameObject{

    private Handler handler;
    private Player player;
    public SmartEnnemy(int x, int y, ID id, Handler handler){
        super(x, y, id);

        this.handler = handler;

        Handler handlerCopy = new Handler();

        handlerCopy.addAll(this.handler);
        for (Object o : handlerCopy) if (o instanceof Player player) this.player = player;
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

        this.handler.add(new Trail(x, y, ID.TRAIL, Color.red, 16, 16, 0.1f, this.handler));
    }

    public void render(Graphics g){
        g.setColor(Color.red);
        g.fillRect((int) x, (int) y, 16, 16);
    }
}
