package pete;

import java.io.IOException;

import javax.swing.JOptionPane;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Listener;
import com.leapmotion.leap.Pointable;
import com.leapmotion.leap.Screen;
import com.leapmotion.leap.ScreenList;
import com.leapmotion.leap.Vector;

public class Leap1 extends Listener {

	static LeapColor colorFrame;
	
	
	public Leap1() {
		
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
		float touchDistance = finger.touchDistance();//Distance from touch zone
		int quadrant = whichQuadrant(touchDistance, finger);
		colorFrame.changeColor(quadrant);
		//colorFrame.changeColor(whichSegment(finger));
	}
	
	public int whichQuadrant(float distance, Pointable point) {
		Vector direction = point.direction();
		float x = direction.getX();		//X component of vector
		float y = direction.getY();		//Y component of vector
		float z = direction.getZ();		//Z component of vector
		float pitch = direction.pitch();//positive when pointing above horizon
		float roll = direction.roll();	//positive when clockwise
		float yaw = direction.yaw();	//positive when pointing to right of horizon bisector
		Vector tipPosition = point.tipPosition();
		float tipX = tipPosition.getX(); //range = -160 to +160
		float tipY = tipPosition.getY(); //~100 to 300
		float tipZ = tipPosition.getZ();
		int quadrant = 0;
		
		/* This uses x and y of tip position vector */
		if (tipX < 0) {
			if (tipY < 200) {
				quadrant = 3;
			}
			else if (tipY > 200){
				quadrant = 1;
			}
		}
		else if (tipX > 0) {
			if (tipY < 200) {
				quadrant = 4;
			}
			else if (tipY > 200){
				quadrant = 2;
			}
		}
		/*This uses direction x and y
		if (x < 0) {
			if (y < 0) {
				quadrant = 3;
			}else if (y > 0){
				quadrant = 1;
			}
		}
		else if (x > 0){
			if (y < 0) {
				quadrant = 4;
			}else if (y > 0){
				quadrant = 2;
			}
		}
		*/
		/*This code uses pitch and yaw angles to calculate quadrant
		if (pitch > 0) {
			if (yaw < 0) {
				quadrant = 1;
			}
			else {
				quadrant = 2;
			}
		}
		else {
			if (yaw < 0) {
				quadrant = 3;
			}
			else {
				quadrant = 4;
			}
		}
		*/
		/*
		System.out.println("Distance: " + distance);
		System.out.println("Yaw: " + yaw);
		System.out.println("Pitch: " + pitch);
		System.out.println("Roll: " + roll);
		System.out.println("X: " + x);
		System.out.println("Y: " + y);
		System.out.println("Z: "+ z);
		*/
		System.out.println("Quad: "+quadrant);
		return quadrant;
	}
	
	public void onDisconnect(Controller controller) {
		
	}
	
	public static void main(String[] args) {

		Leap1 listener = new Leap1();
		Controller controller = new Controller();
		controller.addListener(listener);
		
		colorFrame = new LeapColor();
		colorFrame.setVisible(true);
		
		System.out.println("Press Enter to quit...");
	        try {
	            System.in.read();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		controller.removeListener(listener);
	}

}
