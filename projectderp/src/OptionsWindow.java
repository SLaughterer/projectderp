
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.BorderLayout;

class OptionsWindow extends JFrame {
    
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
     * Constructs the window used in the game engine.
     */

    private JButton menuButton;
    
    public OptionsWindow() {
        windowWidth = 600;
        windowHeight = 500;
        canvas = new GameCanvas();
        mouseListener = new GameMouseListener();
        keyListener = new GameKeyListener(this);
        
        canvas.setPreferredSize(
                new Dimension(windowWidth, windowHeight));
        
        canvas.addMouseListener(mouseListener);
        canvas.addMouseWheelListener(mouseListener);
        this.addKeyListener(keyListener);
        
        add(canvas);
        
        // Add components here.
        
        setTitle("Options Window");
        pack();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
     
        options();
     
        setVisible(true);
    }
    
    public void options() {
    	
    	ImageIcon menuButtonIcon = new ImageIcon("res/menuButton.png");
    	
    	menuButton = new JButton(menuButtonIcon);        
        
    	menuButton.setPreferredSize(new Dimension(140, 60));
              
        canvas.add( menuButton, BorderLayout.LINE_START);
             
        menuButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		
        		System.out.println("testing menu");
        		
        		dispose();
        	}  	
        });  
    }
}
