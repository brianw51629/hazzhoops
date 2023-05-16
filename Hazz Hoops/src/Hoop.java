
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Hoop {
	private int x, y;
	private Image img;
	private AffineTransform tx;
 
	public Hoop() {
		img = getImage("/imgs/HoopLeft.png"); // load the image for Background

		tx = AffineTransform.getTranslateInstance(x, y);
		 // initialize the location of the image
					// use your variables

		y=375;

	}
 
	public Hoop(String fileName) {
		img = getImage("/imgs/" + fileName); // load the image for Tree

		tx = AffineTransform.getTranslateInstance(x, y);
		y=375;
		x=1200;
		// initialize the location of the image
					// use your variables
	}

	public void changePicture(String newFileName) {
		img = getImage("/imgs/"+newFileName);
		if(newFileName=="afternoonBackground")
		init(x+50, y);
	}

	public void paint(Graphics g) {
		// these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		
		g2.drawImage(img, tx, null);
		tx.setToTranslation(x, y);
		tx.scale(0.3, 0.3);
	}

	private void update() {
		tx.setToTranslation(x, y);
		tx.scale(1, 1);
	}

	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(0.1, 0.1);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Background.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}
	
	
}

