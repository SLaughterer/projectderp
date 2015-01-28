/**
 * Temporary memory location for controls of the game.
 * 
 * @author Tero
 * @version 2015.0128
 * @since 1.7
 */
import java.awt.event.KeyEvent;

public interface Keybindings {
	int UP = KeyEvent.VK_W;
	int DOWN = KeyEvent.VK_S;
	int LEFT = KeyEvent.VK_A;
	int RIGHT = KeyEvent.VK_D;
	
	int UP_ALT = KeyEvent.VK_UP;
	int DOWN_ALT = KeyEvent.VK_DOWN;
	int LEFT_ALT = KeyEvent.VK_LEFT;
	int RIGHT_ALT = KeyEvent.VK_RIGHT;
}
