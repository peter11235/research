package drumset2;

import java.io.IOException;
import java.util.ArrayList;

import com.leapmotion.leap.Vector;

public class Main {

	public static void main(String[] args) {
		/*
		//Testing Drumkit
		DrumPlayer player = new DrumPlayer();
		player.playDrum(35);
		try {
			Thread.sleep(250);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		player.playDrum(48);
		try {
			Thread.sleep(250);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		player.playDrum(81);
		/*
		ArrayList<String> s = player.getInstrumentNames();
		
		for (int i = 0; i < s.size(); ++i)
		{
			System.out.println(i+"_"+s.get(i));
		}
		
		player.setInstrument("Drumkit: Standard Kit preset #0");
		for (int j = 35; j <= 81; ++j)
		{
			player.playDrum(j);
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		*/
		
		//Testing Controller
		DrumController drumController = new DrumController();
		drumController.go();
	}

}
