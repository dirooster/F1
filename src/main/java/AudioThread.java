/**
 * Created by Dimasik on 16.05.2018.
 */
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class AudioThread implements Runnable {
    @Override


            public void run()
            {
                try {
                    FileInputStream f = new FileInputStream("res/song.mp3");
                    try {
                    Player p = new Player(f);
                    p.play();
                } catch (JavaLayerException e) {
                        e.printStackTrace();
                    }
            } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
}
