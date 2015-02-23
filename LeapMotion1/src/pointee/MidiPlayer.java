package pointee;

import java.util.ArrayList;

import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Soundbank;
import javax.sound.midi.Synthesizer;

public class MidiPlayer {

	private int[][] scales = { { 8, 10, 12, 13, 15, 17, 19 }, // A = 0
			{ 9, 11, 12, 14, 16, 18, 19 }, // A_MINOR=1

			{ 11, 13, 15, 16, 18, 20, 22 }, // B=2
			{ 11, 13, 14, 16, 18, 20, 21 }, // B_MINOR=3

			{ 0, 2, 4, 5, 7, 9, 11 }, // C=4
			{ 0, 2, 3, 5, 7, 9, 10 }, // C_MINOR=5

			{ 2, 4, 6, 7, 9, 11, 13 }, // D=6
			{ 2, 4, 5, 7, 9, 11, 12 }, // D minor=7

			{ 4, 6, 8, 9, 11, 13, 15 }, // E=8
			{ 4, 6, 7, 9, 11, 13, 14 }, // E minor=9

			{ 5, 7, 9, 10, 12, 14, 16 }, // F=10
			{ 5, 7, 8, 10, 12, 14, 15 }, // Fminor=11

			{ 7, 9, 11, 12, 14, 16, 18 }, // G=12
			{ 7, 9, 10, 12, 14, 16, 17 }, // Gminor=13

			{ 8, 10, 12, 13, 15, 17, 19 },// Aflat=14
			{ 8, 10, 11, 13, 15, 17, 18 }, // AFlat minor=15

			{ 10, 12, 14, 15, 17, 19, 21 }, // bflat=16
			{ 10, 12, 13, 15, 17, 19, 20 }, // bflat minor=17

			{ 1, 3, 5, 6, 8, 10, 12 }, // dflat=18
			{ 1, 3, 4, 6, 8, 10, 11 }, // dflat minor=19

			{ 3, 5, 7, 8, 10, 12, 14 }, // eflat=20
			{ 3, 5, 6, 8, 10, 12, 13 }, // eflat minor21

			{ 6, 8, 10, 11, 13, 15, 17 }, // gflat22
			{ 6, 8, 9, 11, 13, 15, 16 } }; // gflat minor23

	private final static int A = 0;
	private final static int A_MINOR = 1;
	private final static int B = 2;
	private final static int B_MINOR = 3;
	private final static int C = 4;
	private final static int C_MINOR = 5;
	private final static int D = 6;
	private final static int D_MINOR = 7;
	private final static int E = 8;
	private final static int E_MINOR = 9;
	private final static int F = 10;
	private final static int F_MINOR = 11;
	private final static int G = 12;
	private final static int G_MINOR = 13;
	private final static int A_FLAT = 14;
	private final static int A_FLAT_MINOR = 15;
	private final static int B_FLAT = 16;
	private final static int B_FLAT_MINOR = 17;
	private final static int D_FLAT = 18;
	private final static int D_FLAT_MINOR = 19;
	private final static int E_FLAT = 20;
	private final static int E_FLAT_MINOR = 21;
	private final static int G_FLAT = 22;
	private final static int G_FLAT_MINOR = 23;

	private int[] currentScale = null;
	private int currentOctave = 5;

	private Synthesizer synth;
	private Instrument[] instruments;
	private Instrument currentInstrument;
	private MidiChannel[] channels;
	private ArrayList<String> instrumentNames;

	private int lastNote = -1;
	private long time = 0;

	private final static int VELOCITY = 75;
	private final static int DURATION = 125;

	public MidiPlayer() {
		currentScale = scales[C];
		instrumentNames = new ArrayList<String>();
		setUpSound();
	}

	public void setUpSound() {

		synth = null;
		try {
			synth = MidiSystem.getSynthesizer();
			synth.open();
			channels = synth.getChannels();
			/*
			 * for (MidiChannel channel : channels) { channel.setSolo(true); }
			 */
			Soundbank soundbank = synth.getDefaultSoundbank();
			synth.loadAllInstruments(soundbank);
			instruments = synth.getAvailableInstruments();
			for (Instrument i : instruments) {
				instrumentNames.add(i.toString().trim());
			}

		} catch (MidiUnavailableException e) {
			System.out.print("NO MIDI");
			e.printStackTrace();
		}
	}

	public ArrayList<String> getInstrumentNames() {
		return instrumentNames;
	}

	public void setScale(int scale) {
		currentScale = scales[scale];
	}

	public void setInstrument(String name) {
		for (Instrument i : instruments) {
			if (i.toString().trim().equals(name.trim())) {
				for (int j = 0; j < channels.length; j++) {
					channels[j].programChange(i.getPatch().getProgram());
				}
			}
		}
	}

	public void playSound(final int note, final int channel) {

		
		new Thread(new Runnable() {
			@Override
			public void run() {
				if (note >= 0) {
					int octave = currentOctave;
					int midi;
					octave *= 12;
					if (note == 7) {
						midi = currentScale[0] + octave + 12;
					} else {
						midi = currentScale[note] + octave;
					}

					if (lastNote != midi) {
						channels[channel].noteOn(midi, VELOCITY);
						lastNote = midi;
						try {
							Thread.sleep(DURATION);
						} catch (InterruptedException e) {

							e.printStackTrace();
						}

					}
				} else {
					soundOff(channel);
				}

			}

		}).start();

	}

	public void simplePlaySound(final int sound) {
		
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				if (sound != lastNote || System.currentTimeMillis() > time + 500) {
					channels[0].noteOn(sound, VELOCITY);
				}
				lastNote = sound;
			}

		}).start();
		try {
			Thread.sleep(300);
			time = System.currentTimeMillis();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			channels[0].allSoundOff();
			channels[0].allNotesOff();
		}
	}

	public void soundOff(int channel) {

		channels[channel].noteOff(lastNote);
		channels[channel].allNotesOff();
		channels[channel].allSoundOff();

	}

	public void close() {
		synth.close();
	}

}
