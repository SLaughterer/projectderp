
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
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
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
     * Constructs the window used in the game engine.
     */

    private JButton menuButton;
    
    public OptionsWindow() {
        windowWidth = 600;
        windowHeight = 500;
        canvas = new GameCanvas();
        
        canvas.setPreferredSize(
                new Dimension(windowWidth, windowHeight));
        
        getContentPane().add(canvas, BorderLayout.SOUTH);

        // Add components here.

        setTitle("Options Window");
        pack();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
     
        options();
     
        setVisible(true);
    }
    
    public void options() {
        canvas.setLayout(null);
    	
        JLabel title = new JLabel("OPTIONS");
        title.setBounds(238, 13, 134, 50);
        title.setPreferredSize(new Dimension(200, 50));
        title.setFont (title.getFont ().deriveFont (32.0f));
        canvas.add(title);
        
    	ImageIcon menuButtonIcon = new ImageIcon("res/menuButton.png");
    	
    	menuButton = new JButton(menuButtonIcon);        
    	menuButton.setBounds(12, 13, 140, 60);
    	menuButton.setPreferredSize(new Dimension(140, 60));  
        canvas.add( menuButton);

        menuButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		dispose();
        	}  	
        });     
        
        JLabel spacing = new JLabel();
        spacing.setBounds(372, 35, 0, 0);
        // title.setPreferredSize(new Dimension(50, 50));
        canvas.add(spacing);
        
        // slider template, could be used in some setting
        final int MIN = 0;
        final int MAX = 30;
        final int INIT = 15;

        JSlider sliderTemplate = new JSlider(JSlider.HORIZONTAL, MIN, MAX, INIT);
        sliderTemplate.setBounds(206, 123, 200, 52);
        
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
        
        JLabel lblSlidertemplate = new JLabel("SliderTemplate");
        lblSlidertemplate.setBounds(266, 94, 106, 16);
        canvas.add(lblSlidertemplate);
        
        JRadioButton rdbtnRadio = new JRadioButton("RADIO BUTTON");
        rdbtnRadio.setBounds(206, 219, 127, 25);
        canvas.add(rdbtnRadio);
        
        JCheckBox chckbxCheckbox = new JCheckBox("CHECKBOX1");
        chckbxCheckbox.setBounds(206, 260, 113, 25);
        canvas.add(chckbxCheckbox);
        
        JCheckBox chckbxCheckbox_1 = new JCheckBox("CHECKBOX2");
        chckbxCheckbox_1.setBounds(206, 301, 113, 25);
        canvas.add(chckbxCheckbox_1);
    }
}
