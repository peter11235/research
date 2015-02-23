package pete;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ColorGrid extends JFrame {

	JPanel[][] colors;
	JPanel mainGrid;
	Dimension small = new Dimension(125,100);
	Dimension big = new Dimension(200,200);
	
	public ColorGrid() {
		
		init();
	}
	
	private void init() {
		colors = new JPanel[4][8];
		mainGrid = new JPanel(new GridLayout(4,8));
		
		for (int i = 0; i < 4; i++ ) {
			for (int j = 0; j < 8; j++ ) {
				JPanel aPanel = new JPanel();
				aPanel.setSize(small);
				aPanel.setBackground(newRandomColor());
				colors[i][j] = aPanel;
				mainGrid.add(aPanel);
			}
		}
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setContentPane(mainGrid);
		this.setSize(1200,800);
		this.setVisible(true);
	}
	
	public Color newRandomColor() {
		return new Color((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256));
	}
	
	public void changeColor(int row, int col) {
		if (row < 0 || col < 0) {
			//do nothing
		}
		else {
			colors[row][col].setBackground(newRandomColor());
		}
	}
}
