package wavegame;

import java.awt.*;
import java.util.ArrayList;

public class Handler extends ArrayList<Object> {

    public void clearEnnemys(){
        for (Object object : this){
            if (object instanceof Player player){
                this.clear();
                if (WaveGame.gameState != WaveGame.STATE.End)
                    this.add(new Player((int) player.getX(), (int) player.getY(), ID.Player, this));
                break;
            }
        }
    }

    public void tick(){
        Handler handlerCopy = new Handler();

        handlerCopy.addAll(this);
        for (Object object : handlerCopy)
            if (object instanceof IDrawable drawable)
                drawable.tick();
    }

    public void render(Graphics g){
        Handler handlerCopy = new Handler();

        handlerCopy.addAll(this);
        for (Object object : handlerCopy)
            if (object instanceof IDrawable drawable)
                drawable.render(g);
    }

    public void removeObject(GameObject object){
        this.remove(object);
    }
}
