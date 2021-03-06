package drumset2;

import java.util.ArrayList;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Listener;
import com.leapmotion.leap.Pointable;
import com.leapmotion.leap.PointableList;
import com.leapmotion.leap.Screen;
import com.leapmotion.leap.ScreenList;
import com.leapmotion.leap.Vector;

public class DrumLeapListener extends Listener implements DrumObservable {

	//private DrumController drumController;
	private ArrayList<DrumObserver> observers;
	private Pointable left;
	private Pointable right;
	private Pointable frontmost;
	boolean playMode = false;
	float threshold = 2f;
	public Vector currentLocation;
	
	private boolean hit = false;
	
	public DrumLeapListener()
	{
		observers = new ArrayList<DrumObserver>();
	}
	
	public void onConnect()
	{
		System.out.println("Connected...");
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
		left = frame.pointables().leftmost();
		right = frame.pointables().rightmost();
		frontmost = frame.pointables().frontmost();
		if (playMode)
		{
			//One thread for each Pointable in order to be able to play 2 drums at once.
			new Thread(new Runnable()
			{

				@Override
				public void run() {
					checkHit(left);
				}
				
			}).start();
			new Thread(new Runnable()
			{

				@Override
				public void run() {
					checkHit(right);
				}
				
			}).start();
			
			//checkHit(left);
			//checkHit(right);
		}
		else{
			currentLocation = frontmost.tipPosition();
		}
	}
	/*
	private boolean isHit(Frame frame)
	{
		PointableList pointables = frame.pointables();
		if (!pointables.isEmpty())
		{
			left = pointables.leftmost();
			right = pointables.rightmost();
			
			if ()
		}
	}
*/	
	public void checkHit(Pointable p)
	{
		Vector velocity = p.tipVelocity();
		if (velocity.magnitude() >= threshold)
		{
			setChanged();
			notifyObservers(p.tipPosition());
			//System.out.println("notified Controller of hit");
		}
	}
	
	
	public void setPlayMode(boolean b)
	{
		playMode = b;
	}
	
	public void onDisconnect(Controller arg)
	{
		System.out.println("Disconnected...");
	}

	@Override
	public void addObserver(DrumObserver d) {
		observers.add(d);
		
	}

	@Override
	public void deleteObserver(DrumObserver d) {
		observers.remove(d);
	}

	@Override
	public void notifyObservers(Object arg) {
		if (hit == true){
			for (DrumObserver d : observers)
			{
				d.update(this, arg);
			}
		}
		hit = false;
	}

	@Override
	public void setChanged() {
		hit = true;
		
	}
}
