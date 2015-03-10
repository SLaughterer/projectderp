import java.awt.Image;
import java.awt.Point;


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
	private Point lastKnownPos;
	private boolean moveRandomly;
	private boolean playerInSight;
	
	public Enemy(String img, int imageWidth, int imageHeight) {
		super(img, imageWidth, imageHeight);
		initialize();
	}
	
	public Enemy(String img, int frameWidth, int frameHeight, 
            int imageWidth, int imageHeight) {
		super(img, frameWidth, frameHeight, imageWidth, imageHeight);
		initialize();
	}
	
	public void initialize() {
		getGun();
		moveRandomly = true;
	}
	
	public void moveTowardsLastKnownPos() {
		int distance = (int) Sprite.calculateDistance(
				getAnchorX(), getAnchorY(), 
				(int) lastKnownPos.getX(), (int) lastKnownPos.getY());
		int direction = (int) Sprite.calculateDirection(
				getAnchorX(), getAnchorY(), lastKnownPos);
		
		rotation(direction);
		setMovementDirection(direction);
		move();
		
		if (distance <= 20) {
			moveRandomly = true;
		}
	}
	
    public Point getLastKnownPos() {
		return lastKnownPos;
	}

	public void setLastKnownPos(Point lastKnownPos) {
		this.lastKnownPos = lastKnownPos;
	}

	public boolean isMoveRandomly() {
		return moveRandomly;
	}

	public void setMoveRandomly(boolean moveRandomly) {
		this.moveRandomly = moveRandomly;
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

	public void AlterHealth(int value) {
		health += value;
	}
	
	public int getDamageModifier() {
		return damageModifier;
	}

	public void setDamageModifier(int damageModifier) {
		this.damageModifier = damageModifier;
	}
	
	public boolean isPlayerInSight() {
		return playerInSight;
	}

	public void setPlayerInSight(boolean playerInSight) {
		this.playerInSight = playerInSight;
	}

	public void moveGun() {
		gun.rotation(this.getFacingDirection());
		gun.setX(this.getX());
		gun.setY(this.getY());
		
	}
	
	public void shoot() {
		gun.shoot(Bullet.ENEMY);
	}
	
}
