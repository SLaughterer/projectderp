/**
 * Game Canvas for the Game Engine.
 *
 * @author Tero Pykälämäki
 * @version 2014.1217
 * @since 1.7
 */
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;

class GameCanvas extends JPanel {
    
	private Player player;
	private MouseInfo mouse;		
	private Sprite currentGun;
	private EnemyManager enemies;
	private Dimension dimension;
		
	public GameCanvas() {
		
	}
	
	public GameCanvas(Player host) {
		
		player = host;
			
		player.setX(50);
		player.setY(50);
		
		enemies = new EnemyManager(player);
		enemies.createEnemy();
		
		userInterface();
	}
					
    public void userInterface() {
    	
    	// minigun is placeholder
    	currentGun = new Sprite(Toolkit.getDefaultToolkit().createImage("res/Minigun.png"), 64, 64);
    	currentGun.rotation(90);
    	
		// testing
		player.addScore(1);
		
		System.out.println(currentGun.getX() + " " + currentGun.getY());
		System.out.println("Player: " + player.getX() + " " + player.getY());
    }    

    /**
     * Draws all graphical data on the Graphics object.
     *
     * @param g The Graphics context in which to paint.
     */
    public void paint(Graphics g) {
    	
    	super.paint(g);
    	            	
    	if (player != null) {
    		
        	// getting the width and height of the window
        	dimension = this.getSize();
        	
    		player.move();
    		player.rotation(Sprite.calculateDirection(
    				player.getAnchorX(), player.getAnchorY(), 
    				mousePosition()));
    		player.draw(g);
    		
    		enemies.moveEnemies();
    		enemies.drawEnemies(g);
    		    		
    		// healthbar
    		g.drawString("HEALTH", 15, 15);
    		g.setColor(Color.RED);
    		g.fillRect(15, 20, player.getHealth(), 20);
    		
    		g.setColor(Color.BLACK);
    		    		
    		// score
    		g.drawString("SCORE", dimension.width - 75, 15);
    		g.drawString(String.format("%08d", player.getScore()), dimension.width - 75, 30);
    		    		    		   
    		// current gun
    		updateCurrentGunPos();
    		currentGun.draw(g);
    	}
    }
    
    public void updateCurrentGunPos() {
    	
    	currentGun.setX(dimension.width/2 - currentGun.getWidth());
    	currentGun.setY(-25);
    }
    
    private Point mousePosition() {
    	Point point = mouse.getPointerInfo().getLocation();
    	Point canvasPoint = this.getLocationOnScreen();
    	point.setLocation(point.x - canvasPoint.x, point.y - canvasPoint.y);
    	
    	return point;
    }
}
