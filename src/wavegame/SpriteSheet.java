package wavegame;

import java.awt.image.BufferedImage;

public class SpriteSheet {
    private BufferedImage sprite;

    public SpriteSheet(BufferedImage ss) {
        this.sprite = ss;
    }

    public BufferedImage grabImage(int col, int row, int width, int height) {
        try {
            return sprite.getSubimage((col * 32) - 32, (row * 32) - 32, width, height);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
