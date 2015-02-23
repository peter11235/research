package soundgrid;

import java.awt.Point;
import java.util.ArrayList;

import javax.sound.midi.MidiChannel;

public class Drumset extends SoundBoard {

	private final short snare1 = 38;
	private final short snare2 = 40;
	private final short rimshot = 37;
	private final short bass1 = 36;
	private final short bass2 = 35;
	private final short lowTom2 = 41;
	private final short midTom2 = 45;
	private final short closedHH = 42;
	private final short openHH = 46;
	private final short crash = 49;
	private final short ride = 51;
	private final short cowbell = 56;

	private final short SHORT = 125;
	private final short MED = 300;
	private final short LONG = 400;

	/*
	 * public Point midTomPoint = new Point(0,1); public Point ridePoint = new
	 * Point(0,2); public Point closedHHPoint = new Point(1,0); public Point
	 * snarePoint = new Point(1,1); public Point lowTomPoint = new Point(1,2);
	 * public Point openHHPoint = new Point(2,0); public Point bassPoint = new
	 * Point(2,1); public Point tomPoint = new Point(2,2);
	 */
	private MidiChannel percussionChannel;

	public Drumset() {
		super();
		percussionChannel = channels[9];
	}

	public void snare(final int vol) {
		percussionChannel.noteOn(snare1, vol);
		try {
			Thread.sleep(SHORT);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			percussionChannel.noteOff(snare1);
		}

	}

	public void bass(final int vol) {

		percussionChannel.noteOn(bass1, vol);
		try {
			Thread.sleep(SHORT);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			percussionChannel.noteOff(bass1);
		}

	}

	public void lowTom(final int vol) {

		percussionChannel.noteOn(lowTom2, vol);
		try {
			Thread.sleep(MED);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			percussionChannel.noteOff(lowTom2);
		}

	}

	public void midTom(final int vol) {

		percussionChannel.noteOn(midTom2, vol);
		try {
			Thread.sleep(MED);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			percussionChannel.noteOff(midTom2);
		}

	}

	public void closedHH(final int vol) {

		percussionChannel.noteOn(closedHH, vol);
		try {
			Thread.sleep(SHORT);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			percussionChannel.noteOff(closedHH);
		}

	}

	public void openHH(final int vol) {

		percussionChannel.noteOn(openHH, vol);
		try {
			Thread.sleep(LONG);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			percussionChannel.noteOff(openHH);
		}

	}

	public void ride(final int vol) {

		percussionChannel.noteOn(ride, vol);
		try {
			Thread.sleep(MED);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			percussionChannel.noteOff(ride);
		}

	}

	public void crash(final int vol) {

		percussionChannel.noteOn(crash, vol);
		try {
			Thread.sleep(LONG);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			percussionChannel.noteOff(crash);
		}

	}

	public void cowbell(final int vol) {

		percussionChannel.noteOn(cowbell, vol);
		try {
			Thread.sleep(SHORT);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			percussionChannel.noteOff(cowbell);
		}

	}
}
