/**
 * KeyListener for the Game Engine.
 *
 * @author Tero Pykälämäki
 * @version 2014.1214
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
        System.out.println(e.getKeyChar() + " " + e.getKeyCode());
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