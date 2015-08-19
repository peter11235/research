package drumset1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class DrumGui extends JFrame {

	private JPanel mainPanel;
	private JMenuBar menuBar;
	private JMenu instrumentMenu;
	private ArrayList<JMenuItem> instruments;
	private ArrayList<String> instrumentStrings;
	
	public DrumGui(String title, ArrayList<String> instrumentNames)
	{
		super();
		this.instrumentStrings = instrumentNames;
		init();
		
	}
	
	void createMenu()
	{
		menuBar = new JMenuBar();
		for (String name : instrumentStrings)
		{
			JMenuItem item = new JMenuItem(" "+ name);
			//Add action listener
			menuBar.add(item);
		}
	}
	
	private void init()
	{
		mainPanel = new JPanel();
		
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
			if (type.equalsIgnoreCase("instrument")) {
				
			}
		}

	}
	
}
