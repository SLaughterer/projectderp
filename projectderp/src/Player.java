import java.awt.Image;

/**
 * Player class for the Game.
 *
 * @author Tero Pykälämäki
 * @version 2015.0128
 * @since 1.7
 */
public class Player extends Sprite {
	
	/**
	 * Determines how quickly player moves.
	 */
	//private int movementSpeed; Sprite has this attribute.
	
	/**
	 * Determines Player-object's initial movement speed.
	 */
	public final static int DEFAULT_SPEED = 5;
	
	public final static int UP = 0;
	public final static int RIGHT = 1;
	public final static int DOWN = 2;
	public final static int LEFT = 3;
	
	public int health = 100;
	
	/**
     * Creates a non-animated Player sprite.
     *
     * @param img Image depicting whatever the Sprite is intended to be.
     * @param imageWidth The width of parameter img.
     * @param imageHeight The height of parameter img.
     */
    public Player(Image img, int imageWidth, int imageHeight) {
        super(img, imageWidth, imageHeight);
        
        initializePlayer();
    }
    
    /**
     * Creates a (possibly animated) Player sprite.
     *
     * Multiplying frame width with frame count should result in image width.
     * Note: Currently frame height should be the same as image height.
     *
     * @param img Image depicting whatever the Sprite is intended to be.
     * @param frameWidth The width of a single frame in parameter img.
     * @param frameHeight The height of a single frame in parameter img.
     * @param imageWidth The width of parameter img.
     * @param imageHeight The height of parameter img.
     */
    public Player(Image img, int frameWidth, int frameHeight, 
                            int imageWidth, int imageHeight) {
        super(img, frameWidth, frameHeight, imageWidth, imageHeight);
        
        initializePlayer();
    }
    
    /**
     * Initializes Player-object's attributes.
     */
    private void initializePlayer() {
        super.setMovementSpeed(Player.DEFAULT_SPEED);
    }
    
    /**
     * Moves Player-sprite if correct keys are pressed.
     * 
     * See Keybindings and GameKeyListener for more information.
     * 
     * @param keyCodeX Horizontal movement keybinding code.
     * @param keyCodeY Vertical movement keybinding code.
     */
    @Override
    public void move(int keyCodeX, int keyCodeY) {
    	int movementX = 0;
    	int movementY = 0;
    	
    	if(keyCodeY == Keybindings.UP || 
    			keyCodeY == Keybindings.UP_ALT) {
    		movementY = super.getMovementSpeed() * -1;
    	} else if(keyCodeY == Keybindings.DOWN || 
    			keyCodeY == Keybindings.DOWN_ALT) {
    		movementY = super.getMovementSpeed();
    	}
    	
    	if(keyCodeX == Keybindings.RIGHT || 
    			keyCodeX == Keybindings.RIGHT_ALT) {
    		movementX = super.getMovementSpeed();
    	} else if(keyCodeX == Keybindings.LEFT || 
    			keyCodeX == Keybindings.LEFT_ALT) {
    		movementX = super.getMovementSpeed() * -1;
    	}
    	
    	super.move(movementX, movementY);
    }
    
    public void moves(int value) {
    	if (value == UP) {
    		setMovementY(getMovementSpeed() * -1);
    	} else if (value == RIGHT) {
    		setMovementX(getMovementSpeed());
    	} else if (value == DOWN) {
    		setMovementY(getMovementSpeed());
    	} else if (value == LEFT) {
    		setMovementX(getMovementSpeed() * -1);
    	}
    	
    	//move();
    }
    
    public void stopVerticalMovement() {
    	setMovementY(0);
    }
    
    public void stopHorizontalMovement() {
    	setMovementX(0);
    }
    
    public void setHealth(int newHealth) {
    	
    	health = newHealth;
    }
    
    public int getHealth() {
    	
    	return health;
    }
    
    public void changeHealthBy(int damage) {
    	
    	health += damage;
    	
    	if (health < 0) {
    		health = 0;
    	}
    }
}
