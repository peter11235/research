package drumset2;

public class DrumPlayer extends MidiPlayer {

	private final static int DRUM_DURATION = 150;
	private final int channel = 9;
	

	public DrumPlayer()
	{
		super();
	}
	

	public void playDrum(final int note) {
		/*
		new Thread(new Runnable() {
			@Override
			public void run() {
				if (note >= 35 && note <= 81) {

					if (lastNote != note) {
						channels[channel].noteOn(note, VELOCITY);
						lastNote = note;

						try {
							Thread.sleep(DRUM_DURATION);
						} catch (InterruptedException e) {

							e.printStackTrace();
						}
						soundOff(channel);
					}

			}
			}
		}).start();
		 */
		//System.out.println("Made it to playDrum()");
		if (note >= 35 && note <= 81) {
			
			if (lastNote != note) {
				channels[channel].noteOn(note, VELOCITY);
				System.out.println("played note: " + note);
				lastNote = note;

				try {
					Thread.sleep(DRUM_DURATION);
				} catch (InterruptedException e) {

					e.printStackTrace();
				}
				soundOff(channel);
			}
		}
		
	}

	public void soundOff(int channel) {

		channels[channel].noteOff(lastNote);
		channels[channel].allNotesOff();
		channels[channel].allSoundOff();
		lastNote = -1;
	}

}
