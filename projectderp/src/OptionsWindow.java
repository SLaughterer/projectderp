
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
     * Constructs the window used in the game engine.
     */

    private JButton menuButton;
    
    public OptionsWindow() {
        windowWidth = 600;
        windowHeight = 500;
        canvas = new GameCanvas();
        
        canvas.setPreferredSize(
                new Dimension(windowWidth, windowHeight));
        
        add(canvas);

        // Add components here.

        setTitle("Options Window");
        pack();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
     
        options();
     
        setVisible(true);
    }
    
    public void options() {
    	
        JLabel title = new JLabel("OPTIONS");
        title.setPreferredSize(new Dimension(200, 50));
        title.setFont (title.getFont ().deriveFont (32.0f));
        canvas.add(title);
        
    	ImageIcon menuButtonIcon = new ImageIcon("res/menuButton.png");
    	
    	menuButton = new JButton(menuButtonIcon);        
    	menuButton.setPreferredSize(new Dimension(140, 60));  
        canvas.add( menuButton);

        menuButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		dispose();
        	}  	
        });     
        
        JLabel spacing = new JLabel();
        // title.setPreferredSize(new Dimension(50, 50));
        canvas.add(spacing);
        
        // slider template, could be used in some setting
        final int MIN = 0;
        final int MAX = 30;
        final int INIT = 15;

        JSlider sliderTemplate = new JSlider(JSlider.HORIZONTAL, MIN, MAX, INIT);
        
        sliderTemplate.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
            	
                JSlider source = (JSlider)e.getSource();
                
                if (!source.getValueIsAdjusting()) {
                    int fps = (int)source.getValue();
                    
                    if (fps == MIN) {
                        System.out.println("Testing sliderMin");
                    } else if (fps == INIT) {
                    	System.out.println("Testing sliderInit");
                    } else if (fps == MAX) {
                    	System.out.println("Testing sliderMax");
                    } else {
                    	System.out.println("Testing slider whaa");
                    }
                }
            }
        });
        
        canvas.add(sliderTemplate);

        //Turn on labels at major tick marks.
        sliderTemplate.setMajorTickSpacing(15);
        sliderTemplate.setMinorTickSpacing(2);
        sliderTemplate.setPaintTicks(true);
        sliderTemplate.setPaintLabels(true);
    }
}
