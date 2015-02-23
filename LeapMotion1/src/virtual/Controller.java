package virtual;

import java.awt.Point;

public class Controller implements PObserver {

	VirtualScreen screen;
	public Controller(PObservable observable)
	{
		screen = new VirtualScreen();
		observable.registerObserver(this);
	}
	
	public Point whichSegment(float pitch, float yaw)
	{
		return screen.whichSegment(pitch, yaw);
	}
	@Override
	public void update(Object observable) {
		float pitch = ((LeapListener1)observable).getPitch();
		float yaw = ((LeapListener1)observable).getYaw();
		whichSegment(pitch, yaw);
	}

}
