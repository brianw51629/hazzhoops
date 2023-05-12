
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Character {
	private int x, y;
	private int vx, vy;
	private Image img;
	private AffineTransform tx;
	private int newV = 0;
	private int block;
	private int shoot;
	private int speed;
	private int steal;
	public Character() {
		img = getImage("/imgs/tempPlayer.png"); // load the image for Tree

		tx = AffineTransform.getTranslateInstance(x, y);
		
		x=500;
		y=675;
		block=50;
		shoot=50;
		speed=10;
		steal=50;
		 // initialize the location of the image
					// use your variables
		
	}
	
	
	public Character(int bl, int sh, int sp, int st) {
		img = getImage("/imgs/tempPlayer.png"); // load the image for Tree

		tx = AffineTransform.getTranslateInstance(x, y);
		
		x=500;
		y=675;
		vx=0;
		vy=0;
		block=bl;
		shoot=sh;
		speed=sp;
		steal=st;
		 // initialize the location of the image
					// use your variables
		
	}

	public Character(String fileName) {
		img = getImage("/imgs/" + fileName); // load the image for Tree

		tx = AffineTransform.getTranslateInstance(x, y);
		init(x, y); // initialize the location of the image
					// use your variables
		
		x = 500;
		y = 470;
		vx=0;
		vy=0;
	}

	public void changePicture(String newFileName) {
		img = getImage(newFileName);
		init(x, y);
	}

	public void paint(Graphics g) {
		// these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		
		System.out.println(y);
		//update();
		g2.drawImage(img, tx, null);
		g.setColor(new Color(0,0,0));
		if(y<=650) {
			vy=10;
		}
		if(y>675) {
			vy=0;
			y=675;
		}
		x+=vx;
		y+=vy;
		tx.setToTranslation(x, y);
		tx.scale(3,3);
		//g.drawRect(x+20,y,75,75);
		
	}

	public void setNewV(int grabbed) {
		newV=(grabbed+1)*2;
	}
	
	
	public void reset() {
		x = (int)(Math.random() * 751);
		y = 200;
	}
	
	public void moveLeft() {
		vx=-(speed);
		System.out.println("worked");
	}
	public void moveRight() {
		vx=(speed);
		System.out.println("worked");
	}
	public void shot() {
		vy=-10;
	}
	public void moveStop() {
		vx=0;
	}
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(0, 0);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Character.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}
	
	
	public int getSpeed() {
		return speed;
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
	
	
	
	
	
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

