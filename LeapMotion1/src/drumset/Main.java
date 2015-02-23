package drumset;

import java.io.IOException;

import pointee.LeapListener1;
import pointee.PointController;

import com.leapmotion.leap.Controller;

public class Main {

	public static void main(String[] args) {
		
		LeapListener1 listener = new LeapListener1();
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
