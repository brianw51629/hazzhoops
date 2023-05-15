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
	private boolean shot = false;
	private boolean out = false;
	public Ball() {
		img = getImage("/imgs/tempBall.png"); // load the image for Tree

		tx = AffineTransform.getTranslateInstance(x, y);
		init(x, y); // initialize the location of the image
					// use your variables
		x = 665;
		y = 875;

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
		tx.scale(0.05, 0.05);
		
		
		
		
			if(y<=400) {
				vy=0;
				vx=15;
			}
			if(x>1350&&shot) {
				vy=20;
				if(y>525) {
					vx=0;
				}
			}
		
		
		
		x+=vx;
		y+=vy;
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
	
	public void moveStop() {
		vx=0;
	}
	public void moveLeft(int speed) {
		vx=-(speed);
		//System.out.println("worked");
	}
	public void moveRight(int speed) {
		vx=(speed);
		//System.out.println("worked");
	}
	
	public void far() {
		vx=20;
		vy=-20;
		shot=true;
	}
	public void normal() {
		vx=20;
		vy=-40;
		shot=true;
	}
	public void close() {
		vx=5;
		vy=-80;
		shot=true;
	}
	public void out() {
		vx=30;
		vy=0;
		out=true;
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
