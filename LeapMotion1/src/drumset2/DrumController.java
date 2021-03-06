package drumset2;

import java.io.IOException;
import java.util.ArrayList;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Vector;

public class DrumController implements DrumObserver {

	private int numDrums = 1;
	private float distanceThreshold = 60f;
	private DrumLeapListener list;
	private Controller controller;
	private DrumGui gui;
	private DrumPlayer player;
	private Drum[] drums;
	private int c = 0;//index of drum being configured
	private boolean singleDrumMode = false;
	
	public DrumController()
	{
		player = new DrumPlayer();
		
		list = new DrumLeapListener();
		controller = new Controller();
		list.addObserver(this);
		
		gui = new DrumGui(this);
		numDrums = gui.getNumDrums();
		drums = new Drum[numDrums];
		gui.setVisible(true);
		if (numDrums == 1)
		{
			distanceThreshold = 300f;
		}
	}
	
	public void go(){
		controller.addListener(list);
		System.out.println("Press Enter to quit...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        controller.removeListener(list);
	}
	
	public void setPlayMode(boolean b)
	{
		list.playMode = b;
		if (b) gui.switchToPlayMode();
	}
	
	public void okPressed(int sound)
	{
		Vector l = list.currentLocation;
		addDrum(sound, l);
		if (c >= numDrums)
		{
			setPlayMode(true);
		}
		System.out.println("C: " + c);
	}
	
	public int getNumDrums()
	{
		return numDrums;
	}
	
	public void addDrum(int sound, Vector location)
	{
		if (c < numDrums)
		{
			drums[c] = new Drum(sound, location);
			++c;
			System.out.print("Added Drum " + sound);
			System.out.println(" at " + location.toString());
		}
		
	}
	

	/*
	 * Still having problems with drum.play();
	 * player.playDrum() plays but sounds bad. look into threading?
	 * 
	 * @see drumset2.DrumObserver#update(drumset2.DrumObservable, java.lang.Object)
	 */
	@Override
	public void update(DrumObservable d, Object arg) {
		Vector hitPosition = (Vector) arg;
		for (Drum drum: drums)
		{
			//System.out.println(""+drum.location.distanceTo(hitPosition));
			
			if (drum.location.distanceTo(hitPosition) < distanceThreshold)
			{
				//player.playDrum(drum.sound);
				drum.play();
				//System.out.println("should have played: " + drum.sound);
			}
		}
	}
	
}
