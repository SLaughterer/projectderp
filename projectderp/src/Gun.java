import java.util.Random;

/**
 * Gun class to store individual gun properties.
 *
 * @author Mikko Jokinen
 * @version 2015.0217
 * @since 1.7
 */
public class Gun extends Sprite {
	private String name;
	private int damage;
	private int accuracy;
	private int price;
	private int range;
	private int roundsPerMinute;
	private int cooldown;
	private int clipSize;
	private int reloadTime;
	private int bulletsInClip;
	private BulletData bulletData;
	private Random random;
	
	public Gun(GunData data) {
		super(data.getImg(), data.getFrameWidth(), data.getFrameHeight(),
			  data.getImageWidth(), data.getImageHeight());
		this.name = data.getName();
		this.damage = data.getDamage();
		this.accuracy = data.getAccuracy();
		this.price = data.getPrice();
		this.range = data.getRange();
		this.bulletData = data.getBulletData();
		this.roundsPerMinute = data.getRoundsPerMinute();
		this.clipSize = data.getClipSize();
		this.reloadTime = data.getReloadTime();
		bulletsInClip = clipSize;
		random = new Random();
	}
	
	public void shoot(int shooter) {
		 if (bulletsInClip == 0) {
			initializeReload();
		} else if (cooldown == 0) {
			BulletManager.addBullet(newBullet(shooter));
			initializeCooldown();
			bulletsInClip--;
		}
	}
	
	private Bullet newBullet(int shooter) {
		//laske damage
		int direction = (int) (getFacingDirection() + 
				        (random.nextInt(91) - 45) * (100.0 - accuracy) / 100.0);
		Bullet bullet = new Bullet(damage, direction, range, bulletData, getX(), getY());
		bullet.setShooter(shooter);
		bullet.setHitbox(new Hitbox(Hitbox.TYPE_CIRCLE, 2));
		return bullet;
	}
	
	private void initializeCooldown() {
		cooldown = 30 / (roundsPerMinute / 60);
	}
	
	private void initializeReload() {
		cooldown = 30 * reloadTime;
		bulletsInClip = clipSize;
	}
	
	public void cool() {
		if (cooldown > 0) {
			cooldown--;
		}
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getDamage() {
		return damage;
	}
	
	public void setDamage(int damage) {
		this.damage = damage;
	}
	public int getAccuracy() {
		return accuracy;
	}
	public void setAccuracy(int accuracy) {
		this.accuracy = accuracy;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public int getRoundsPerMinute() {
		return roundsPerMinute;
	}

	public void setRoundsPerMinute(int roundsPerMinute) {
		this.roundsPerMinute = roundsPerMinute;
	}

	public int getClipSize() {
		return clipSize;
	}

	public void setClipSize(int clipSize) {
		this.clipSize = clipSize;
	}

	public int getReloadTime() {
		return reloadTime;
	}

	public void setReloadTime(int reloadTime) {
		this.reloadTime = reloadTime;
	}

	public BulletData getBulletData() {
		return bulletData;
	}

	public void setBulletData(BulletData bulletData) {
		this.bulletData = bulletData;
	}
}
