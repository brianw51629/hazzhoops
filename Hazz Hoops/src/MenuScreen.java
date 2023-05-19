import java.awt.Rectangle;
import java.awt.event.MouseEvent;

public class MenuScreen extends Background {
	
	public MenuScreen(){
		super("MenuScreen.png");
	}
	
	
	
	 
	//selecting buttons
	
	
	public boolean highscore(MouseEvent mouse) {
		//mouse cursor hit box
		Rectangle m = new Rectangle(mouse.getX(), mouse.getY(), 30, 30);
		
		//button
		Rectangle s1 = new Rectangle(450, 800, 899, 140);
		if(m.intersects(s1)) {
			return true;
		}
		return false;
		
	}
	
	public boolean play(MouseEvent mouse) {
		
		//checks if cursor is within the start button
		if((Math.pow(((mouse.getX()-890)), 2) + Math.pow((mouse.getY() - 450), 2)) <= Math.pow(300, 2)) {
			return true;
		}		
		return false;
	}
	
}
	