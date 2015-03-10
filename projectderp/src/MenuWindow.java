
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.io.FileNotFoundException;
import java.io.IOException;

class MenuWindow extends JFrame {
    
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
     * Constructs the window used in the game engine.
     */
    
    /**
     * 
     */
    private JButton playButton, optionsButton;
    
    /**
     * 
     */
    private MenuWindow menuWindow = this;
    
    /**
     * 
     */
    private JTextField textField;
    
    /**
     * 
     */
    private String playerName = "Defaultiina";
    
    public MenuWindow() {
        windowWidth = 600;
        windowHeight = 500;
        
        canvas = new GameCanvas();
        
        canvas.setPreferredSize(
                new Dimension(windowWidth, windowHeight));
        
        add(canvas);
        
        // Add components here.
        
        setTitle("Menu Window");
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        menu();
        
        setVisible(true);
    }
    
    public void menu() {
    	    	
    	ImageIcon playButtonIcon = new ImageIcon("res/playButton.png");
    	ImageIcon optionsButtonIcon = new ImageIcon("res/optionsButton.png");
    	
        playButton = new JButton(playButtonIcon);        
        optionsButton = new JButton(optionsButtonIcon);
        
        playButton.setPreferredSize(new Dimension(140, 60));
        optionsButton.setPreferredSize(new Dimension(140, 60));
        
        textField = new JTextField("Your name here (press Enter)", 15);
              
        canvas.add(playButton, BorderLayout.LINE_START);
        canvas.add(optionsButton, BorderLayout.CENTER);
        canvas.add(textField, BorderLayout.LINE_END);
             
        playButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		
        		GameWindow gameWindow;
        		GameLoop gameLoop;
				
        		try {
        			// 
					gameWindow = new GameWindow(menuWindow, textField.getText());
					gameLoop = new GameLoop(gameWindow);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 	
        		
        		setVisible(false);
        	}  	
        });
        
        optionsButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		
        		System.out.println("options");
        		
        		OptionsWindow optionsWindow = new OptionsWindow();
        				
        		//setVisible(false);
        	}  	
        });   
        
/*       textField.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		
        	     textField.selectAll();
        	}  	
        });  
*/
    }
}
