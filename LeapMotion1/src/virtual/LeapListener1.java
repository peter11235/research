package virtual;

import java.util.ArrayList;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.FingerList;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Listener;
import com.leapmotion.leap.Pointable;
import com.leapmotion.leap.Screen;
import com.leapmotion.leap.ScreenList;
import com.leapmotion.leap.Vector;

public class LeapListener1 extends Listener implements PObservable {

	
	private final static int zThreshold = 40;
	private boolean keyPressed = false;
	private Pointable pointer;
	private FingerList pointers;
	private ArrayList<PObserver> observers;
	private boolean changed = false;
	private Vector currentVector;
	
	public LeapListener1()
	{
		observers = new ArrayList<PObserver>();
		currentVector = new Vector();
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
		currentVector = pointer.direction();
	}
	
	public float getPitch()
	{
		return currentVector.pitch();
	}
	
	public float getYaw()
	{
		return currentVector.yaw();
	}
	
	public void onDisconnect(Controller arg)
	{
		
	}
	
	public void registerObserver(PObserver observer)
	{
		observers.add(observer);
	}
	
	public void setChanged()
	{
		changed = true;
	}
	

	@Override
	public void deleteObserver(PObserver o) {
		observers.remove(o);
		
	}

	@Override
	public void notifyObservers() {
		if (changed)
		{
			for (PObserver obs : observers)
			{
				obs.update(this);
			}
		}
		changed = false;
	}
	
}
