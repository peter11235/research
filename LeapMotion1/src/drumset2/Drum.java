package drumset2;

import com.leapmotion.leap.Vector;

public class Drum {

	public int sound;// RANGE: [35,81]
	public Vector location;//float x,y,z
	private DrumPlayer player;
	
	public Drum(int aSound, Vector aLocation){
		sound = aSound;
		location = aLocation;
		player = new DrumPlayer();
	}
	
	 public void play()
	 {
	 	player.playDrum(sound);
	 }
	 
}
