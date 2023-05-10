
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Background {
	private int x, y;
	private Image img;
	private AffineTransform tx;

	public Background() {
		img = getImage("/imgs/temp background.png"); // load the image for Tree

		tx = AffineTransform.getTranslateInstance(x, y);
		init(0, 1000); // initialize the location of the image
					// use your variables

		

	}

	public Background(String fileName) {
		img = getImage("/imgs/" + fileName); // load the image for Tree

		tx = AffineTransform.getTranslateInstance(x, y);
		init(x, y); // initialize the location of the image
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
		update();
		g2.drawImage(img, tx, null);
	}

	private void update() {
		tx.setToTranslation(x, y);
		tx.scale(1, 1);
	}

	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(0.25, 0.25);
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
