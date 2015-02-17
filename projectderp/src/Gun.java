import java.awt.Image;

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
	private double accuracy;
	private int price;
	private double range;
	private BulletData bulletData;
	
	public Gun(GunData data) {
		super(data.getImg(), data.getFrameWidth(), data.getFrameHeight(),
			  data.getImageWidth(), data.getImageHeight());
		this.name = data.getName();
		this.damage = data.getDamage();
		this.accuracy = data.getAccuracy();
		this.price = data.getPrice();
		this.range = data.getRange();
		this.bulletData = data.getBulletData();
	}
	
	public Bullet newBullet() {
		//laske damage
		//laske direction
		Bullet bullet = new Bullet(damage, super.getMovementDirection(), range, bulletData);
		return bullet;
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
	public double getAccuracy() {
		return accuracy;
	}
	public void setAccuracy(double accuracy) {
		this.accuracy = accuracy;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

	public double getRange() {
		return range;
	}

	public void setRange(double range) {
		this.range = range;
	}

	public BulletData getBulletData() {
		return bulletData;
	}

	public void setBulletData(BulletData bulletData) {
		this.bulletData = bulletData;
	}
}
