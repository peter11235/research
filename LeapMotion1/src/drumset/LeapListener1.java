package drumset;

import java.util.ArrayList;

import virtual.PObservable;
import virtual.PObserver;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.FingerList;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Listener;
import com.leapmotion.leap.Pointable;
import com.leapmotion.leap.PointableList;
import com.leapmotion.leap.Screen;
import com.leapmotion.leap.ScreenList;
import com.leapmotion.leap.Vector;

public class LeapListener1 extends Listener implements PObservable {

	
	private Pointable left;
	private Pointable right;
	private PointableList pointers;
	private ArrayList<PObserver> observers;
	private boolean changed = false;

	private static final float tipVelocityX = 100;
	
	public LeapListener1()
	{
		observers = new ArrayList<PObserver>();
		
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
    	
    	lastFrameId = frame.id();
	}
	
	
	
	public void processFrame(Frame frame)
	{
		pointers = frame.pointables();
		left = pointers.leftmost();
		right = pointers.rightmost();

		System.out.println("DATA:");
		
		System.out.println("Left Tip X: "+left.tipVelocity().getX());
		System.out.println("Right Tip X: "+right.tipVelocity().getX()+"\n");
		
		System.out.println("Left Tip Y: "+left.tipVelocity().getY());
		System.out.println("Right Tip Y: "+right.tipVelocity().getY()+"\n");
		
		System.out.println("Left Tip Z: "+left.tipVelocity().getZ());
		System.out.println("Right Tip Z: "+right.tipVelocity().getZ()+"\n");
		
		
		
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
