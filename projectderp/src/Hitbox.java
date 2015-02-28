
public class Hitbox {
	public final static int TYPE_CIRCLE = 0;
	public final static int TYPE_RECTANGLE = 1;
	private int type;
	private double radius;
	private int width;
	private int height;
	
	public Hitbox(int type, double radius) {
		this.type = type;
		this.radius = radius;
	}
	
	public Hitbox(int type, int width, int height) {
		this.type = type;
		this.width = width;
		this.height = height;
	}
	
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
		if (sprite.getAnchorX()
				>= sprite2.getAnchorX() - hitbox2.getWidth()/2 
				&& sprite.getAnchorX() 
				< sprite2.getAnchorX() + hitbox2.getWidth()/2) {
			
			// If within distance.
			if (hitbox1.getRadius() + hitbox2.getHeight()/2 
					> Sprite.calculateDistance(
							sprite.getAnchorX(), sprite.getAnchorY(), 
							sprite.getAnchorX(), sprite2.getAnchorY())) {
				collides = true;
			}
			
		// If sprite anchor is within sprite2 y and y + height.
		} else if (sprite.getAnchorY()
				>= sprite2.getAnchorY() - hitbox2.getHeight()/2
				&& sprite.getAnchorY()
				< sprite2.getAnchorY() + hitbox2.getHeight()/2) {
			
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
		if (sprite.getAnchorX()
				>= sprite2.getAnchorX() - hitbox2.getWidth()/2 
				&& sprite.getAnchorX() 
				< sprite2.getAnchorX() + hitbox2.getWidth()/2) {
			
			// If within distance.
			if (hitbox1.getHeight()/2 + hitbox2.getHeight()/2 
					> Sprite.calculateDistance(
							sprite.getAnchorX(), sprite.getAnchorY(), 
							sprite.getAnchorX(), sprite2.getAnchorY())) {
				collides = true;
			}
			
		// If sprite anchor is within sprite2 y and y + height.
		} else if (sprite.getAnchorY()
				>= sprite2.getAnchorY() - hitbox2.getHeight()/2
				&& sprite.getAnchorY()
				< sprite2.getAnchorY() + hitbox2.getHeight()/2) {
			
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
