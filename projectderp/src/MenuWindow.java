
import javax.annotation.PostConstruct;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import java.awt.Composite;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JLabel;
import java.awt.Font;

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
        
    public MenuWindow() {
        windowWidth = 600;
        windowHeight = 500;
        
        canvas = new GameCanvas();
        
        canvas.setPreferredSize(
                new Dimension(windowWidth, windowHeight));
        
        getContentPane().add(canvas, BorderLayout.CENTER);
        
        // Add components here.
        
        setTitle("Menu Window");
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        menu();
        
        setVisible(true);
    }
    
    
    /**
     * Needed for WindowBuilder.
     * @param parent
     */
    @PostConstruct
    public void createControls(Composite parent) {

    } 
    
    public void menu() {
    	    	
    	ImageIcon playButtonIcon = new ImageIcon("res/playButton.png");
    	ImageIcon optionsButtonIcon = new ImageIcon("res/optionsButton.png");
    	
        playButton = new JButton(playButtonIcon);        
        playButton.setBounds(428, 33, 140, 60);
        optionsButton = new JButton(optionsButtonIcon);
        optionsButton.setBounds(428, 109, 140, 60);
        
        playButton.setPreferredSize(new Dimension(140, 60));
        optionsButton.setPreferredSize(new Dimension(140, 60));
        
        textField = new JTextField("", 15);
        textField.setBounds(138, 80, 171, 22);
        canvas.setLayout(null);
              
        canvas.add(playButton);
        canvas.add(optionsButton);
        canvas.add(textField);
        
        JLabel lblYourName = new JLabel("Your name:");
        lblYourName.setBounds(64, 83, 76, 16);
        canvas.add(lblYourName);
        
        JLabel lblDerpvivor = new JLabel("DERPVIVOR");
        lblDerpvivor.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
        lblDerpvivor.setBounds(64, 33, 140, 34);
        canvas.add(lblDerpvivor);
             
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
        	}  	
        });   
    }
}
