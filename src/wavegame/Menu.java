package wavegame;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Menu extends MouseAdapter {

    private Handler handler;
    private Random r = new Random();

    public Menu(Handler handler) {
        this.handler = handler;
    }

    public void tick() {

    }

    public void render(Graphics g) {
        if (WaveGame.gameState == WaveGame.STATE.Menu){
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);

            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Menu", 240, 50);

            g.setFont(fnt2);
            g.drawRect(210, 150, 200, 64);
            g.drawString("Play", 280, 190);

            g.drawRect(210, 250, 200, 64);
            g.drawString("Help", 280, 290);

            g.drawRect(210, 350, 200, 64);
            g.drawString("Quit", 280, 390);
        }else if (WaveGame.gameState == WaveGame.STATE.Help){
            Font fn = new Font("arial", 1, 50);
            g.setFont(fn);
            g.setColor(Color.white);
            g.drawString("Help", 240, 50);
        }

        System.out.println("WaveGame.gameState = " + WaveGame.gameState);
    }

    private Boolean mouseOver(int mx, int my, int x, int y, int width, int height){
        return mx > x && mx < x + width && my > y && my < y + height;
    }

    public void mousePressed(MouseEvent e){
        int mx = e.getX();
        int my = e.getY();

        // Play button
        if (mouseOver(mx, my, 210, 150, 200, 64)){
            WaveGame.gameState = WaveGame.STATE.Game;
            handler.addObject(new Player(WaveGame.WIDTH / 2 - 32, WaveGame.HEIGHT / 2 - 32, ID.Player));
            handler.clearEnnemys();
            handler.addObject(new BasicEnnemy(r.nextInt(WaveGame.WIDTH - 50), r.nextInt(WaveGame.HEIGHT - 50), ID.BasicEnnemy));
        }

        // Help button
        if (mouseOver(mx, my, 210, 250, 200, 64)){
            WaveGame.gameState = WaveGame.STATE.Help;
        }

        // Exit button
        if (mouseOver(mx, my, 210, 350, 200, 64)){
            System.exit(1);
        }
    }


}
