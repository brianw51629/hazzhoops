import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

public class Select extends Background {
	
	private boolean b1;
	private boolean b2;
	private boolean b3;
	public Select() {
		
		
		super("playerSelect.png");
		
	}
	
	
	public boolean changeScreen(MouseEvent mouse) {
		
		Rectangle m = new Rectangle(mouse.getX(), mouse.getY(), 30, 30);
		
		//add dimensions for buttons
		Rectangle box1 = new Rectangle(265, 760, 210, 140);
		Rectangle box2 = new Rectangle(795, 760, 210, 140);
		Rectangle box3 = new Rectangle(1340, 760, 210, 140);
		
		if(m.intersects(box1) || m.intersects(box2) || m.intersects(box3)) {
			
			// display text 
			if(m.intersects(box1)) {
				b1 = true;
			}
			if(m.intersects(box2)) {
				b2 = true;
			}
			if(m.intersects(box3)) {
				b3 = true;
			}
		}
		return true;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	public boolean isB1() {
		return b1;
	}


	public void setB1(boolean b1) {
		this.b1 = b1;
	}


	public boolean isB2() {
		return b2;
	}


	public void setB2(boolean b2) {
		this.b2 = b2;
	}


	public boolean isB3() {
		return b3;
	}


	public void setB3(boolean b3) {
		this.b3 = b3;
	}
	
	
}
	
	
	
	
	// hit boxes on rectangles to select players
	
	
	
	
	// turn rectangle green when player selected
	
	
	
	// create display text for Player 1 : 
	// Player 2: 
	


