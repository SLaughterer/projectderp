/**
 * Game Canvas for the Game Engine.
 *
 * @author Tero Pykälämäki
 * @version 2014.1217
 * @since 1.7
 */
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Color.*;

class GameCanvas extends JPanel {
    
	private Player player;
	private GameWindow gameWindow;
	private MouseInfo mouse;		
	private Sprite currentGun;
	private EnemyManager enemies;
	
//	private Sprite minigun;
	
	public GameCanvas() {
		
	}
	
	public GameCanvas(Player host, GameWindow gameWindow) {
		
		player = host;
		this.gameWindow = gameWindow;
			
		player.setX(50);
		player.setY(50);
		
		enemies = new EnemyManager(player);
		enemies.createEnemy();
		
		userInterface();
	}
					
    public void userInterface() {
    	
    	currentGun = new Sprite(Toolkit.getDefaultToolkit().createImage("res/Minigun.png"), 64, 64);
    	currentGun.rotation(90);
    	currentGun.setX(gameWindow.getWindowWidth()/2 - currentGun.getWidth());
    	currentGun.setY(-25);
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
    		
    		g.drawString("HEALTH", 15, 15);
    		g.setColor(Color.RED);
    		g.fillRect(15, 20, player.getHealth(), 20);
    		
    		g.setColor(Color.BLACK);
    		
    		g.drawString("SCORE", gameWindow.getWindowWidth() - 75, 15);
    		g.drawString(String.format("%08d", player.getScore()), gameWindow.getWindowWidth() - 75, 30);
    		
    		// testing
    		player.addScore(1);
    		    		
    		currentGun.draw(g);
    		
    	}
    }
    
    private Point mousePosition() {
    	Point point = mouse.getPointerInfo().getLocation();
    	Point canvasPoint = this.getLocationOnScreen();
    	point.setLocation(point.x - canvasPoint.x, point.y - canvasPoint.y);
    	
    	return point;
    }
}
