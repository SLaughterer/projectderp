/**
 * MouseListener for the Game Engine.
 *
 * @author Tero Pykälämäki
 * @version 2014.1217
 * @since 1.7
 */
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

class GameMouseListener extends MouseAdapter {
	
	private Player player;
	
	public GameMouseListener() {
		
	}
	
	public GameMouseListener(Player host) {
		
		player = host;	
	}
    
    /**
     * .
     *
     * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/awt/event/MouseListener.html#mouseClicked(java.awt.event.MouseEvent)">mouseClicked</a> .
     */
    @Override
    public void mouseClicked(MouseEvent e) {
    	player.shoot();
    }
    
    public void mousePressed(MouseEvent e) {
        player.setShooting(true);
    }
    
    public void mouseReleased(MouseEvent e) {
    	player.setShooting(false);
    }
    
    /**
     * .
     *
     * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/awt/event/MouseWheelListener.html#mouseWheelMoved(java.awt.event.MouseWheelEvent)">mouseWheelMoved</a>.
     */
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        
    }
}