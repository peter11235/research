package pete;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LeapColor extends JFrame {

	//stuff for dynamic array
	
	
	//Stuff for quadrants
	JPanel colorGrid;
	JPanel quad1;
	JPanel quad2;
	JPanel quad3;
	JPanel quad4;
	
	
	
	List<JPanel> quadrants;
	
	public LeapColor() {
		init();
	}
	public void init() {
		/*

		*/
		
		colorGrid = new JPanel(new GridLayout(2,2));
		
		quadrants = new ArrayList();
		quad1 = new JPanel();
		quad2 = new JPanel();
		quad3 = new JPanel();
		quad4 = new JPanel();
		
		quad1.add(new JLabel("1"));
		quad2.add(new JLabel("2"));
		quad3.add(new JLabel("3"));
		quad4.add(new JLabel("4"));
		quadrants.add(quad1);
		quadrants.add(quad2);
		quadrants.add(quad3);
		quadrants.add(quad4);
		for (JPanel panel : quadrants) {
			panel.setBackground(Color.RED);
		}
		
		
		colorGrid.add(quad1);
		colorGrid.add(quad2);
		colorGrid.add(quad3);
		colorGrid.add(quad4);
		
		this.setLocationRelativeTo(null);
		this.setSize(600, 500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setContentPane(colorGrid);
		//this.setContentPane(colorStrip);
	}
	
	public void changeColor(int quad) {
		
		switch(quad){
			case 1:
				quad1.setBackground(Color.GREEN);
				quad2.setBackground(Color.RED);
				quad3.setBackground(Color.RED);
				quad4.setBackground(Color.RED);
				break;
			
			case 2:
				quad2.setBackground(Color.GREEN);
				quad1.setBackground(Color.RED);
				quad3.setBackground(Color.RED);
				quad4.setBackground(Color.RED);
				break;
			
			case 3: 
				quad3.setBackground(Color.GREEN);
				quad2.setBackground(Color.RED);
				quad1.setBackground(Color.RED);
				quad4.setBackground(Color.RED);
				break;
				
			case 4:
				quad1.setBackground(Color.RED);
				quad2.setBackground(Color.RED);
				quad3.setBackground(Color.RED);
				quad4.setBackground(Color.GREEN);
				break;
		}
		
		
	}
}
