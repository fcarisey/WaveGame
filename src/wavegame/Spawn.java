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
                        handler.add(new BasicEnnemy(r.nextInt(WaveGame.WIDTH), r.nextInt(WaveGame.HEIGHT), ID.BasicEnnemy, this.handler));
                        break;
                    case 3:
                        handler.add(new FastEnnemy(r.nextInt(WaveGame.WIDTH), r.nextInt(WaveGame.HEIGHT), ID.FastEnnemy, this.handler));
                        break;
                    case 4:
                        handler.clearEnnemys();
                        handler.add(new SmartEnnemy(r.nextInt(WaveGame.WIDTH), r.nextInt(WaveGame.HEIGHT), ID.SmartEnnemy, handler));
                        break;
                    case 5:
                        handler.add(new BossEnnemy(WaveGame.WIDTH / 2 - 64, -60, ID.BossEnnemy, handler));
                        break;
                    case 6:
                        handler.add(new Heal(r.nextInt(WaveGame.WIDTH), r.nextInt(WaveGame.HEIGHT), ID.Heal));
                        break;
                }
            }else if (game.diff == 1){
                switch (hud.getLevel()){
                    case 2:
                        handler.add(new HardEnnemy(r.nextInt(WaveGame.WIDTH), r.nextInt(WaveGame.HEIGHT), ID.HardEnnemy, handler));
                        break;
                    case 3:
                        handler.add(new FastEnnemy(r.nextInt(WaveGame.WIDTH), r.nextInt(WaveGame.HEIGHT), ID.FastEnnemy, this.handler));
                        break;
                    case 4:
                        handler.clearEnnemys();
                        handler.add(new SmartEnnemy(r.nextInt(WaveGame.WIDTH), r.nextInt(WaveGame.HEIGHT), ID.SmartEnnemy, handler));
                        break;
                    case 5:
                        handler.add(new BossEnnemy(WaveGame.WIDTH / 2 - 64, -60, ID.BossEnnemy, handler));
                        break;
                    case 6:
                        handler.add(new Heal(r.nextInt(WaveGame.WIDTH), r.nextInt(WaveGame.HEIGHT), ID.Heal));
                        break;
                }
            }


        }
    }
}
