package pete1;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class LeapGui extends JFrame {

	private LeapController controller;
	private LeapListener listener;
	private ColorStrip colorStrip;
	private JPanel mainPanel;
	private JMenuBar menuBar;
	private JMenu scaleMenu;
	private JMenu instrumentMenu;
	private JMenu octaveMenu;
	private JMenu majorMenu;
	private JMenu minorMenu;
	private JMenuItem oc1, oc2, oc3, oc4, oc5;
	private JMenuItem[] scaleMenuItems;
	private JScrollPane scroller;

	private final static String[] scaleNames = { "A Major", "A Minor",
			"B Major", "B Minor", "C Major", "C Minor", "D Major", "D Minor",
			"E Major", "E Minor", "F Major", "F Minor", "G Major", "G Minor",
			"A Flat Major", "A Flat Minor", "B Flat Major", "B Flat Minor",
			"D Flat Major", "D Flat Minor", "E Flat Major", "E Flat Minor",
			"G Flat Major", "G Flat Minor" };
	private ArrayList<String> instrumentNames;
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

	public LeapGui(LeapListener listener) {
		super();
		this.listener = listener;
		init();
	}

	private void init() {

		colorStrip = new ColorStrip();
		mainPanel = new JPanel(new BorderLayout());
		menuBar = new JMenuBar();
		scaleMenu = new JMenu("Scales");
		minorMenu = new JMenu("Minor Scales");
		majorMenu = new JMenu("Major Scales");
		instrumentMenu = new JMenu("Instruments");
		scaleMenuItems = new JMenuItem[24];
		scroller = new JScrollPane();

		for (int i = 0; i < 24; i++) {
			JMenuItem item = new JMenuItem(scaleNames[i]);
			item.addActionListener(new MenuListener("scale", scaleNames[i]));
			if (i % 2 == 0) {
				majorMenu.add(item);
			} else {
				minorMenu.add(item);
			}
		}
		scaleMenu.add(majorMenu);
		scaleMenu.add(minorMenu);
		menuBar.add(scaleMenu);
		getInstrumentList(listener.getInstrumentNames());
		menuBar.add(instrumentMenu);
		mainPanel.setLayout(new BorderLayout());
		menuBar.setVisible(true);
		mainPanel.add(colorStrip, BorderLayout.CENTER);
		mainPanel.add(menuBar, BorderLayout.NORTH);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(1600, 300);
		this.setContentPane(mainPanel);

		this.setVisible(true);
	}

	public void changeColor(int which) {
		colorStrip.changeColor(which);
	}

	public void getInstrumentList(ArrayList<String> instruments) {
		instrumentNames = instruments;
		for (String name : instruments) {
			JMenuItem item = new JMenuItem("" + name);
			item.addActionListener(new MenuListener("instrument", name));
			instrumentMenu.add(item);
		}
		instrumentMenu.add(scroller);
	}

	public class MenuListener implements ActionListener {

		String name;
		String type;

		public MenuListener(String aType, String id) {
			name = id;
			type = aType;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (type.equalsIgnoreCase("scale")) {
				for (int i = 0; i < 24; i++) {
					if (name.equals(scaleNames[i])) {
						listener.setScale(i);
					}
				}
			}
			else if (type.equalsIgnoreCase("instrument")) {
				listener.setInstrument(name);
			}
		}

	}

}
