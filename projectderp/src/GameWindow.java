/**
 * Game Window for the Game Engine.
 *
 * @author Tero Pykälämäki
 * @version 2014.1202
 * @since 1.7
 */
import javax.swing.JFrame;

import java.awt.Dimension;
import java.awt.Toolkit;

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
     * Tracks keyboard activity.
     */
    private GameKeyListener keyListener;
    
    /**
     * 
     */
    private Player player;
    
    /**
     * Constructs the window used in the game engine.
     */
    public GameWindow() {
        windowWidth = 600;
        windowHeight = 500;
        player = new Player(Toolkit.getDefaultToolkit().createImage("res/derpvivor.png"), 64, 64);
        canvas = new GameCanvas(player);
        mouseListener = new GameMouseListener(player);
        keyListener = new GameKeyListener(player);
        
        canvas.setPreferredSize(
                new Dimension(windowWidth, windowHeight));
        
        canvas.addMouseListener(mouseListener);
        canvas.addMouseWheelListener(mouseListener);
        this.addKeyListener(keyListener);
        
        add(canvas);
        
        // Add components here.
        
        setTitle("Game Window");
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}
