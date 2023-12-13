package wavegame;

// Etape 15

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;

public class WaveGame extends Canvas implements Runnable{
    public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
    private Thread thread;
    private boolean running = false;

    public static boolean paused = false;

    public Handler handler;

    private HUD hud;

    private Spawn spawner;

    private Random r;

    private Menu menu;

    public int diff = 0;

    public static BufferedImage sprite_sheet;

    public enum STATE{
        Menu,
        Game,
        Help,
        Select,
        End
    };

    public static STATE gameState = STATE.Menu;

    public WaveGame(){
        hud = new HUD();
        handler = new Handler();
        spawner = new Spawn(handler, hud, this);
        menu = new Menu(handler, hud, this);

        new Window(WIDTH, HEIGHT, "Wave Game", this);
        this.requestFocus();

        r = new Random();

        AudioPlayer.Load();
        AudioPlayer.getMusic("Music").setVolume(0.5f);
        AudioPlayer.getMusic("Music").loop();

        this.addKeyListener(new KeyInput(handler, this));
        this.addMouseListener(new MouseInput(handler));

        if (gameState == STATE.Game){
            handler.add(new Player(WIDTH/2-32, HEIGHT/2-32, ID.Player, handler));
            handler.add(new BasicEnnemy(r.nextInt(WaveGame.WIDTH), r.nextInt(WaveGame.HEIGHT), ID.BasicEnnemy, this.handler));
        }

        BufferedImageLoader loader = new BufferedImageLoader();
        sprite_sheet = loader.loadImage("Meme.png");
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
                frames = 0;
            }
        }

        stop();
    }

    public void tick(){
        if (!paused)
            handler.tick();

        if (gameState == STATE.Game){
            if (!paused){
                hud.tick();
                spawner.tick();

                if (HUD.HEALTH <= 0){
                    HUD.HEALTH = 100;
                    gameState = STATE.End;
                    handler.clearEnnemys();
                    spawner.setScoreKeep(0);

                    for (int i = 0; i < 20; i++)
                        handler.add(new MenuParticle(r.nextInt(WaveGame.WIDTH), r.nextInt(WaveGame.HEIGHT), ID.MenuParticle, handler));

                    menu.end();
                }
            }
        } else if (gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.Select)
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

        try {
            handler.render(g);
        } catch (Exception e){
            e.printStackTrace();
        }

        if (paused){
            g.drawString("Paused", 240, 50);
        }

        if (gameState == STATE.Game){
            hud.render(g);
        } else if (gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End || gameState == STATE.Select)
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
