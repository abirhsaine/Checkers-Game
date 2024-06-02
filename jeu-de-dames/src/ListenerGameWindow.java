import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ListenerGameWindow implements MouseListener  {
	private GameWindow gameWindow; 
	
	public ListenerGameWindow(GameWindow gameWindow){
		this.gameWindow=gameWindow; 
	}

	public void mouseClicked(MouseEvent arg0) {
		
	}

	public void mouseEntered(MouseEvent arg0) {
	
	}

	public void mouseExited(MouseEvent arg0) {
		
	}

	public void mousePressed(MouseEvent arg0) {
		gameWindow.exitWindow();
	}

	public void mouseReleased(MouseEvent arg0) {
		
	}
}
