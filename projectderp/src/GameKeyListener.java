/**
 * KeyListener for the Game Engine.
 *
 * @author Tero Pykälämäki
 * @version 2015.0128
 * @since 1.7
 */
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

class GameKeyListener extends KeyAdapter {
	
	private Player player;
	private JFrame window;
	private KeyboardFocusManager k;
	
	public GameKeyListener(JFrame window) {
		
		this.window = window;
		
		k = KeyboardFocusManager.getCurrentKeyboardFocusManager();
	}
	
	public GameKeyListener(Player host) {
		
		player = host;	
		
		k = KeyboardFocusManager.getCurrentKeyboardFocusManager();
	}
    
    /**
     * .
     *
     * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/awt/event/KeyListener.html#keyPressed(java.awt.event.KeyEvent)">keyPressed</a>.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        // Example
        // System.out.println(e.getKeyChar() + " " + e.getKeyCode());
        
	   if (k.getActiveWindow() instanceof GameWindow) {
	    	if(e.getKeyCode() == Keybindings.UP) {
	    		// move player up;
	    		player.moves(Player.UP);
	    	} else if(e.getKeyCode() == Keybindings.DOWN) {
	    		player.moves(Player.DOWN);		
	    	} else if(e.getKeyCode() == Keybindings.RIGHT) {
	    		// move player right;
	    		player.moves(Player.RIGHT);
	    	} else if(e.getKeyCode() == Keybindings.LEFT) {
	    		// move player left;
	    		player.moves(Player.LEFT);
	    	}
    	}
    }
    
    /**
     * .
     *
     * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/awt/event/KeyListener.html#keyReleased(java.awt.event.KeyEvent)">keyReleased</a>.
     */
    @Override
    public void keyReleased(KeyEvent e) {
    	
 	    if (k.getActiveWindow() instanceof GameWindow) {
	    	if(e.getKeyCode() == Keybindings.UP ||
	    			e.getKeyCode() == Keybindings.DOWN) {
	    		player.stopVerticalMovement();		
	    	} else if(e.getKeyCode() == Keybindings.RIGHT ||
	    			e.getKeyCode() == Keybindings.LEFT) {
	    		// move player left;
	    		player.stopHorizontalMovement();
	    	}
 	    }
    }
}