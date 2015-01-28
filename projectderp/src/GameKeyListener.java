/**
 * KeyListener for the Game Engine.
 *
 * @author Tero Pykälämäki
 * @version 2015.0128
 * @since 1.7
 */
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

class GameKeyListener extends KeyAdapter {
    
    /**
     * .
     *
     * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/awt/event/KeyListener.html#keyPressed(java.awt.event.KeyEvent)">keyPressed</a>.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        // Example
        // System.out.println(e.getKeyChar() + " " + e.getKeyCode());
        
    	if(e.getKeyCode() == Keybindings.UP || 
    			e.getKeyCode() == Keybindings.UP_ALT) {
    		// move player up;
    	} else if(e.getKeyCode() == Keybindings.DOWN || 
    			e.getKeyCode() == Keybindings.DOWN_ALT) {
    		// move player down;
    	}
    	
    	if(e.getKeyCode() == Keybindings.RIGHT || 
    			e.getKeyCode() == Keybindings.RIGHT_ALT) {
    		// move player right;
    	} else if(e.getKeyCode() == Keybindings.LEFT || 
    			e.getKeyCode() == Keybindings.LEFT_ALT) {
    		// move player left;
    	}
    }
    
    /**
     * .
     *
     * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/awt/event/KeyListener.html#keyReleased(java.awt.event.KeyEvent)">keyReleased</a>.
     */
    @Override
    public void keyReleased(KeyEvent e) {
        
    }
}