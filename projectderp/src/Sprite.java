/**
 * Sprite for the Game Engine.
 *
 * @author Tero Pykälämäki
 * @version 2014.1217
 * @since 1.7
 */
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.imageio.ImageIO;

class Sprite {
    
    /**
     * X coordinate for Sprite's upper-left corner.
     */
    private int posX;
    
    /**
     * Y coordinate for Sprite's upper-left corner.
     */
    private int posY;
    
    /**
     * Centered X coordinate calculated from image given to Sprite.
     */
    private int anchorX;
    /**
     * Centered Y coordinate calculated from image given to Sprite.
     */
    private int anchorY;
    
    /**
     * Sprite's traversal speed.
     */
    private int movementSpeed;
	
    /**
     * Sprite's traversal direction in degrees. (0 - 359.999...)
     */
    private int movementDirection;
	
    /**
     * The direction that the Sprite is looking towards/image rotation.
     */
    private int facingDirection;
    
    /**
     * Horizontal movement speed. Used with move().
     */
    private int movementX;
    
    /**
     * Vertical movement speed. Used with move().
     */
    private int movementY;
    
    /**
     * Single frame's width and therefore Sprite's width.
     */
    private int frameWidth;
    
    /**
     * Single frame's height and therefore Sprite's height.
     */
    private int frameHeight;
    
    /**
     * Index of the frame being shown.
     */
    private int frame;
    
    /**
     * The amount of frames this Sprite holds.
     */
    private int maxFrames;
    
    /**
     * PNG or JPG image given to Sprite.
     */
    private String image;
    
    /**
     * Width of the image given to Sprite.
     */
    private int imageWidth;
    
    /**
     * Height of the image given to Sprite.
     */
    private int imageHeight;
    
    /**
     * Determines if the image should be shown mirrored.
     */
    private boolean reversedImage;
    
    /**
     * Determines the size of the Sprite.
     */
    private double scale;
    
    /**
     * Determines if Sprite is horizontally mirrored.
     */
    private int flip;
    
    /**
     * Calculational aid value used in flipping the image.
     */
    private int flipValue;
    
    /**
     * Determines if the Sprite's position needs correcting.
     */
    private int flipReposition;
    
    /**
     * Handles rotation and flipping of Image.
     */
    private AffineTransform tr;
    
    /**
     * Determines the angle of the image rotation in radians.
     */
    private double rotation;

    private BufferedImage imgB;
    private BufferedImage[] imgSub;
    private Hitbox hitbox;
    private boolean isDead;
     
    /**
     * Creates a non-animated Sprite.
     *
     * @param img Image depicting whatever the Sprite is intended to be.
     * @param imageWidth The width of parameter img.
     * @param imageHeight The height of parameter img.
     */
    public Sprite(String img, int imageWidth, int imageHeight) {
    	frameWidth = imageWidth;
        frameHeight = imageHeight;
        image = img;
    	initialize(imageWidth, imageHeight);        
        maxFrames = 1;
        createFrames();
    }
    
    /**
     * Creates a (possibly animated) Sprite.
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
    public Sprite(String img, int frameWidth, int frameHeight, 
                            int imageWidth, int imageHeight) {
    	this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        image = img;
    	initialize(imageWidth, imageHeight);
        calculateFrames();
        createFrames();
    }
    
    /**
     * Creates a (possibly animated) Sprite.
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
    public Sprite(BufferedImage img, int frameWidth, int frameHeight, 
                            int imageWidth, int imageHeight) {
    	this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
    	initialize(imageWidth, imageHeight);
        calculateFrames();
        imgB = img;
        createFrames();
    }
    
    /**
     * Initializes many of the Sprites attributes.
     *
     * @param img Image depicting whatever the Sprite is intended to be.
     * @param imageWidth The width of parameter img.
     * @param imageHeight The height of parameter img.
     */
    private void initialize(int imageWidth, int imageHeight) {
        posX = 0;
        posY = 0;
        anchorX = frameWidth / 2;
        anchorY = frameHeight / 2;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
        frame = 0;
        reversedImage = false;
        scale = 1.0;
        flip = 0;
        flipValue = 1;
        flipReposition = 0;
        tr = new AffineTransform();
        rotation(0);
        hitbox = new Hitbox(Hitbox.TYPE_CIRCLE, frameWidth / 2);
    }

