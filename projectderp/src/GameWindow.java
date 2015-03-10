/**
 * Game Window for the Game Engine.
 *
 * @author Tero Pykälämäki
 * @version 2014.1202
 * @since 1.7
 */
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

class GameWindow extends JFrame {
    
    /**
     * Objects added here are shown in the game engine's window.
     */
    private GameCanvas canvas;
    
    /**
     * Desired width of the window.
     */
    private int windowWidth;
    
    /**
     * Desired height of the window.
     */
    private int windowHeight;
    
    /**
     * Tracks mouse activity.
     */
    private GameMouseListener mouseListener;
    
    /**
     * Tracks mouse movement.
     */
    private GameMouseMotionListener mouseMotionListener;

    /**
     * Tracks keyboard activity.
     */
    private GameKeyListener keyListener;
    
    /**
     * 
     */
    private Player player;

    private EnemyManager enemies;
    
    /**
     * The parent window. It needs to be closed in code because it's hidden 
     * when GameWindow is visible and it doesn't automatically close when
     * GameWindow is closed. 
     */
    private MenuWindow menu;
    
    /**
     * Constructs the window used in the game engine.
     * @throws IOException 
     * @throws FileNotFoundException 
     */
    public GameWindow(final MenuWindow menu) throws FileNotFoundException, IOException {
    	
    	this.menu = menu;
        windowWidth = 800;
        windowHeight = 600;
        BufferedImage img;
        
		img = ImageIO.read(new FileInputStream(new File("res/2H Stance.png") ));
		
        player = new Player(img, 64, 64, 64, 64);
        player.setHitbox(new Hitbox(Hitbox.TYPE_CIRCLE, 16));
        player.setX(windowWidth/2 - 32);
        player.setY(windowHeight/2 - 32);
        
        canvas = new GameCanvas(player);
        player.setGun();
        
        // acquire memory location of EnemyManager.
        enemies = canvas.getEnemyManager();
        mouseListener = new GameMouseListener(player);
        mouseMotionListener = new GameMouseMotionListener(player);
        keyListener = new GameKeyListener(player, this);
        
        canvas.setPreferredSize(
                new Dimension(windowWidth, windowHeight));
        
        canvas.addMouseListener(mouseListener);
        canvas.addMouseWheelListener(mouseListener);
        canvas.addMouseMotionListener(mouseMotionListener);
        this.addKeyListener(keyListener);
        
        add(canvas);
        
        // Add components here.
        
        setTitle("Game Window");
        pack();
        // do nothing on close because there's a custom closing operation
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        // custom closing operation
        WindowListener exitListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	
            	Object[] options = {"Yes", "No", "To menu"};
            	
                int confirm = JOptionPane.showOptionDialog(null, 
                										   "Are You Sure to Close Application?", "Exit Confirmation", 
                										   JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);

                closeWindow(confirm);
            }
        };
        
        addWindowListener(exitListener);
        
        setVisible(true);
    }

    public void checkCollisions() {
    	// collisions
    	checkPlayerMovement();
    }
    
    private void checkPlayerMovement() {
    	Enemy enemy;
    	
    	for (int i = 0; i < enemies.size(); i++) {
    		enemy = enemies.getEnemy(i);
    		
    		if (Hitbox.collisionCheck(player, enemy)) {
    			if (Hitbox.verticallyAligned(player, enemy)) {
    				if ( ( player.getMovementY() < 0 
    						&& enemy.getAnchorY() < player.getAnchorY() )
    						||
    						( player.getMovementY() > 0
    						&& enemy.getAnchorY() > player.getAnchorY() )
    					) {
    					player.stopVerticalMovement();
    				}
    			}
    			
    			if (Hitbox.horizontallyAligned(player, enemy)) {
    				if ( ( player.getMovementX() < 0 
    						&& enemy.getAnchorX() < player.getAnchorX() )
    						||
    						( player.getMovementX() > 0
    						&& enemy.getAnchorX() > player.getAnchorX() )
    					) {
    					player.stopHorizontalMovement();
    				}
    			}
    		}
    	}
    }
    
    public int getWindowWidth() {
    	
    	return windowWidth;
    }
    
    public int getWindowHeight() {
    	
    	return windowHeight;
    }
    
    /**
     * Closes the GameWindow. 
     * 
     * Either by closing the whole game or just returning to menu.
     * 
     * @param choice Determines what is closed.
     */
    public void closeWindow(int choice) {
    
        if (choice == 0) {
        	System.exit(0);
     	
        } else if (choice == 2) {
        	menu.setVisible(true);
        	dispose();
        }	
    }
}
