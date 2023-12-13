package wavegame;

import java.awt.*;

public interface IDrawable {
    public void tick();
    public void render(Graphics g);

    public float getX();

    public float getY();

    public int getWidth();

    public int getHeight();
}
