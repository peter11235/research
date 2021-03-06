package soundgrid;

import java.awt.Point;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Gesture;
import com.leapmotion.leap.GestureList;
import com.leapmotion.leap.Listener;
import com.leapmotion.leap.Pointable;
import com.leapmotion.leap.PointableList;
import com.leapmotion.leap.Vector;

public class DrumController extends Listener{
	
	private PointableList drumsticks;
	private final float HIT_VELOCITY = 100f;
	private SoundBoard drumset;
	
	public DrumController()
	{
		drumset = new Drumset();
	}
	
	public void onConnect()
	{
		System.out.println("Connected");
	}
	
	public void onFrame(Controller controller)
	{
		long lastFrameId = 0;
    	Frame frame = controller.frame();
    	if (frame.id() == lastFrameId) return;
    	processFrame3(frame);
    	lastFrameId = frame.id();
	}
	
	public void processFrame3(Frame frame)
	{
		drumsticks = frame.pointables();
		for (Pointable drumstick : drumsticks)
		{
			if (drumstick.tipPosition().getZ() < 40 && drumstick.tipVelocity().getZ() > 20f)
			{
				playSound(drumstick,50);
			}
			
		}
	}
	public void processFrame2(Frame frame)
	{
		GestureList gestures = frame.gestures();
		for (Gesture gesture : gestures)
		{
			if (gesture.type() == Gesture.Type.TYPE_SCREEN_TAP || gesture.type() == Gesture.Type.TYPE_KEY_TAP)
			{
				Pointable p = gesture.pointables().frontmost();
				float velocity = p.tipVelocity().getZ();
				playSound(p, velocity);
			}
		}
	}
	
	public void processFrame(Frame frame)
	{
		drumsticks = frame.pointables();
		
		for (Pointable drumstick : drumsticks)
		{
			if (drumstick.tipVelocity().getZ() >= HIT_VELOCITY)
			{
				playSound(drumstick, drumstick.tipVelocity().getZ());
			}
		}
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void playSound(Pointable p, float speed){
		Vector v = p.tipPosition();		
		
		int row = getRow(v);
		int col = getCol(v);
		
		if (row == 0){
			if (col == 0)
			{
				((Drumset)drumset).crash((int)speed);
			}
			else if (col == 1)
			{
				((Drumset)drumset).midTom((int)speed);
			}
			else
			{
				((Drumset)drumset).ride((int)speed);
			}
		}
		else if (row == 1)
		{
			if (col == 0)
			{
				((Drumset)drumset).closedHH((int)speed);
			}
			else if (col == 1)
			{
				((Drumset)drumset).snare((int)speed);
			}
			else
			{
				((Drumset)drumset).lowTom((int)speed);
			}
		}
		else
		{
			if (col == 0)
			{
				((Drumset)drumset).cowbell((int)speed);
			}
			else if (col == 1)
			{
				((Drumset)drumset).bass((int)speed);
			}
			else
			{
				((Drumset)drumset).bass((int)speed);
			}
		}
	
	
	}
	
	public int getRow(Vector v)
	{
		if (v.getX() <= 60)
		{
			if (v.getX() <= -60)
			{
				return 0;
			}
			else
			{
				return 1;
			}
		}
		else{
			return 2;
		}
	}
	public int getCol(Vector v)
	{
		if (v.getY() <= 233.3)
		{
			if (v.getX() <= 166.7)
			{
				return 0;
			}
			else
			{
				return 1;
			}
		}
		else{
			return 2;
		}
	}
	
	public void onDisconnect()
	{
		System.out.println("Connected");
	}
	
}
