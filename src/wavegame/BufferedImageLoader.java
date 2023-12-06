package wavegame;

import org.newdawn.slick.util.ResourceLoader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class BufferedImageLoader {
    BufferedImage image;

    public BufferedImage loadImage(String path){
        try{
            image = ImageIO.read(ResourceLoader.getResource(path));
        } catch (Exception e){
            e.printStackTrace();
        }

        return image;
    }
}
