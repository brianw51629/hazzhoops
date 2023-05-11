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

public class Ball {
	private int x, y;
	private int vx, vy;
	private Image img;
	private AffineTransform tx;
	private int newV = 0;
	public Ball() {
		img = getImage("/imgs/tempBall.png"); // load the image for Tree

		tx = AffineTransform.getTranslateInstance(x, y);
		init(x, y); // initialize the location of the image
					// use your variables
		x = 450;
		y = 200;

	}

	public Ball(String fileName) {
		img = getImage("/imgs/" + fileName); // load the image for Tree

		tx = AffineTransform.getTranslateInstance(x, y);
		init(x, y); // initialize the location of the image
					// use your variables
	}

	public void changePicture(String newFileName) {
		img = getImage(newFileName);
		init(x, y);
	}

	public void paint(Graphics g) {
		// these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		
		
		
		//update();
		g2.drawImage(img, tx, null);
		g.setColor(new Color(0,0,0));
		tx.setToTranslation(x, y);
		tx.scale(3, 3);
		//g.drawRect(x+20,y,75,75);
		
	}

	public void setNewV(int grabbed) {
		newV=(grabbed+1)*2;
	}
	
	
	public void reset() {
		x = (int)(Math.random() * 751);
		y = 200;
		vx = 3;
		vy = 3;
		vx+=newV;
		vy += newV;
	}
	
	private void update() {
		tx.setToTranslation(x, y);
		tx.scale(.5, .5);
	}

	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(.5, .5);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Ball.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}
	
	
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	public int getVX() {
		return vx;
	}
	public void setVX(int setter) {
		vx = setter;
	}
	public boolean hit(MouseEvent mouse) {
		
		//represent the mouse as a rectangle
		
		Rectangle m = new Rectangle(mouse.getX(),mouse.getY(), 25, 25);
		
		
		//Duck hit box
		
		Rectangle d = new Rectangle(x+20,y,75,75);
		//Rectangle d2 = new Rectangle(x+20,y,75,75);
		
		if(m.intersects(d)) {
			vx=0;
			vy=10;
			System.out.println("HIT");
			return true;
		}
	
		
		return false;
	}
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
