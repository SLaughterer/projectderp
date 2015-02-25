
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
    
    private JButton testiButton, testiButton2;
    
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
//        setVisible(true);
        
        menu();
    }
    
    public void menu() {
    	
    	ImageIcon testIcon = new ImageIcon("res/menuButton.png");
    	
        testiButton = new JButton(testIcon);        
        testiButton2 = new JButton(testIcon);
        
        testiButton.setPreferredSize(new Dimension(140, 60));
        testiButton2.setPreferredSize(new Dimension(140, 60));
              
        canvas.add( testiButton, BorderLayout.LINE_START);
        canvas.add( testiButton2, BorderLayout.CENTER);
             
        testiButton.addActionListener(this);
                
        setVisible(true);     
    }
   
	public void actionPerformed(ActionEvent arg0) {
		
		System.out.println("testing");
		
//		requestFocusInWindow();
		
		GameWindow gameWindow = new GameWindow();
		GameLoop gameLoop = new GameLoop(gameWindow);	
		
		setVisible(false);
	}
}
