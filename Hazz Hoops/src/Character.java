
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
	private double gravity;
	private int temp;
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
		y=600;
		
		//sets x based on player 1 or 2
		if(pl==1) {
			x=500;
		}
		if(pl==2) {
			x=900;
		}
		
		temp = speed;
		
		tx = AffineTransform.getTranslateInstance(x, y);
		tx.scale(0.25,0.25);
	}

	

	public void changePicture(String newFileName) {
		img = getImage(newFileName);
	}

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(img, tx, null);
		
		//boundaries
		if(x>=1250) {
			x=1200;
		}
		if(x<=275) {
			x=325;
		}
		
		//updating characters
		x+=vx;
		y+=vy;
		vy += gravity;
		if(y > 600) {
			gravity = 0;
			vy = 0;
			y = 600;
			speed = temp;
		}
		
		
		//g.fillRect(x+110, y, 30, 200);
		
		
		//stuff?
		tx.setToTranslation(x, y);
		
		//flip signs to flip image
		tx.scale(0.25, 0.25);
	}
	
	//reset for each player
	public void reset(int pl) {
		//y=600;
		//resets player1
		if(pl==1) {
			x=500;
		}
		//resets player2
		if(pl==2) {
			x=900;
		}	
	}
	
	
	//moving character
	public void moveLeft() {
		vx=-(speed);
	}
	public void moveRight() {
		vx=(speed);
	}
	
	//jump
	public void jump() {
		if(vy == 0) {
			vy=-15;
			gravity = 1.0;
		}
	}
	
	
	public boolean block(int bx, int by) {
		
		
		Rectangle player = new Rectangle(x+110, y, 30, 200);
		Rectangle ball = new Rectangle(bx, by , 45, 45);
		
		if(gravity != 3.0) {
			vy = -55;
			gravity = 3.0;
			speed = 15;
		}		
		
		if(player.intersects(ball)) {
			return true;
		}
		return false;
	}
	
	
	
	
	
	//stopping the character
	public void moveStop() {
		vx=0;
	}
	
	//what does this do
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(0, 0);
	}

	
	//get the image or something
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
	
	
	
	//getters and setters
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
	public int getVY() {
		return vy;
	}
	public void setVX(int setter) {
		vx = setter;
	}
}