package pete;

import java.io.IOException;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Gesture;
import com.leapmotion.leap.Listener;
import com.leapmotion.leap.Pointable;
import com.leapmotion.leap.Vector;

public class Leap0 extends Listener {

	public void onInit(Controller controller) {
        System.out.println("Initialized");
    }

    public void onConnect(Controller controller) {
        System.out.println("Connected");
        controller.enableGesture(Gesture.Type.TYPE_SWIPE);
        controller.enableGesture(Gesture.Type.TYPE_CIRCLE);
        controller.enableGesture(Gesture.Type.TYPE_SCREEN_TAP);
        controller.enableGesture(Gesture.Type.TYPE_KEY_TAP);
    }

    public void onDisconnect(Controller controller) {
        //Note: not dispatched when running in a debugger.
        System.out.println("Disconnected");
    }

    public void onExit(Controller controller) {
        System.out.println("Exited");
    }
    public void onFrame(Controller controller) {
    	long lastFrameId = 0;
    	Frame frame = controller.frame();
    	if (frame.id() == lastFrameId) return;
    	processFrame(frame);
    	lastFrameId = frame.id();
    }
    public void processFrame(Frame frame) {
    	System.out.println("Frame id: " + frame.id()
                + ", timestamp: " + frame.timestamp()
                + ", hands: " + frame.hands().count()
                + ", fingers: " + frame.fingers().count()
                + ", tools: " + frame.tools().count()
                + ", gestures " + frame.gestures().count());
    	
    	Pointable point = frame.pointables().frontmost();
    	Vector direction = point.direction();
    	float length = point.length();
    	float width = point.width();
    	Vector stablizedPos = point.stabilizedTipPosition();
    	Vector tipPos = point.tipPosition();
    	Vector speed = point.tipVelocity();
    	float touchDistance = point.touchDistance();
    	Pointable.Zone zone = point.touchZone();
    	System.out.println("direction: " + direction.toString());
    	System.out.println("length: " + length);
    	System.out.println("width: " + width);
    	System.out.println("stablized position: " + stablizedPos.toString());
    	System.out.println("tip position: " + tipPos.toString());
    	System.out.println("speed: " + speed.toString());
    	System.out.println("touchDistance: " + touchDistance);
    	
    }
	
	public static void main(String[] args) {
		
		Leap0 listener = new Leap0();
		Controller controller = new Controller();
		
        // Have the sample listener receive events from the controller
        controller.addListener(listener);

        // Keep this process running until Enter is pressed
        System.out.println("Press Enter to quit...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Remove the sample listener when done
        controller.removeListener(listener);

	}

}
