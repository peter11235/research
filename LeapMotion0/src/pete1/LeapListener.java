package pete1;

import java.util.ArrayList;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.FingerList;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Listener;
import com.leapmotion.leap.Pointable;
import com.leapmotion.leap.Screen;
import com.leapmotion.leap.ScreenList;
import com.leapmotion.leap.Vector;

public class LeapListener extends Listener{

	
	private final static int zThreshold = 40;
	private boolean keyPressed = false;
	private LeapController controller;
	private Pointable pointer;
	private FingerList pointers;
	private LeapGui gui;
	private MidiPlayer player;
	
	public LeapListener()
	{
		
		player = new MidiPlayer();
		gui = new LeapGui(this);
	}
	
	public ArrayList<String> getInstrumentNames()
	{
		return player.getInstrumentNames();
	}
	
	public void setScale(int scale)
	{
		player.setScale(scale);
	}
	
	public void setInstrument(String name)
	{
		player.setInstrument(name);
	}
	public void onConnect()
	{
		System.out.println("Connected");
	}
	
	public void onFrame(Controller arg)
	{
		long lastFrameId = 0;
    	Frame frame = arg.frame();
    	if (frame.id() == lastFrameId) return;
    	processFrame(frame);
    	ScreenList screenList = arg.locatedScreens();
    	Screen screen;
    	if (!screenList.isEmpty()) screen = screenList.get(0);
    	lastFrameId = frame.id();
	}
	
	public void processFrame(Frame frame)
	{
		pointer = frame.fingers().frontmost(); 
		pointers = frame.fingers();
		
		for (int i = 0; i < pointers.count(); i++)
		{
			int which = whichSegment(pointers.get(i));
			player.playSound(which, i);
			gui.changeColor(which);
		}
		
		
	}
	
	public void onDisconnect(Controller arg)
	{
		player.close();
	}
	
	public int whichSegment(Pointable pointy) {
		
		Vector tipPosition = pointy.tipPosition();
		float tipX = tipPosition.getX();
		float tipY = tipPosition.getY();
		float tipZ = tipPosition.getZ();
		int segment= -1;
		
		//is key pressed?
		if (tipZ < zThreshold) 
		{
			keyPressed = true;
		}
		else if (tipZ >= zThreshold) {
			keyPressed = false;
		}
		
		//Each segment = 40 "milimeters" (by tipPos) if 8 segments
		if (tipX < -120) {
			segment = 0;
		}
		else if (tipX < -80) {
			segment =1;
		}
		else if (tipX < -40) {
			segment = 2;
		}
		else if (tipX < 0) {
			segment = 3;
		}
		else if (tipX < 40) {
			segment = 4;
		}
		else if (tipX < 80) {
			segment = 5;
		}
		else if (tipX < 120) {
			segment = 6;
		}
		else if (tipX <=160) {
			segment = 7; 
		}
		
		if (keyPressed)
		{
			return segment;
		}
		else{
			return -1;
		}
	}
}
