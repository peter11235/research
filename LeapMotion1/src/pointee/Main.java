package pointee;

import java.io.IOException;

import com.leapmotion.leap.Controller;

public class Main {

	public static void main(String[] args) {
		LeapListener1 listener = new LeapListener1();
		Controller controller = new Controller();
		controller.addListener(listener);
		PointController pController = new PointController(listener);

		System.out.println("Press Enter to quit...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        controller.removeListener(listener);
	}

}
