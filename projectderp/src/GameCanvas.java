/**
 * Game Canvas for the Game Engine.
 *
 * @author Tero Pykälämäki
 * @version 2014.1217
 * @since 1.7
 */
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

class GameCanvas extends JPanel {
    
	private Player player;
	
	public GameCanvas(Player host) {
		
		player = host;
			
		player.setX(50);
		player.setY(50);
	}
    
    /**
     * Draws all graphical data on the Graphics object.
     *
     * @param g The Graphics context in which to paint.
     */
    public void paint(Graphics g) {
    	
    	g.fillRect(10, 10, 25, 25);
        
    	player.move();
    	player.draw(g);
    } 
}