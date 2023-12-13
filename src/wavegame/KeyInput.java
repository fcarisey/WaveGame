package wavegame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
    private Handler handler;

    private WaveGame game;
    private boolean[] keyDown = new boolean[4];

    public KeyInput(Handler handler, WaveGame game){
        this.handler = handler;
        this.game = game;
        keyDown[0] = false; // Z
        keyDown[1] = false; // S
        keyDown[2] = false; // Q
        keyDown[3] = false; // D
    }

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();

        Handler handlerCopy = new Handler();

        handlerCopy.addAll(this.handler);
        for (Object object : handlerCopy) {
            if (object instanceof Player player) {
                if (key == KeyEvent.VK_Z) {
                    player.setVelY(-5);
                    keyDown[0] = true;
                }
                if (key == KeyEvent.VK_S) {
                    player.setVelY(5);
                    keyDown[1] = true;
                }
                if (key == KeyEvent.VK_Q) {
                    player.setVelX(-5);
                    keyDown[2] = true;
                }
                if (key == KeyEvent.VK_D) {
                    player.setVelX(5);
                    keyDown[3] = true;
                }
            }
        }

        if (key == KeyEvent.VK_P){
            if (WaveGame.gameState == WaveGame.STATE.Game){
                WaveGame.paused = !WaveGame.paused;
            }
        }

        if (key == KeyEvent.VK_ESCAPE) System.exit(1);
    }

    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();

        Handler handlerCopy = new Handler();

        handlerCopy.addAll(this.handler);
        for (Object object : handlerCopy) {
            if (object instanceof Player player) {
                if (key == KeyEvent.VK_Z) keyDown[0] = false;
                if (key == KeyEvent.VK_S) keyDown[1] = false;
                if (key == KeyEvent.VK_Q) keyDown[2] = false;
                if (key == KeyEvent.VK_D) keyDown[3] = false;

                if (!keyDown[0] && !keyDown[1]) player.setVelY(0);
                if (!keyDown[2] && !keyDown[3]) player.setVelX(0);
            }
        }
    }
}
