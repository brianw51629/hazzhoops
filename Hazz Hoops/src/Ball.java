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
	private double gravity = 1.5;
	private Image img;
	private AffineTransform tx;
	private boolean reset = false;
	private boolean shot = false;
	private boolean possesion = true;
	
	
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
	public void setPossesion(boolean set) {
		possesion = set;
	}
	public void paint(Graphics g) {
		// these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		 
		    
		
		//update();
		g2.drawImage(img, tx, null);
		g.setColor(new Color(0,0,0));
		tx.setToTranslation(x, y);
		tx.scale(0.05, 0.05);
		
		
			
		
			
			
			
			//reset
			if(y>800) {
				y=755;
				vy=10;
				if(shot) {
					reset=true;
					shot=false;
				}
				
			}
			if(reset) {
				if(possesion) {
					x = 940;
				}else if(possesion==false) {
					x = 670;
				}
			}
			//ends shot arc
			
			
			x += vx;
			y += vy;
			vy += gravity;
			
		//g.drawRect(x+20,y,75,75);
		
	}
	
	
	public void setShot(boolean set){
		shot = set;
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
		
		
		y=750;
		double Up_Displacement = -550;
		double Down_Displacement = 180;
		double Right_Displacement = (1400-x)+40;
		double Left_Displacement = (x-320);
		vy = -(Math.sqrt(-2*gravity*Up_Displacement));
		double Tup = Math.sqrt((-2*Up_Displacement)/gravity);
		double Tdown = Math.sqrt((2*Down_Displacement)/gravity);
		if(pl==1) {
			vx = Right_Displacement/(Tup+Tdown);
		}
		if(pl==2) {
			vx = -(Left_Displacement/(Tup+Tdown));
		}
	}
	
	
	
	
	
	 
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
	public void setX(int setter) {
		x = setter;
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
