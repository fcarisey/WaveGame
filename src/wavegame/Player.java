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
        for (int i = 0; i < WaveGame.handler.object.size(); i++){
            GameObject tempObject = WaveGame.handler.object.get(i);

            if (tempObject.getId() == ID.BasicEnnemy){
                if (getBounds().intersects(tempObject.getBounds())){
                    HUD.HEALTH -= 3;
                }
            }

            if (tempObject.getId() == ID.HardEnnemy){
                if (getBounds().intersects(tempObject.getBounds())){
                    HUD.HEALTH -= 4;
                }
            }

            if (tempObject.getId() == ID.FastEnnemy){
                if (getBounds().intersects(tempObject.getBounds())){
                    HUD.HEALTH -= 2;
                }
            }

            if (tempObject.getId() == ID.SmartEnnemy){
                if (getBounds().intersects(tempObject.getBounds())){
                    HUD.HEALTH -= 1;
                }
            }

            if (tempObject.getId() == ID.BossEnnemy){
                if (getBounds().intersects(tempObject.getBounds())){
                    HUD.HEALTH -= 8;
                }
            }

            if (tempObject.getId() == ID.HEAL){
                if (getBounds().intersects(tempObject.getBounds())){
                    HUD.HEALTH += 20;
                    WaveGame.handler.removeObject(tempObject);
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
