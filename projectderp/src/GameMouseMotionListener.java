import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class GameMouseMotionListener extends MouseMotionAdapter {
	
	@Override
	public void mouseMoved(MouseEvent e) {
		System.out.println(e.getX() + " " + e.getY());
	}
}
