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
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;

class GameCanvas extends JPanel {
    
	private Player player;
	private MouseInfo mouse;
	private EnemyManager enemies;
	
	public GameCanvas() {
		
	}
	
	public GameCanvas(Player host) {
		
		player = host;
			
		player.setX(50);
		player.setY(50);
		
		enemies = new EnemyManager(player);
		enemies.createEnemy();
	}
    
    /**
     * Draws all graphical data on the Graphics object.
     *
     * @param g The Graphics context in which to paint.
     */
    public void paint(Graphics g) {
    	
    	super.paint(g);
    	        
    	if (player != null) {
    		player.move();
    		player.rotation(Sprite.calculateDirection(
    				player.getAnchorX(), player.getAnchorY(), 
    				mousePosition()));
    		player.draw(g);
    		
    		enemies.moveEnemies();
    		enemies.drawEnemies(g);
    	}
    }
    
    private Point mousePosition() {
    	Point point = mouse.getPointerInfo().getLocation();
    	Point canvasPoint = this.getLocationOnScreen();
    	point.setLocation(point.x - canvasPoint.x, point.y - canvasPoint.y);
    	
    	return point;
    }
}
