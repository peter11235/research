package pointee;

import java.util.ArrayList;

import virtual.PObserver;

public class PointController implements PObserver {

	private float thresholdPitch = (float)(Math.PI/12);
	private float thresholdYaw = (float)(Math.PI/12);
	
	private LeapListener1 listener;
	private MidiPlayer player;
	
	private ArrayList<Pointee> pointees;
	
	
	
	public PointController(LeapListener1 aListener)
	{
		listener = aListener;
		listener.registerObserver(this);
		pointees = new ArrayList<Pointee>();
		
		player = new MidiPlayer();
		
		setUpTestPointees();
		
		//Threshold should be smaller the more pointees are present
		thresholdPitch /= ((float)pointees.size());
		thresholdYaw /= ((float)pointees.size());
	}
	
	private void setUpTestPointees()
	{
		pointees.add(new Pointee(1f,.5f,2f,false, 60));
		pointees.add(new Pointee(-1f,2.5f,2f,false, 72));
		pointees.add(new Pointee(0f, .5f, 2.5f, false, 84));
	}

	@Override
	public void update(Object observable) {
		
		float pitch = ((LeapListener1)observable).getPitch();
		float yaw = ((LeapListener1)observable).getYaw();
		
		for (Pointee p : pointees)
		{
			if ( pitch>= p.targetPitch-thresholdPitch 
					&& pitch <= p.targetPitch + thresholdPitch
					&& yaw >= p.targetYaw -thresholdYaw 
					&& yaw <= p.targetYaw + thresholdYaw)
			{
				player.simplePlaySound(p.sound);
			}
		}
	}
	
	
}
