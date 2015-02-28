
public class Hitbox {
	public final static int TYPE_CIRCLE = 0;
	public final static int TYPE_SQUARE = 1;
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
		
		if (hitbox1.getType() == TYPE_CIRCLE && hitbox2.getType() == TYPE_CIRCLE) {
			if (Sprite.calculateDistance(
					sprite.getAnchorX(), sprite.getAnchorY(), 
					sprite2.getAnchorX(), sprite2.getAnchorY()) 
					< 
					hitbox1.getRadius() + hitbox2.getRadius() ) {
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
