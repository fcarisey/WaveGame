package wavegame;

import java.awt.*;
import java.util.Random;

public class BossEnnemy extends GameObject{
    private Handler handler;
    private int timer = 80;
    private int timer2 = 30;

    private Random r = new Random();

    public BossEnnemy(int x, int y, ID id, Handler handler){
        super(x, y, id);
        this.handler = handler;
        this.setVelX(0);
        this.setVelY(2);
        this.r = new Random();
    }

    public Rectangle getBounds(){
        return new Rectangle((int) x, (int) y, 16, 16);
    }

    public void tick(){
        x += velX;
        y += velY;

        timer--;

        if (timer <= 0) velY = 0;
        else timer--;

        if (timer <= 0) timer2--;
        if (timer2 <= 0){
            if (velX == 0) velX = 2;
            if (velX > 0) velX += 0.005f;
            else if (velX < 0) velX -= 0.005f;

            velX = (int) WaveGame.clamp(velX, -10, 10);

            int spawn = r.nextInt(30);
            if (spawn == 0) handler.add(new BossEnnemyBullet((int) x + 48, (int) y + 48, ID.BasicEnnemy, handler));
        }

        if (x <= 0 || x >= WaveGame.WIDTH - 96) velX *= -1;
    }

    public void render(Graphics g){
        g.setColor(Color.black);
        g.fillRect((int) x, (int) y, 64, 64);
    }
}