    private void createFrames() {
    	imgSub = new BufferedImage[maxFrames];
    	int frameX = 0;
    	int frameY = 0;
    	
    	try {
			if (imgB == null) {
				imgB = ImageIO.read(new FileInputStream(new File(image) ));
			}
			
			for (int i = 0; i < maxFrames; i++) {
				
				imgSub[i] = imgB.getSubimage(frameX, frameY, frameWidth, frameHeight);
				frameX += frameWidth;
				
				if (frameX == imageWidth) {
					frameX = 0;
					frameY += frameHeight;
				}
				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public static double calculateDistance(int startX, int startY, int endX, int endY) {
    	double distance;
    	
    	distance = Math.pow(endX - startX, 2) + Math.pow(endY - startY, 2);
    	distance = Math.sqrt(distance);
    	
    	return distance;
    }
    
    public static double calculateDirection(
    		int startX, int startY, int endX, int endY) {
    	double direction = -1; 
    	
    	direction = (Math.atan2(endY - startY, endX - startX) * 180.0 / Math.PI);
    	
    	return direction + 90;
    }
    
    public static double calculateDirection(int startX, int startY, Point endPoint) {
    	int endX = endPoint.x;
    	int endY = endPoint.y;
    	
    	return calculateDirection(startX, startY, endX, endY);
    }
    
    /**
     * Calculates the amount of frames the newly created Sprite contains.
     *
     * Throws InvalidFrameException if frame width multiplied with frame count
     * doesn't result in image width.
     *
     * @throws InvalidFrameException
     */
    private void calculateFrames() {
    	if (imageWidth % frameWidth == 0 && imageHeight % frameHeight == 0) {
            maxFrames = imageWidth / frameWidth;
            maxFrames = maxFrames * imageHeight / frameHeight;
        } else {
            throw new InvalidFrameException("Image width/height divided with frame " +
                                    "width/height must result in an Integer value.");
        }
    }
    
    public void rotate(int value) {
    	int direction = facingDirection + value;
    	rotation(direction);
    }
    
    /**
     * Sets the angle the Sprite is shown at.
     * 
     * The rotational radian value is calculated from the given parameter.
     * The parameter should have a degree value for accuracy.
     * 
     * @param degrees Determines the angle of Sprite.
     */
    public void rotation(double degrees) {
    	degrees = checkDegrees(degrees);
    	setFacingDirection(degrees);
    	rotation = (degrees * flipValue) * Math.PI / 180;
    }
    
    /**
     * Flips the Sprite's image horizontally.
     */
    public void flip() {
    	if (flip != 0) {
    		flipValue = -1;
    		flipReposition = frameWidth;
    	} else {
    		flipValue = 1;
    		flipReposition = 0;
    	}
    }

    /**
     * Paints the Sprite.
     *
     * @param g Currently active Graphics object.
     */
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
    	
        tr.setTransform(flipValue, 0, 0, 1, flipReposition+posX, 0+posY);
        tr.rotate(rotation, frameWidth/2, frameHeight/2);
        
        g2d.drawImage(imgSub[frame], tr, null);
    }
    
    /**
     * Determines if Sprite is touching another Sprite.
     *
     * @param sprite The other Sprite that this one is checked against.
     * @return True when the Sprites are touching each other.
     */
    public boolean collidesWith(Sprite sprite) {
        boolean collides = false;
        
        if (sprite != this) {
        	collides = Hitbox.collisionCheck(this, sprite);
        }
        
        return collides;
    }
    
    /**
     * Changes the Sprite's movement speed and direction.
     *
     * @param directionX Horizontal movement speed.
     * @param directionY Vertical movement speed.
     */
    public void changeMovement(int directionX, int directionY) {
        movementX = directionX;
        movementY = directionY;
    }
    
    /**
     * Calculates movementX and -Y values from movementDirection.
     * 
     * Limits directional movement to 8 compass directions.
     */
    private void calculateMovement() {
    	movementY = (int)(movementSpeed * Math.sin(Math.toRadians(movementDirection - 90)));
    	movementX = (int)(movementSpeed * Math.cos(Math.toRadians(movementDirection - 90)));
    }
    
    /**
     * Adjusts the Sprite's coordinate position according to set values.
     */
    public void move() {
        posX += movementX;
        posY += movementY;
        anchorX += movementX;
        anchorY += movementY;
    }
    
    /**
     * Moves Sprite by the amount parameter values determine.
     *
     * @param directionX Horizontal movement in pixels.
     * @param directionY Vertical movement in pixels.
     */
    public void move(int directionX, int directionY) {
        posX += directionX;
        posY += directionY;
        anchorX += directionX;
        anchorY += directionY;
    }
    
    /**
     * Switches the value of reversedImage attribute.
     *
     * Determines whether the current frame should be shown as a mirror-image.
     */
    public void reverseFrame() {
        if (reversedImage) {
            reversedImage = false;
        } else {
            reversedImage = true;
        }
    }
    
    /**
     * Changes current frame to the next one.
     *
     * If current frame is the last one, current frame is reset.
     */
    public void nextFrame() {
        if (frame < maxFrames - 1) {
            frame++;
        } else {
            frame = 0;
        }
    }
    
    /**
     * Sets current frame directly if given parameter < frame count.
     *
     * @param newFrame Index of the desired frame.
     */
    public void setFrame(int newFrame) {
        if (newFrame < maxFrames) {
            frame = newFrame;
        }
    }
    
    /**
     * Returns current frame.
     *
     * @return current frame.
     */
    public int getFrame() {
        return frame;
    }

    public int getFrameWidth() {
    	return frameWidth;
    }
    
    public int getFrameHeight() {
    	return frameHeight;
    }
    
    /**
     * Returns frame count.
     *
     * @return The amount frames the Sprite has.
     */
    public int getMaxFrames() {
        return maxFrames;
    }

    public BufferedImage getImage() {
    	return imgSub[frame];
    }
    
    public void setHitbox(Hitbox hitbox) {
    	this.hitbox = hitbox;
    }
    
    public Hitbox getHitbox() {
    	return hitbox;
    }
    
    /**
     * Sets Sprite's x-coordinate.
     *
     * @param newX Sprite's new horizontal position.
     */
    public void setX(int newX) {
        posX = newX;
        anchorX = posX + frameWidth / 2;
    }
    
    /**
     * Returns x-coordinate.
     *
     * @return Sprite's horizontal coordinate.
     */
    public int getX() {
        return posX;
    }
    
    /**
     * Sets Sprite's y-coordinate.
     *
     * @param newY Sprite's new vertical position.
     */
    public void setY(int newY) {
        posY = newY;
        anchorY = posY + frameHeight / 2;
    }
    
    /**
     * Returns y-coordinate.
     *
     * @return Sprite's vertical coordinate.
     */
    public int getY() {
        return posY;
    }
    
    public int getAnchorX() {
		return anchorX;
	}

	public void setAnchorX(int anchorX) {
		this.anchorX = anchorX;
	}

	public int getAnchorY() {
		return anchorY;
	}

	public void setAnchorY(int anchorY) {
		this.anchorY = anchorY;
	}

	public int getMovementSpeed() {
		return movementSpeed;
	}

	public void setMovementSpeed(int movementSpeed) {
		this.movementSpeed = movementSpeed;
	}

	public int getMovementDirection() {
		return movementDirection;
	}

	public void setMovementDirection(int degrees) {
		this.movementDirection = (int) checkDegrees(degrees);
		calculateMovement();
	}

	public int getFacingDirection() {
		return facingDirection;
	}
	
	public void setFacingDirection(double degrees) {
		facingDirection = (int) checkDegrees(degrees);
	}
	
	private double checkDegrees(double degrees) {
		while(degrees < 0) {
			degrees = degrees + 360;
		}
		
		while(degrees > 360) {
			degrees = degrees - 360;
		}
		
		return degrees;
	}
	
	/**
     * Changes the Sprite's size.
     *
     * @param scale The scaling value for the Sprite.
     */
    public void setScale(double scale) {
        if (scale >= 0.1) {
            this.scale = scale;
        }
    }
    
    /**
     * Returns the scaling value of the Sprite.
     *
     * @return Sprite's scaling value.
     */
    public double getScale() {
        return scale;
    }
    
    /**
     * Changes horizontal movement speed.
     *
     * @param directionX New horizontal movement speed and direction.
     */
    public void setMovementX(int directionX) {
        movementX = directionX;
    }
    
    /**
     * Returns horizontal movement speed and direction value.
     *
     * @return Sprite's horizontal speed and direction.
     */
    public int getMovementX() {
        return movementX;
    }
    
    /**
     * Changes vertical movement speed.
     *
     * @param directionY New vertical movement speed and direction.
     */
    public void setMovementY(int directionY) {
        movementY = directionY;
    }
    
    /**
     * Returns vertical movement speed and direction value.
     *
     * @return Sprite's vertical speed and direction.
     */
    public int getMovementY() {
        return movementY;
    }
    
    /**
     * Returns the Sprite's width.
     *
     * @return Sprite's width determined by frame-size.
     */
    public int getWidth() {
        return frameWidth;
    }
    
    /**
     * Returns the Sprite's height.
     *
     * @return Sprite's height determined by frame-size.
     */
    public int getHeight() {
        return frameHeight;
    }

	public boolean isDead() {
		return isDead;
	}

	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}
}