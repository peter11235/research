package pete;

import java.awt.Robot;
import java.io.IOException;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Gesture;
import com.leapmotion.leap.Listener;
import com.leapmotion.leap.Screen;
import com.leapmotion.leap.ScreenList;

public class LeapScroll extends Listener {

	Robot robot;
	public LeapScroll () {
		//robot= new Robot();
	}
	 
	public void onConnect (Controller controller) {
		System.out.println("Connected");
        controller.enableGesture(Gesture.Type.TYPE_SWIPE);
        controller.enableGesture(Gesture.Type.TYPE_CIRCLE);
        controller.enableGesture(Gesture.Type.TYPE_SCREEN_TAP);
        controller.enableGesture(Gesture.Type.TYPE_KEY_TAP);
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
		
	}
	public void onDisconnect(Controller controller) {
		
	}
	public static void main(String[] args) {
		LeapScroll listener = new LeapScroll();
		Controller controller = new Controller();
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
