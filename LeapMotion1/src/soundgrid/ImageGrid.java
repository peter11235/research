package soundgrid;

import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ImageGrid extends JFrame {

	private JPanel mainContainer;

	public ImageGrid() {
		init();
	}

	public void init() {
		mainContainer = new JPanel(new GridLayout(3, 3));
		addImages();
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setContentPane(mainContainer);
		this.setSize(getMaximumSize());
		this.setVisible(true);
	}

	public JLabel imageToLabel(File file) {
		BufferedImage myPicture = null;
		try {
			myPicture = ImageIO.read(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new JLabel(new ImageIcon(myPicture));
		
	}

	public void addImages() {
		JLabel crash = imageToLabel(new File("Images/crash.png"));
		JLabel midTom = imageToLabel(new File("Images/midTom.png"));
		JLabel floorTom = imageToLabel(new File("Images/floor_tom.jpg"));
		JLabel snare = imageToLabel(new File("Images/snare.png"));
		JLabel ride = imageToLabel(new File("Images/ride.png"));
		JLabel bass = imageToLabel(new File("Images/bass_drum.png"));
		JLabel highhat = imageToLabel(new File("Images/highhat.png"));
		JLabel cowbell = imageToLabel(new File("Images/cowbell.jpg"));
		
		mainContainer.add(crash);
		mainContainer.add(midTom);
		mainContainer.add(ride);
		mainContainer.add(highhat);
		mainContainer.add(snare);
		mainContainer.add(floorTom);
		mainContainer.add(cowbell);
		mainContainer.add(bass);
		mainContainer.add(bass);
		
		
	}
}