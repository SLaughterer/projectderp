import java.awt.Image;


/**
 * Enemy class for the Game.
 *
 * @author Tero Pykälämäki
 * @version 2015.0217
 * @since 1.7
 */
public class Enemy extends Sprite {
	private int health;
	private int damageModifier;
	private Gun gun;
	
	public Enemy(Image img, int imageWidth, int imageHeight) {
		super(img, imageWidth, imageHeight);
		initialize();
	}
	
	public Enemy(Image img, int frameWidth, int frameHeight, 
            int imageWidth, int imageHeight) {
		super(img, frameWidth, frameHeight, imageWidth, imageHeight);
		initialize();
	}
	
	public void initialize() {
		getGun();
	}
	
    public void getGun() {
    	GunData data = GunManager.armory.get(0);
    	gun = GunManager.requestGun(data);
    }

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getDamageModifier() {
		return damageModifier;
	}

	public void setDamageModifier(int damageModifier) {
		this.damageModifier = damageModifier;
	}
	
	@Override
	public void move() {
		super.move();
		gun.rotation(this.getFacingDirection());
		gun.setX(this.getX());
		gun.setY(this.getY());
		
	}
	
	public void shoot() {
		gun.shoot();
	}
	
}
