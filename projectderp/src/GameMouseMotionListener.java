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
		double degrees = Sprite.calculateDirection(player.getAnchorX(), player.getAnchorY(), e.getX(), e.getY());
		System.out.println("x");
		System.out.println(player.getAnchorX());
		System.out.println(e.getX());
		System.out.println("y");
		System.out.println(player.getAnchorY());
		System.out.println(e.getY());
		System.out.println();
    	System.out.println(player.getFacingDirection());
    	System.out.println(degrees);
        player.rotation(degrees);
		
	}
}
