package pete;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ColorStrip extends JFrame {

	List<JPanel> segments;
	JPanel colorStrip;
	JPanel seg0;
	JPanel seg1;
	JPanel seg2;
	JPanel seg3;
	JPanel seg4;
	JPanel seg5;
	JPanel seg6;
	JPanel seg7;
	
	public ColorStrip() {
		init();
	}
	
	public void init() {
		segments = new ArrayList();
		colorStrip = new JPanel(new FlowLayout());
		colorStrip.setSize(800,100);
		
		segments.add(seg0);
		segments.add(seg1);
		segments.add(seg2);
		segments.add(seg3);
		segments.add(seg4);
		segments.add(seg5);
		segments.add(seg6);
		segments.add(seg7);
		
		for (int i = 0; i < 8; i++) {
			
			segments.get(i).setBackground(newRandomColor());
			segments.get(i).setSize(100,100);
			colorStrip.add(segments.get(i));
		}
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double screenWidth = screenSize.getWidth();
		double screenHeight = screenSize.getHeight();
		this.setContentPane(colorStrip);
		this.setSize(800,100);
		this.setLocation((int)(.5*screenWidth), (int)(.5 * screenHeight));
	}
	
	public Color newRandomColor() {
		return new Color((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256));
	}
	
	public void changeColor(int which) {
		segments.get(which).setBackground(newRandomColor());
	}
}
