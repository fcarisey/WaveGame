package wavegame;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends GameObject{

    private Handler handler;
    private BufferedImage player_image;
    public Player(int x, int y, ID id, Handler handler){
        super(x, y, id);
        this.handler = handler;

        SpriteSheet ss = new SpriteSheet(WaveGame.sprite_sheet);
        player_image = ss.grabImage(1, 1, 32, 32);
    }

    public Rectangle getBounds(){
        return new Rectangle((int) x, (int) y, 32, 32);
    }

    public void tick(){
        x += velX;
        y += velY;

        x = (int) WaveGame.clamp(x, 0, WaveGame.WIDTH - 37);
        y = (int) WaveGame.clamp(y, 0, WaveGame.HEIGHT - 60);

        // WaveGame.handler.addObject(new Trail(x, y, ID.TRAIL, Color.blue, 32, 32, 0.15f, WaveGame.handler));

        collision();
    }

    public void collision(){
        Handler handlerCopy = new Handler();

        handlerCopy.addAll(this.handler);
        for (Object object : handlerCopy){

            if (object instanceof BasicEnnemy o){
                if (getBounds().intersects(o.getBounds())){
                    HUD.HEALTH -= 3;
                }
            }

            if (object instanceof FastEnnemy o){
                if (getBounds().intersects(o.getBounds())){
                    HUD.HEALTH -= 2;
                }
            }

            if (object instanceof HardEnnemy o){
                if (getBounds().intersects(o.getBounds())){
                    HUD.HEALTH -= 4;
                }
            }

            if (object instanceof SmartEnnemy o){
                if (getBounds().intersects(o.getBounds())){
                    HUD.HEALTH -= 1;
                }
            }

            if (object instanceof BossEnnemy o){
                if (getBounds().intersects(o.getBounds())){
                    HUD.HEALTH -= 8;
                }
            }

            if (object instanceof Heal o){
                if (getBounds().intersects(o.getBounds())){
                    HUD.HEALTH += 20;
                    this.handler.removeObject(o);
                }
            }
        }
    }

    public void render(Graphics g){
//        g.setColor(Color.blue);
//        g.fillRect((int) x, (int) y, 32, 32);

        g.drawImage(player_image, (int) x, (int) y, null);
    }
}
