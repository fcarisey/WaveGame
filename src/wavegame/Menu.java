package wavegame;

import java.awt.*;
import java.util.Random;

public class Menu {

    private Handler handler;
    private Random r = new Random();
    private WaveGame game;
    private HUD hud;

    private Button playButton;
    private Button normalButton;
    private Button hardButton;
    private Button HelpButton;
    private Button ExitButton;
    private Button BackButton;
    private Button TryAgainButton;

    public Menu(Handler handler, HUD hud, WaveGame game) {
        this.game = game;
        this.handler = handler;
        this.hud = hud;

        this.TryAgainButton = new Button("Try Again", 210, 350, 200, 64, handler, (Object[] args) -> {
            WaveGame.gameState = WaveGame.STATE.Menu;

            try{
                AudioPlayer.getSound("Clic").play(1.0f, 0.5f);
            }catch (Exception e){
                e.printStackTrace();
            }

            this.TryAgainButton.disable();
            this.BackButton.disable();

            return null;
        }, null);
        this.TryAgainButton.disable();

        this.normalButton = new Button("Normal", 210, 150, 200, 64, handler, (Object[] args) -> {
            WaveGame.gameState = WaveGame.STATE.Game;
            this.handler.add(new Player(WaveGame.WIDTH/2-32, WaveGame.HEIGHT/2-32, ID.Player, this.handler));
            this.handler.clearEnnemys();
            this.handler.add(new BasicEnnemy(r.nextInt(WaveGame.WIDTH), r.nextInt(WaveGame.HEIGHT), ID.BasicEnnemy, this.handler));
            this.game.diff = 0;

            try{
                AudioPlayer.getSound("Clic").play(1.0f, 0.5f);
            }catch (Exception e){
                e.printStackTrace();
            }

            this.normalButton.disable();
            this.hardButton.disable();
            this.BackButton.disable();

            return null;
        }, null);
        this.normalButton.disable();

        this.hardButton = new Button("Difficile", 210, 250, 200, 64, handler, (Object[] args) -> {
            WaveGame.gameState = WaveGame.STATE.Game;
            this.handler.add(new Player(WaveGame.WIDTH/2-32, WaveGame.HEIGHT/2-32, ID.Player, this.handler));
            this.handler.clearEnnemys();
            this.handler.add(new HardEnnemy(r.nextInt(WaveGame.WIDTH), r.nextInt(WaveGame.HEIGHT), ID.BasicEnnemy, this.handler));
            this.game.diff = 1;

            try{
                AudioPlayer.getSound("Clic").play(1.0f, 0.5f);
            }catch (Exception e){
                e.printStackTrace();
            }

            this.normalButton.disable();
            this.hardButton.disable();
            this.BackButton.disable();

            return null;
        }, null);
        this.hardButton.disable();

        this.playButton = new Button("Play", 210, 150, 200, 64, handler, (Object[] args) -> {
            WaveGame.gameState = WaveGame.STATE.Select;

            try{
                AudioPlayer.getSound("Clic").play(1.0f, 0.5f);
            }catch (Exception e){
                e.printStackTrace();
            }

            this.normalButton.enable();
            this.hardButton.enable();
            this.BackButton.enable();
            this.playButton.disable();
            this.HelpButton.disable();
            this.ExitButton.disable();

            return null;
        }, null);

        this.HelpButton = new Button("Help", 210, 250, 200, 64, handler, (Object[] args) -> {
            WaveGame.gameState = WaveGame.STATE.Help;

            try{
                AudioPlayer.getSound("Clic").play(1.0f, 0.5f);
            }catch (Exception e){
                e.printStackTrace();
            }

            this.playButton.disable();
            this.HelpButton.disable();
            this.ExitButton.disable();
            this.BackButton.enable();

            return null;
        }, null);

        this.ExitButton = new Button("Quit", 210, 350, 200, 64, handler, (Object[] args) -> {
            System.exit(1);
            return null;
        }, null);

        this.BackButton = new Button("Back", 210, 350, 200, 64, handler, (Object[] args) -> {
            WaveGame.gameState = WaveGame.STATE.Menu;

            try{
                AudioPlayer.getSound("Clic").play(1.0f, 0.5f);
            }catch (Exception e){
                e.printStackTrace();
            }

            this.playButton.enable();
            this.HelpButton.enable();
            this.ExitButton.enable();
            this.BackButton.disable();

            return null;
        }, null);
        this.BackButton.disable();

        this.handler.add(this.playButton);
        this.handler.add(this.HelpButton);
        this.handler.add(this.ExitButton);
        this.handler.add(this.normalButton);
        this.handler.add(this.hardButton);
        this.handler.add(this.BackButton);
        this.handler.add(this.TryAgainButton);

        for (int i = 0; i < 20; i++)
            this.handler.add(new MenuParticle(r.nextInt(WaveGame.WIDTH), r.nextInt(WaveGame.HEIGHT), ID.MenuParticle, handler));
    }

    public void end(){
        this.TryAgainButton.enable();
    }

    public void tick() {
        this.playButton.tick();
        this.HelpButton.tick();
        this.ExitButton.tick();
        this.normalButton.tick();
        this.hardButton.tick();
        this.BackButton.tick();
        this.TryAgainButton.tick();
    }

    public void render(Graphics g) {
        if (WaveGame.gameState == WaveGame.STATE.Menu){
            Font fnt = new Font("arial", 1, 50);
//            Font fnt2 = new Font("arial", 1, 30);
//
            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Menu", 240, 50);
//
//            g.setFont(fnt2);
//            g.drawRect(210, 150, 200, 64);
//            g.drawString("Play", 280, 190);
//
//            g.drawRect(210, 250, 200, 64);
//            g.drawString("Help", 280, 290);
//
//            g.drawRect(210, 350, 200, 64);
//            g.drawString("Quit", 280, 390);
        }else if (WaveGame.gameState == WaveGame.STATE.Help){
            Font fn = new Font("arial", 1, 50);
            g.setFont(fn);
            g.setColor(Color.white);
            g.drawString("Help", 240, 50);

            Font fn2 = new Font("arial", 1, 20);
            g.setFont(fn2);
            g.drawString("Utilisez ZQSD pour bouger et bloquer les ennemies", 50, 200);

//            Font fn3 = new Font("arial", 1, 30);
//            g.setFont(fn3);
//            g.drawRect(210, 350, 200, 64);
//            g.drawString("Back", 280, 390);
        }else if (WaveGame.gameState == WaveGame.STATE.End){
            Font fn = new Font("arial", 1, 50);
            g.setFont(fn);
            g.setColor(Color.white);
            g.drawString("Game Over", 180, 50);

            Font fn2 = new Font("arial", 1, 20);
            g.setFont(fn2);
            g.drawString("Vous avez perdu avec un score de " + hud.getScore(), 175, 200);

//            Font fn3 = new Font("arial", 1, 30);
//            g.setFont(fn3);
//            g.drawRect(210, 350, 200, 64);
//            g.drawString("Try Again", 240, 390);
        }
        else if (WaveGame.gameState == WaveGame.STATE.Select){
            Font fn = new Font("arial", 1, 50);
            g.setFont(fn);
            g.setColor(Color.white);
            g.drawString("Select difficulty", 80, 50);

            Font fn2 = new Font("arial", 1, 30);
            g.setFont(fn2);
//            g.drawRect(210, 150, 200, 64);
//            g.drawString("Normal", 240, 190);

//            g.drawRect(210, 250, 200, 64);
//            g.drawString("Hard", 280, 290);

//            g.drawRect(210, 350, 200, 64);
//            g.drawString("Back", 280, 390);
        }
    }
}
