package drumset2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class DrumGui extends JFrame {

	private JPanel mainPanel;
	private JPanel configurePanel;
	private JPanel exitPanel;
	//private JMenuBar menuBar;
	//private JMenu drumMenu;
	private JComboBox<String> dropDown;
	private JScrollPane scroller;
	private JButton okButton;
	private JButton exitButton;
	private JLabel menuLabel;
	
	private Dimension dimension;
	
	//private ArrayList<JMenuItem> drumMenuItems;
	private final String[] drumNames = {"35 Bass Drum 2",
			"36 Bass Drum 1",
			"37 Side Stick/Rimshot",
			"38 Snare Drum 1",
			"39 Hand Clap",
			"40 Snare Drum 2",
			"41 Low Tom 2",
			"42 Closed Hi-hat",
			"43 Low Tom 1",
			"44 Pedal Hi-hat",
			"45 Mid Tom 2",
			"46 Open Hi-hat",
			"47 Mid Tom 1",
			"48 High Tom 2",
			"49 Crash Cymbal 1",
			"50 High Tom 1",
			"51 Ride Cymbal 1",
			"52 Chinese Cymbal",
			"53 Ride Bell",
			"54 Tambourine",
			"55 Splash Cymbal",
			"56 Cowbell",
			"57 Crash Cymbal 2",
			"58 Vibra Slap",
			"59 Ride Cymbal 2",
			"60 High Bongo",
			"61 Low Bongo",
			"62 Mute High Conga",
			"63 Open High Conga",
			"64 Low Conga",
			"65 High Timbale",
			"66 Low Timbale",
			"67 High Agogô",
			"68 Low Agogô",
			"69 Cabasa",
			"70 Maracas",
			"71 Short Whistle",
			"72 Long Whistle",
			"73 Short Güiro",
			"74 Long Güiro",
			"75 Claves",
			"76 High Wood Block",
			"77 Low Wood Block",
			"78 Mute Cuíca",
			"79 Open Cuíca",
			"80 Mute Triangle",
			"81 Open Triangle"};
	
	private int numDrums;
	private int c = 1; // Drum to be configured. range: [1,numDrums]
	public int currentSound = 35;
	DrumController drumController;
	
	public DrumGui(DrumController aDrumController)
	{
		drumController = aDrumController;
		numDrums = drumController.getNumDrums();
		dimension = new Dimension(600,400);
		init();
	}
	
	public void switchToPlayMode()
	{
		
		mainPanel.remove(configurePanel);
		mainPanel.add(exitPanel, BorderLayout.CENTER);
		mainPanel.revalidate();	
		System.out.println("Switched to exit panel");
	}
	
	private void setUpComboBox()
	{
		/*
		menuBar = new JMenuBar();
		drumMenu = new JMenu();
		
		
		int totalDrums = drumNames.length;
		for (int i = 0; i < totalDrums; ++i) {
			JMenuItem item = new JMenuItem("" + drumNames[i]);
			item.addActionListener(new MenuListener(i));
			drumMenu.add(item);
			drumMenu.add(scroller);
		}
		menuBar.add(drumMenu);
		*/
		menuLabel = new JLabel("Please select sound for instrument 1. Then "+
		"place frontmost drumstick on desired location and click OK.");
		dropDown = new JComboBox<String>(drumNames);
		scroller = new JScrollPane();
		dropDown.add(scroller);
		dropDown.addActionListener(new DropDownListener());
	}
	
	public int getNumDrums()
	{
		int i;
		
		i = Integer.parseInt(JOptionPane.showInputDialog(null, 
				"Enter the number of drums up to 4.", "Number of Drums", 
				JOptionPane.PLAIN_MESSAGE).trim());
		
		if (i < 1|| i >4 )
			i = 1;
		
		return i;
	}
	
	private void updateLabel()
	{
		if (c < numDrums)
		{
			++c;
			menuLabel.setText("Please select sound for instrument " + c +
					". Then place frontmost drumstick on desired location and click OK.");
			mainPanel.repaint();
		}
		System.out.println("Updated " + (c-1) + "th Label");
	}
	
	private void setUpButtons()
	{
		okButton = new JButton("OK");
		okButton.addActionListener(new ButtonListener("Ok"));
		exitButton = new JButton("EXIT");
		exitButton.addActionListener(new ButtonListener("Exit"));
	}
	
	private void setUpPanels()
	{
		mainPanel = new JPanel(new BorderLayout());
		configurePanel = new JPanel(new BorderLayout());
		configurePanel.setSize(dimension);
		exitPanel = new JPanel(new BorderLayout());
		exitPanel.setSize(dimension);
		configurePanel.add(menuLabel, BorderLayout.NORTH);
		configurePanel.add(dropDown, BorderLayout.CENTER);
		configurePanel.add(okButton, BorderLayout.SOUTH);
		exitPanel.add(exitButton, BorderLayout.CENTER);
		mainPanel.add(configurePanel, BorderLayout.CENTER);
		
	}
	
	private void init()
	{
		setUpButtons();
		setUpComboBox();
		setUpPanels();
		
		this.setSize(dimension);
		this.setLocation(600, 400);
		this.setContentPane(mainPanel);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//this.setVisible(true);
	}
	
	protected class ButtonListener implements ActionListener
	{
		String name;
		
		public ButtonListener(String aName)
		{
			name = aName;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			if (name.equals("Ok"))
			{
				drumController.okPressed(currentSound);
				updateLabel();
				System.out.println("Pressed OK");
			}
			else
			{
				DrumGui.this.dispatchEvent(new WindowEvent(DrumGui.this, 
						WindowEvent.WINDOW_CLOSING));
				System.out.println("Pressed Exit");
			}
		}
		
	}
	/*
	protected class MenuListener implements ActionListener
	{
		int index;
		
		public MenuListener(int anIndex)
		{
			index = anIndex;
		}
	
		@Override
		public void actionPerformed(ActionEvent arg0) {
			currentSound = index;
		}
	}
	*/
	protected class DropDownListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			currentSound = ((JComboBox)arg0.getSource()).getSelectedIndex() + 35;
			//Sound value at index 0 is 35.
		}
	}
}
