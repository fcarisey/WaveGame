package wavegame;

import java.awt.*;
import java.util.function.Function;

public class Button implements IDrawable, IClickable {
    private final Handler handler;
    private float x, y;
    private int width, height;
    private Function<Object[], Void> onClick;
    private Object[] args;
    private final String text;
    private boolean enabled = true;

    public Button(String text, int x, int y, int width, int height, Handler handler, Function<Object[], Void> onClick, Object[] args) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.handler = handler;
        setOnClick(onClick, args);
    }

    public void tick(){
        if (enabled) {
            // TODO: Add tick logic
        }
    }

    public void render(java.awt.Graphics g){
        if (enabled) {
            Font fnt = new Font("arial", 1, 30);
            g.setFont(fnt);
            g.setColor(Color.WHITE);

            FontMetrics metrics = g.getFontMetrics(fnt);
            int x = (int) (this.x + (width - metrics.stringWidth(text)) / 2);
            int y = (int) (this.y + ((height - metrics.getHeight()) / 2) + metrics.getAscent());

            g.drawString(text, x, y);

            g.drawRect((int) this.x, (int) this.y, width, height);
        }
    }

    public void enable(){
        this.enabled = true;
    }

    public void disable(){
        this.enabled = false;
    }

    public boolean isEnabled(){
        return this.enabled;
    }

    public void setOnClick(Function<Object[], Void> onClick, Object[] args){
        Object[] newArgs = new Object[]{this};

        if (args != null){
            newArgs = new Object[args.length + 1];
            newArgs[0] = this;

            System.arraycopy(args, 0, newArgs, 1, args.length);
        }

        this.onClick = onClick;
        this.args = newArgs;
    }

    public void click(){
        this.onClick.apply(this.args);
    }

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

}
