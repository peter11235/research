package pete;

import java.awt.Point;
import java.io.IOException;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Listener;
import com.leapmotion.leap.Pointable;
import com.leapmotion.leap.Screen;
import com.leapmotion.leap.ScreenList;
import com.leapmotion.leap.Vector;

public class Leap3 extends Listener {

	ColorGrid colors;
	Synthesizer synth;
	int lastNote = -1;
	public Leap3() {
		
		colors = new ColorGrid();
	}
	
	public void onConnect(Controller controller) {
		System.out.println("Connected");
	}
	
	public void onFrame(Controller controller) {
		long lastFrameId = 0;
    	Frame frame = controller.frame();
    	if (frame.id() == lastFrameId) return;
    	processFrame(frame);
    	lastFrameId = frame.id();
	
	}
	
	public void processFrame(Frame frame) {
		Pointable finger = frame.fingers().frontmost(); 
		Point cell = whichSegment(finger);
		playSound(cell.x, cell.y);
		colors.changeColor(cell.x, cell.y);
	}
	
	public void onDisconnect(Controller controller) {
		System.out.println("Disconnected");
	}
	public Point whichSegment(Pointable pointer) {
		Point which = new Point(-1,-1);
		Vector tipPosition = pointer.tipPosition();
		float tipX = tipPosition.getX();
		float tipY = tipPosition.getY();
		float tipZ = tipPosition.getZ();
		
		
		//Each cell is 40 "milimeters" (by tipPos) wide
		if (tipX < -120) {
			which.x = 0;
		}
		else if (tipX < -80) {
			which.x =1;
		}
		else if (tipX < -40) {
			which.x = 2;
		}
		else if (tipX < 0) {
			which.x= 3;
		}
		else if (tipX < 40) {
			which.x = 4;
		}
		else if (tipX < 80) {
			which.x = 5;
		}
		else if (tipX < 120) {
			which.x = 6;
		}
		else if (tipX <=160) {
			which.x = 7; 
		}
		
		//Each cell is 30 "milimeters" in height bc using range 80-320
		if (tipY < 110) {
			which.y = 3;
		}
		else if (tipY < 150) {
			which.y = 2;
		}
		else if (tipY < 180){
			which.y = 1;
		}
		else if (tipY < 210)
		{
			which.y = 0;
		}
		
		return which;
	}
	
	public void playSound(int horiz, int vert)
	{
	
		int note = 60;
		vert = vert+4;
		int startC = (12*vert);//0,0 -> 48
		
		switch(horiz) {
		
			case 0:
				note = startC;
				break;
			case 1:
				note = startC+2;
				break;
			case 2:
				note = startC+4;
				break;
			case 3:
				note = startC + 5;
				break;
			case 4:
				note = startC + 7;
				break;
			case 5:
				note = startC + 9;
				break;
			case 6:
				note = startC + 11;
				break;
			case 7:
				note = startC+ 12;
				break;
		}
		synth = null;
		int velocity = 75;
		int duration = 125;
		try {
			synth = MidiSystem.getSynthesizer();
			synth.open();
			MidiChannel[] channels = synth.getChannels();
			System.out.println(""+channels.length);
			MidiChannel channel = channels[0];
			if (note != lastNote)
			channel.noteOn(note, velocity);
			//channels[which].noteOn(note, velocity); this is how it used to be; don't know why
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
		Leap3 listener = new Leap3();
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
