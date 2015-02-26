
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

class MenuWindow extends JFrame implements ActionListener {
    
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
    
    private JButton playButton, optionsButton;
    
    public MenuWindow() {
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
        
        setTitle("Menu Window");
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        menu();
    }
    
    public void menu() {
    	
    	ImageIcon playButtonIcon = new ImageIcon("res/playButton.png");
    	ImageIcon optionsButtonIcon = new ImageIcon("res/optionsButton.png");
    	
        playButton = new JButton(playButtonIcon);        
        optionsButton = new JButton(optionsButtonIcon);
        
        playButton.setPreferredSize(new Dimension(140, 60));
        optionsButton.setPreferredSize(new Dimension(140, 60));
              
        canvas.add( playButton, BorderLayout.LINE_START);
        canvas.add( optionsButton, BorderLayout.CENTER);
             
        playButton.addActionListener(this);
                
        setVisible(true);     
    }
   
	public void actionPerformed(ActionEvent arg0) {
		
		System.out.println("testing");
				
		GameWindow gameWindow = new GameWindow(this);
		GameLoop gameLoop = new GameLoop(gameWindow);	
		
		setVisible(false);
	}
}
