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
import javax.swing.JOptionPane;

class GameKeyListener extends KeyAdapter {
	
	private Player player;
	private GameWindow gameWindow;
	private MenuWindow menuWindow;
	private KeyboardFocusManager k;
	
	public GameKeyListener(MenuWindow menuWindow) {
		
		this.menuWindow = menuWindow;
				
		k = KeyboardFocusManager.getCurrentKeyboardFocusManager();
	}
		
	public GameKeyListener(Player host, GameWindow gameWindow) {
		
		player = host;	
		this.gameWindow = gameWindow;
		
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
	    	} else if (e.getKeyCode() == Keybindings.ESCAPE) {
	    		
	    		int confirm = JOptionPane.showOptionDialog(null, 
						   "Do you want to return to menu?", "Menu confirmation", 
						   JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
	    		
	    		// converting the numbers to the ones used by the closeWindow-method
	            if (confirm == 0) {
	            	confirm = 2;	         	
	            }
	            
	    		gameWindow.closeWindow(confirm);
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