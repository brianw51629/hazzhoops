import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

public class Select extends Background {
	
	public Select() {
		
		
		super("playerSelect.png");
		
	}
	
	
	public boolean changeScreen(MouseEvent mouse) {
		
		Rectangle m = new Rectangle(mouse.getX(), mouse.getY(), 30, 30);
		
		//add dimensions for buttons
		Rectangle box1 = new Rectangle();
		Rectangle box2 = new Rectangle();
		Rectangle box3 = new Rectangle();
		
		if(m.intersects(box1)) {
			
			// display text 
			
			
			
			// return true
			return true;
		}
				return false;
		
		
	}
	
	
}
	
	
	
	
	// hit boxes on rectangles to select players
	
	
	
	
	// turn rectangle green when player selected
	
	
	
	// create display text for Player 1 : 
	// Player 2: 
	


