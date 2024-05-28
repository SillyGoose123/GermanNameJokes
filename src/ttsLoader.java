import java.io.*;
import java.net.URL;

import jaco.mp3.player.MP3Player;

public class ttsLoader {


    public void load(String speech) {

        try {
            MP3Player mp3Player = new MP3Player(getClass().getResource("tts/" + speech + ".mp3").toURI().toURL());

            mp3Player.play();

        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
		


			
	

