package wavegame;

import java.util.Random;

public class Spawn {
    private Handler handler;
    private HUD hud;

    private WaveGame game;

    private int scoreKeep = 0;

    private Random r = new Random();

    public Spawn(Handler handler, HUD hud, WaveGame game){
        this.game = game;
        this.handler = handler;
        this.hud = hud;
    }

    public void setScoreKeep(int scoreKeep){
        this.scoreKeep = scoreKeep;
    }

    public void tick(){
        scoreKeep++;

        if (scoreKeep >= 500){
            scoreKeep = 0;
            hud.setLevel(hud.getLevel() + 1);

            if (game.diff == 0){
                switch (hud.getLevel()){
                    case 2:
                        handler.addObject(new BasicEnnemy(r.nextInt(WaveGame.WIDTH), r.nextInt(WaveGame.HEIGHT), ID.BasicEnnemy));
                        break;
                    case 3:
                        handler.addObject(new FastEnnemy(r.nextInt(WaveGame.WIDTH), r.nextInt(WaveGame.HEIGHT), ID.FastEnnemy));
                        break;
                    case 4:
                        handler.clearEnnemys();
                        handler.addObject(new SmartEnnemy(r.nextInt(WaveGame.WIDTH), r.nextInt(WaveGame.HEIGHT), ID.SmartEnnemy, handler));
                        break;
                    case 5:
                        handler.addObject(new BossEnnemy(WaveGame.WIDTH / 2 - 64, -60, ID.BossEnnemy, handler));
                        break;
                    case 6:
                        handler.addObject(new Heal(r.nextInt(WaveGame.WIDTH), r.nextInt(WaveGame.HEIGHT), ID.HEAL));
                        break;
                }
            }else if (game.diff == 1){
                switch (hud.getLevel()){
                    case 2:
                        handler.addObject(new HardEnnemy(r.nextInt(WaveGame.WIDTH), r.nextInt(WaveGame.HEIGHT), ID.HardEnnemy));
                        break;
                    case 3:
                        handler.addObject(new FastEnnemy(r.nextInt(WaveGame.WIDTH), r.nextInt(WaveGame.HEIGHT), ID.FastEnnemy));
                        break;
                    case 4:
                        handler.clearEnnemys();
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
}
