

	
	import java.io.File;

	
	import jaco.mp3.player.MP3Player;
public class ttsLoader {
	    

		public void load(String speech) {

			        try {

			            File f = new File("sources/tts/" + speech + ".mp3");

			            MP3Player mp3Player = new MP3Player(f);
			            mp3Player.play();

			            while (!mp3Player.isStopped()) {
			                
			            }
			        } catch (Exception e) {
			            System.err.println(e.getMessage());
			        }
			    }
		}
		


			
	

