/**
 * Game Window for the Game Engine.
 *
 * @author Tero Pykälämäki
 * @version 2014.1202
 * @since 1.7
 */
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

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
    
    /**
     * The parent window. It needs to be closed in code because it's hidden 
     * when GameWindow is visible and it doesn't automatically close when
     * GameWindow is closed. 
     */
    private MenuWindow menu;
    
    /**
     * Constructs the window used in the game engine.
     */
    public GameWindow(final MenuWindow menu) {
    	
    	this.menu = menu;
        windowWidth = 600;
        windowHeight = 500;
        player = new Player(Toolkit.getDefaultToolkit().createImage("res/derpvivor.png"), 64, 64);
        canvas = new GameCanvas(player);
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
    
    public int getWindowWidth() {
    	
    	return windowWidth;
    }
    
    public int getWindowHeight() {
    	
    	return windowHeight;
    }
    
    public void closeWindow(int confirm) {
    
        if (confirm == 0) {
        	System.exit(0);
     	
        } else if (confirm == 2) {
        	menu.setVisible(true);
        	dispose();
        }	
    }
}
