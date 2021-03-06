package pete;

import java.io.IOException;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Listener;
import com.leapmotion.leap.Pointable;
import com.leapmotion.leap.Screen;
import com.leapmotion.leap.ScreenList;
import com.leapmotion.leap.Vector;

//sound imports
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiUnavailableException;

public class Leap2 extends Listener{

	private ColorStrip colorStrip;
	private Synthesizer synth;
	private boolean keyPressed = false;
	private final static int zThreshold = 20;
	
	int lastNote = -1;
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
		playSound(which);
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
		
		//is key pressed?
		if (tipZ < zThreshold) 
		{
			keyPressed = true;
		}
		else if (tipZ >= zThreshold) {
			keyPressed = false;
		}
		
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
	
	public void playSound(int which) {
		
		int note = 60;
		//This makes notes follow C-major scale
		switch (which){
			case 0:
				note=60;
				break;
			case 1:
				note=62;
				break;
			case 2:
				note=64;
				break;
			case 3:
				note=65;
				break;
			case 4:
				note = 67;
				break;
			case 5:
				note=69;
				break;
			case 6:
				note=71;
				break;
			case 7:
				note=72;
				break;
		}
		synth = null;
		int velocity = 75;
		int duration = 125;
		try {
			synth = MidiSystem.getSynthesizer();
			synth.open();
			MidiChannel[] channels = synth.getChannels();
			//System.out.println(""+channels.length);
			MidiChannel channel = channels[0];
			if (note != lastNote && keyPressed)
			{
				channels[0].noteOn(note, velocity);
			}
			else
			{
				channels[0].allSoundOff();
			}
			lastNote = note;
			Thread.sleep(duration);
			//channels[which].noteOff(note);
		}
		catch(MidiUnavailableException e) {
			System.out.println("Midi unavailable");
			e.printStackTrace();
		} catch (InterruptedException e) {
			System.out.println("Interrupted");
			e.printStackTrace();
		}
		finally {
			//synth.close();
			//leave this out to allow multiple notes to play
		}
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
