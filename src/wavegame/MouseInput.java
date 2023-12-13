package wavegame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {
    private Handler handler;

    public MouseInput(Handler handler){
        this.handler = handler;
    }

    public boolean mouseOver(int mx, int my, int x, int y, int width, int height){
        if (mx > x && mx < x + width){
            return my > y && my < y + height;
        }else{
            return false;
        }
    }

    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        Handler handlerCopy = new Handler();

        handlerCopy.addAll(this.handler);
        for (Object object : handlerCopy) {
            if (object instanceof IClickable o) {
                if (mouseOver(mx, my, (int) o.getX(), (int) o.getY(), o.getWidth(), o.getHeight())) {
                    if (o.isEnabled())
                        o.click();
                }
            }
        }
    }
}
