/**
 * Hitbox class for the Game. Used to detect collisions.
 *
 * @author Tero Pykälämäki
 * @version 2015.0228
 * @since 1.7
 */
public class Hitbox {
	
	/**
	 * Determines hitbox type.
	 */
	public final static int TYPE_CIRCLE = 0;
	
	/**
	 * Determines hitbox type.
	 */
	public final static int TYPE_RECTANGLE = 1;
	
	/**
	 * Determines hitbox type.
	 */
	private int type;
	
	/**
	 * Radius of the circle-shaped hitbox.
	 */
	private double radius;
	
	/**
	 * Width of the rectangle hitbox.
	 */
	private int width;
	
	/**
	 * Height of the rectangle hitbox.
	 */
	private int height;
	
	/**
	 * Creates a hitbox object. Use for circle type.
	 * 
	 * @param type TYPE_CIRCLE expected.
	 * @param radius Radius of the hitbox.
	 */
	public Hitbox(int type, double radius) {
		this.type = type;
		this.radius = radius;
		
		// Adding values to these allow for alignment methods to
		// work with circle type.
		width = (int) this.radius * 2;
		height = width;
	}
	
	/**
	 * Creates a hitbox object. Use for rectangle type.
	 * 
	 * @param type TYPE_RECTANGLE expected.
	 * @param width Width of the hitbox.
	 * @param height Height of the hitbox.
	 */
	public Hitbox(int type, int width, int height) {
		this.type = type;
		this.width = width;
		this.height = height;
	}
	
	/**
	 * Determines if hitboxes of given Sprites are overlapping each other.
	 * 
	 * @param sprite First sprite with a set hitbox object.
	 * @param sprite2 Second sprite with a set hitbox object.
	 * @return True when overlapping.
	 */
	public static boolean collisionCheck(Sprite sprite, Sprite sprite2) {
		boolean collides = false;
		
		Hitbox hitbox1 = sprite.getHitbox();
		Hitbox hitbox2 = sprite2.getHitbox();
		
		if (hitbox1.getType() == TYPE_CIRCLE && 
				hitbox2.getType() == TYPE_CIRCLE) {
			collides = circleToCircle(sprite, sprite2);
		} else if (hitbox1.getType() == TYPE_CIRCLE && 
				hitbox2.getType() == TYPE_RECTANGLE) {
			collides = circleToRectangle(sprite, sprite2);
		} else if (hitbox1.getType() == TYPE_RECTANGLE && 
				hitbox2.getType() == TYPE_CIRCLE) {
			collides = circleToRectangle(sprite2, sprite);
		} else if (hitbox1.getType() == TYPE_RECTANGLE &&
				hitbox2.getType() == TYPE_RECTANGLE) {
			collides = rectangleToRectangle(sprite, sprite2);
		}
		
		return collides;
	}

	// Assisting functions.
	private static boolean circleToCircle(Sprite sprite, Sprite sprite2) {
		boolean collides = false;
		
		// If within distance.
		if (Sprite.calculateDistance(
				sprite.getAnchorX(), sprite.getAnchorY(), 
				sprite2.getAnchorX(), sprite2.getAnchorY()) 
				< 
				sprite.getHitbox().getRadius() 
				+ sprite2.getHitbox().getRadius() ) {
			collides = true;
		}
		
		return collides;
	}
	
	private static boolean circleToRectangle(Sprite sprite, Sprite sprite2) {
		boolean collides = false;
		
		Hitbox hitbox1 = sprite.getHitbox();
		Hitbox hitbox2 = sprite2.getHitbox();
		
		// If sprite anchor is within sprite2 x and x + width.
		if (verticallyAligned(sprite, sprite2)) {
			
			// If within distance.
			if (hitbox1.getRadius() + hitbox2.getHeight()/2 
					> Sprite.calculateDistance(
							sprite.getAnchorX(), sprite.getAnchorY(), 
							sprite.getAnchorX(), sprite2.getAnchorY())) {
				collides = true;
			}
			
		// If sprite anchor is within sprite2 y and y + height.
		} else if (horizontallyAligned(sprite, sprite2)) {
			
			// If within distance.
			if (hitbox1.getRadius() + hitbox2.getWidth()/2
					> Sprite.calculateDistance(
							sprite.getAnchorX(), sprite.getAnchorY(),
							sprite2.getAnchorX(), sprite.getAnchorY())) {
				collides = true;
			}
		}
		
		return collides;
	}
	
	private static boolean rectangleToRectangle(Sprite sprite, Sprite sprite2) {
		boolean collides = false;
		
		Hitbox hitbox1 = sprite.getHitbox();
		Hitbox hitbox2 = sprite2.getHitbox();
		
		// If sprite anchor is within sprite2 x and x + width.
		if (verticallyAligned(sprite, sprite2)) {
			
			// If within distance.
			if (hitbox1.getHeight()/2 + hitbox2.getHeight()/2 
					> Sprite.calculateDistance(
							sprite.getAnchorX(), sprite.getAnchorY(), 
							sprite.getAnchorX(), sprite2.getAnchorY())) {
				collides = true;
			}
			
		// If sprite anchor is within sprite2 y and y + height.
		} else if (horizontallyAligned(sprite, sprite2)) {
			
			// If within distance.
			if (hitbox1.getWidth()/2 + hitbox2.getWidth()/2
					> Sprite.calculateDistance(
							sprite.getAnchorX(), sprite.getAnchorY(),
							sprite2.getAnchorX(), sprite.getAnchorY())) {
				collides = true;
			}
		}
		
		return collides;
	}
	
	public static boolean verticallyAligned(Sprite sprite, Sprite sprite2) {
		boolean aligned = false;
		
		if (sprite.getAnchorX()
				>= sprite2.getAnchorX() - sprite2.getHitbox().getWidth()/2 
				&& sprite.getAnchorX() 
				< sprite2.getAnchorX() + sprite2.getHitbox().getWidth()/2) {
			aligned = true;
			// System.out.println("vertically " + aligned);
		}
		
		return aligned;
	}
	
	public static boolean horizontallyAligned(Sprite sprite, Sprite sprite2) {
		boolean aligned = false;
		
		if (sprite.getAnchorY()
				>= sprite2.getAnchorY() - sprite2.getHitbox().getHeight()/2
				&& sprite.getAnchorY()
				< sprite2.getAnchorY() + sprite2.getHitbox().getHeight()/2) {
			aligned = true;
			// System.out.println("horizontally " + aligned);
		}
		
		return aligned;
	}
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
