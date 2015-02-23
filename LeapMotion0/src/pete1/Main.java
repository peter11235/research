package pete1;

import java.io.IOException;

import com.leapmotion.leap.Controller;

public class Main {

	public static void main(String[] args) {
		LeapListener listener = new LeapListener();
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
