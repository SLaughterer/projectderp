import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class GameMouseMotionListener extends MouseMotionAdapter {
	
	private Player player;
	
	public GameMouseMotionListener(Player host) {
		super();
		
		player = host;
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		// System.out.println(e.getX() + " " + e.getY());
		int degrees = Sprite.calculateDirection(player.getX(), player.getY(), e.getX(), e.getY());
    	System.out.println(degrees);
        player.rotation(degrees);
		
	}
}
