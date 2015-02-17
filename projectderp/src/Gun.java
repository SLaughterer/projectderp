import java.awt.Image;
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
	private double range;
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
		random = new Random();
	}
	
	public Bullet newBullet() {
		//laske damage
		//laske direction
		int direction = (int) (super.getMovementDirection() + 
				        (random.nextInt(91) - 45) * (100.0 - accuracy) / 100.0);
		Bullet bullet = new Bullet(damage, direction, range, bulletData);
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
