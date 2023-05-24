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
	private double vx, vy;
	private Image img;
	private double slope;
	private AffineTransform tx;
	private boolean rightshot = false;
	private boolean rightout = false;
	private boolean leftshot = false;
	private boolean leftout = false;
	private boolean reset = false;
	
	
	
	public Ball() {
		img = getImage("/imgs/tempBall.png"); // load the image for Tree

		tx = AffineTransform.getTranslateInstance(x, y);
		init(x, y); // initialize the location of the image
					// use your variables
		x = 670;
		y = 705;
		vy = 15;//velocity to get the ball bouncing
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
		
		
			
		//starts the shot arc
		/*
		if (y <= 200) {
			vy = 0;
			if (rightshot) {
				vx = 15;
			}
			if (leftshot) {
				vx = -15;
			}

		}
		if (x > 1300 && rightshot) {
			vy = 20;
			if (y > 400) {
				vx = 0;
			}
		}
		if (y > 800 && rightshot) {
			reset = true;
			y = 705;
			rightshot = false;
			rightout = false;
			x = 665;
			y = 705;
			vy = 20;

		}
		if (x < 450 && leftshot) {
			vy = 20;
			if (y > 400) {
				vx = 0;
			}
		}
		if (y > 800 && leftshot) {
			reset = true;
			y = 705;
			leftshot = false;
			leftout = false;
			x = 665;
			y = 705;
			vy = 20;

		}
		*/
			
			
			
			//reset
			if(y>800) {
				y=755;
				vy=10;
			}
			
			//ends shot arc
			if(y<200) { //bounce off ceiling (wind bending)
				vy*=-1;
			}
			
			
			x += vx;
			y += vy;
			
		//g.drawRect(x+20,y,75,75);
		
	}
	
	
	
	public void resetShot(boolean set) {
		reset = set;
	}
	public boolean getReset() {
		return reset;
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
		//stops ball moving at key released
	}
	public void moveLeft(int speed) {
		vx=-(speed);
		//System.out.println("worked");
	}
	public void moveRight(int speed) {
		vx=(speed);
		//System.out.println("worked");
	}
	
	
	
	
	public void thrown(int pl) {
		
		if(pl==1) {
			slope = 400.0/Math.abs((1400-x));
			vy = slope*-15;
			System.out.println(vy);
			vx = 10;	
			
		}
		if(pl==2) {
			slope = 400.0/(250-x);
			vy = slope*-10;
			System.out.println(vy);
			vx = 10;	
		}
		
	}
	
	
	
	/*
	//starts methods for shots from player 1
	public void rightfar() {
		//y=705;
		//vx=20;
		//vy=-20;
		rightshot=true;
	}
	public void rightnormal() {
		y=705;
		vx=20;
		vy=-40;
		rightshot=true;
	}
	public void rightclose() {
		y=705;
		vx=5;
		vy=-80;
		rightshot=true;
	}
	public void rightout() {
		y=705;
		vx=30;
		vy=0;
		rightout=true;
	}
	//ends shot methods for player 1
	//starts shot methods for player 2
	public void leftfar() {
		//y=705;
		//vx=-20;
		//vy=-20;
		leftshot=true;
	} 
	public void leftnormal() {
		y=705;
		vx=-20;
		vy=-40;
		leftshot=true;
	}
	public void leftclose() {
		y=705;
		vx=-5;
		vy=-80;
		leftshot=true;
	}
	public void leftout() {
		y=705;
		vx=-30;
		vy=0;
		leftout=true;
	}
	*/
	
	
	//ends shot methods for player 2
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void setY(int tempY) {
		y=tempY;
	}
	public double getVX() {
		return vx;
	}
	public void setVX(double setter) {
		vx = setter;
	}
	public void setVY(double setter) {
		vy = setter;
	}
	public boolean hit(Rectangle h) {//hitbox for scoring mechanism
		
		//represent the mouse as a rectangle
		
		//Rectangle b = new Rectangle(m.getX(),mouse.getY(), 25, 25);
		
		
		
		
		Rectangle ballHitbox = new Rectangle(x + 2, y , 45, 45);
		
		
		if(h.intersects(ballHitbox)) {
			
			//System.out.println("SCORE");
			vx=0;
			return true;
		}
	
		
		return false;
	}
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
