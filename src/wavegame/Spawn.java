package wavegame;

import java.util.Random;

public class Spawn {
    private Handler handler;
    private HUD hud;

    private int scoreKeep = 0;

    private Random r = new Random();

    public Spawn(Handler handler, HUD hud){
        this.handler = handler;
        this.hud = hud;
    }

    public void tick(){
        scoreKeep++;

        if (scoreKeep >= 500){
            scoreKeep = 0;
            hud.setLevel(hud.getLevel() + 1);

            switch (hud.getLevel()){
                case 2:
                    handler.addObject(new BasicEnnemy(r.nextInt(WaveGame.WIDTH), r.nextInt(WaveGame.HEIGHT), ID.BasicEnnemy));
                    break;
                case 3:
                    handler.addObject(new FastEnnemy(r.nextInt(WaveGame.WIDTH), r.nextInt(WaveGame.HEIGHT), ID.FastEnnemy));
                    break;
                case 4:
                    handler.clearEnnemys();
                    WaveGame.handler.object.remove(1);
                    handler.addObject(new SmartEnnemy(r.nextInt(WaveGame.WIDTH), r.nextInt(WaveGame.HEIGHT), ID.SmartEnnemy, handler));
                    break;
                case 5:
                    handler.addObject(new BossEnnemy(WaveGame.WIDTH / 2 - 64, -60, ID.BossEnnemy, handler));
                    break;
                case 6:
                    handler.addObject(new Heal(r.nextInt(WaveGame.WIDTH), r.nextInt(WaveGame.HEIGHT), ID.HEAL));
                    break;
            }
        }
    }
}
