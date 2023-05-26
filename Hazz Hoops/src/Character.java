
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
	private int player;
	public Character() {
		img = getImage("/imgs/tempPlayer.png"); // load the image for Tree

		tx = AffineTransform.getTranslateInstance(x, y);
		
		x=500;
		y=550;
		block=50;
		shoot=50;
		speed=10;
		steal=50;
		player=0;
		 // initialize the location of the image
					// use your variables
		
	}
	
	
	public Character(int bl, int sh, int sp, int st, int pl) {
		img = getImage("/imgs/tempPlayer.png"); // load the image for Tree

		
		
		
		vx=0;
		vy=0;
		//start of ratings
		block=bl;
		shoot=sh;
		speed=sp;
		steal=st;
		player=pl;
		//end of ratings
		y=600;
		//sets x based on player 1 or 2
		if(pl==1) {
			x=500;
		}
		if(pl==2) {
			x=900;
		}
		
		tx = AffineTransform.getTranslateInstance(x, y);
		tx.scale(0.25,0.25);
		//System.out.println("x is "+x+" and y is "+y);
		
	}

	

	public void changePicture(String newFileName) {
		img = getImage(newFileName);
	}

	public void paint(Graphics g) {
		// these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		
		//System.out.println(x);
		//update();
		g2.drawImage(img, tx, null);
		g.setColor(new Color(0,0,0));
		
		//start of jump going down
		if(y<=525) {
			vy=10;
		}
		if(y>600) {
			vy=0;
			y=600;
		}
		//end of jump going down
		if(x>=1250) {
			x=1200;
		}
		if(x<=275) {
			x=325;
		}
		
		x+=vx;
		y+=vy;
		tx.setToTranslation(x, y);
		tx.scale(0.25, 0.25);
		//g.drawRect(x+20,y,75,75);
		
	}

	public void setNewV(int grabbed) {
		newV=(grabbed+1)*2;
	}
	
	//reset for each player
	public void reset(int pl) {
		y=600;
		//resets p1
		if(pl==1) {
			x=500;
		}
		//resets p2
		if(pl==2) {
			x=900;
		}
		
		
		
	}
	
	public void moveLeft() {
		
		vx=-(speed);
	}
	public void moveRight() {
		vx=(speed);
	}
	
	
	
	public void jump() {
		//jump
		vy=-10;
	}
	
	
	
	
	public void moveStop() {
		//zeros the velocity for key release
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

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

