package wavegame;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.util.ResourceLoader;

import java.net.URL;
import java.util.Map;

public class AudioPlayer {
    public static Map<String, Sound> soundMap = new java.util.HashMap<>();
    public static Map<String, Music> musicMap = new java.util.HashMap<>();

    public static void Load(){


        try{
            URL ref = ResourceLoader.getResource("yeah.ogg");
            musicMap.put("Music", new Music(ref){
                {
                    this.setVolume(0.1f);
                }
            });

            ref = ResourceLoader.getResource("clic.ogg");
            soundMap.put("Clic", new Sound(ref));
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    public static Music getMusic(String key){
        return musicMap.get(key);
    }

    public static Sound getSound(String key){
        return soundMap.get(key);
    }
}
