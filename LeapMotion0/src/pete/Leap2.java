package pete;

import java.io.IOException;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Listener;
import com.leapmotion.leap.Pointable;
import com.leapmotion.leap.Screen;
import com.leapmotion.leap.ScreenList;
import com.leapmotion.leap.Vector;

public class Leap2 extends Listener{

	ColorStrip colorStrip;
	
	public Leap2() {
		colorStrip = new ColorStrip();
		colorStrip.setVisible(true);
	}
	public void onConnect(Controller controller) {
		System.out.println("Connected");
	}
	
	public void onFrame(Controller controller) {
		long lastFrameId = 0;
    	Frame frame = controller.frame();
    	if (frame.id() == lastFrameId) return;
    	processFrame(frame);
    	ScreenList screenList = controller.locatedScreens();
    	Screen screen;
    	if (!screenList.isEmpty()) screen = screenList.get(0);
    	lastFrameId = frame.id();
	
	}
	
	public void processFrame(Frame frame) {
		Pointable finger = frame.fingers().frontmost(); 
		int which = whichSegment(finger);
		colorStrip.changeColor(which);
	}
	
	public void onDisconnect(Controller controller) {
		System.out.println("Disconnected");
	}
	public int whichSegment(Pointable pointer) {
		Vector tipPosition = pointer.tipPosition();
		float tipX = tipPosition.getX();
		float tipY = tipPosition.getY();
		float tipZ = tipPosition.getZ();
		int segment= -1;
		
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
		
		return segment;
	}
	
	public static void main(String[] args) {
		Leap2 listener = new Leap2();
		Controller controller = new Controller();
		controller.addListener(listener);

		System.out.println("Press Enter to quit...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        controller.removeListener(listener);
	}

}