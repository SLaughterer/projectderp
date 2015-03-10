import java.util.ArrayList;


public class Wall extends Sprite {
	ArrayList<Ray> hitboxes;
	
	public Wall(String img, int frameWidth, int frameHeight, int imageWidth,
			int imageHeight) {
		super(img, frameWidth, frameHeight, imageWidth, imageHeight);
		hitboxes = new ArrayList<Ray>();
	}

	// collision check for each ray
	@Override
	public boolean collidesWith(Sprite sprite) {
		boolean collides = false;
		
		for (int i = 0; i < hitboxes.size(); i++) {
			if (Hitbox.collisionCheck(sprite, hitboxes.get(i))) {
				collides = true;
				break;
			}
		}
		
		return collides;
	}
	
	// addhitbox adds new ray object with new hitbox
	public void addHitbox(Hitbox hitbox, int posX_offset, int posY_offset) {
		Ray ray = new Ray();
		ray.setX(getAnchorX() + posX_offset);
		ray.setY(getAnchorY() + posY_offset);
		ray.setHitbox(hitbox);
		hitboxes.add(ray);
	}
	
	public ArrayList getHitboxes() {
		return hitboxes;
	}
}
