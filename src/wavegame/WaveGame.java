package wavegame;

// Etape 15

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class WaveGame extends Canvas implements Runnable{
    public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
    private Thread thread;
    private boolean running = false;
    public static Handler handler;

    private HUD hud;

    private Spawn spawner;

    private Random r;

    private Menu menu;

    public enum STATE{
        Menu,
        Game,
        Help,
        End
    };

    public static STATE gameState = STATE.Menu;

    public WaveGame(){
        new Window(WIDTH, HEIGHT, "Wave Game", this);
        this.requestFocus();

        hud = new HUD();
        r = new Random();

        handler = new Handler();
        spawner = new Spawn(handler, hud);
        menu = new Menu(handler);

        this.addKeyListener(new KeyInput(handler));
        this.addMouseListener(menu);

        if (gameState == STATE.Game){
            handler.addObject(new Player(WIDTH/2-32, HEIGHT/2-32, ID.Player));
            handler.addObject(new BasicEnnemy(r.nextInt(WaveGame.WIDTH), r.nextInt(WaveGame.HEIGHT), ID.BasicEnnemy));
        }
    }

    public static float clamp(float var, int min, int max){
        if (var >= max) return max;
        else if (var <= min) return min;
        else return var;
    }

    public synchronized void start(){
        // Create a new thread and start it
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public void run(){
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            while (delta >=1){
                tick();
                delta--;
            }

            if (running)
                render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
    }

    public void tick(){
        handler.tick();

        if (gameState == STATE.Game){
            this.removeMouseListener(menu);
            hud.tick();
            spawner.tick();
        } else if (gameState == STATE.Menu || gameState == STATE.Help)
            menu.tick();
    }

    public void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null){
            // Create 3 buffers
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.setColor(new Color(200, 200, 230));
        g.fillRect(0, 0, WIDTH, HEIGHT);
        handler.render(g);

        if (gameState == STATE.Game)
            hud.render(g);
        else if (gameState == STATE.Menu)
            menu.render(g);

        g.dispose();
        bs.show();
    }

    public synchronized void stop(){
        // This method is called when the thread is stopped
        // We will use it to stop the game
        try {
            thread.join();
            running = false;
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        new WaveGame();
    }
}
