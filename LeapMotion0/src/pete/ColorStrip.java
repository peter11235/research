package pete;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
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
	
	Dimension small = new Dimension(100,100);
	Dimension big = new Dimension(200,200);
	
	public ColorStrip() {
		init();
	}
	
	public void init() {
		segments = new ArrayList();
		colorStrip = new JPanel(new GridLayout(1,8));
		colorStrip.setSize(1600,200);
		seg0 = new JPanel();
		seg1 = new JPanel();
		seg2 = new JPanel();
		seg3 = new JPanel();
		seg4 = new JPanel();
		seg5 = new JPanel();
		seg6 = new JPanel();
		seg7 = new JPanel();
		/*
		seg0.setSize(small);
		seg1.setSize(small);
		seg2.setSize(small);
		seg3.setSize(small);
		seg4.setSize(small);
		seg5.setSize(small);
		seg6.setSize(small);
		seg7.setSize(small);
		*/
		seg0.setSize(big);
		seg1.setSize(big);
		seg2.setSize(big);
		seg3.setSize(big);
		seg4.setSize(big);
		seg5.setSize(big);
		seg6.setSize(big);
		seg7.setSize(big);
		
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
			//segments.get(i).setSize(200,200);
			colorStrip.add(segments.get(i));
		}
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double screenWidth = screenSize.getWidth();
		double screenHeight = screenSize.getHeight();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setContentPane(colorStrip);
		this.setSize(1600,200);
		this.setLocation((int)(.5*screenWidth), (int)(.5 * screenHeight));
	}
	
	public Color newRandomColor() {
		return new Color((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256));
	}
	
	public void changeColor(int which) {
		if (which == -1) {
			//do nothing
		}
		else {
			segments.get(which).setBackground(newRandomColor());
		}
	}
}
