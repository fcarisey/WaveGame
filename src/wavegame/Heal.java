package wavegame;

import java.awt.*;

public class Heal extends GameObject{

    public Heal(int x, int y, ID id) {
        super(x, y, id);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        // Background
        g.setColor(new Color(40, 176, 2));
        g.fillRect((int) x, (int) y, 28, 28);

        // Border
        g.setColor(Color.white);
        g.drawRect((int) x, (int) y, 28, 28);

        // Cross
        g.setColor(Color.white);
        g.fillRect((int) x + 14 - 4, (int) y + 2, 8, 24);
        g.fillRect((int) x + 2, (int) y + 14 - 4, 24, 8);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 28, 28);
    }
}
