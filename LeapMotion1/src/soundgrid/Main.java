package soundgrid;

import java.io.IOException;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Gesture;

public class Main {

	public static void main(String[] args) {
		ImageGrid img = new ImageGrid();
		DrumController drumController = new DrumController();
		Controller controller = new Controller();
		controller.enableGesture(Gesture.Type.TYPE_SCREEN_TAP);
		controller.enableGesture(Gesture.Type.TYPE_KEY_TAP);
		controller.addListener(drumController);
		
		System.out.println("Press Enter to quit...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        controller.removeListener(drumController);

	}

}
