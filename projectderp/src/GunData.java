import java.awt.Image;

/**
 * Gun class to store stock gun properties.
 *
 * @author Mikko Jokinen
 * @version 2015.0217
 * @since 1.7
 */
public class GunData {
	private String name;
	private Image img;
	private int frameWidth;
	private int frameHeight;
	private int imageWidth;
	private int imageHeight;
	private int damage;
	private int accuracy;
	private int price;
	private double range;
	private BulletData bulletData;
	
	public GunData (String name, Image img, int frameWidth, int frameHeight,
                    int imageWidth, int imageHeight, int damage, int accuracy,
			        int price, double range, BulletData bulletData) {
		this.name = name;
		this.img = img;
		this.frameWidth = frameWidth;
		this.frameHeight = frameHeight;
		this.imageWidth = imageWidth;
		this.imageHeight = imageHeight;
		this.damage = damage;
		this.accuracy = accuracy;
		this.price = price;
		this.range = range;
		this.bulletData = bulletData;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	public int getFrameWidth() {
		return frameWidth;
	}

	public void setFrameWidth(int frameWidth) {
		this.frameWidth = frameWidth;
	}

	public int getFrameHeight() {
		return frameHeight;
	}

	public void setFrameHeight(int frameHeight) {
		this.frameHeight = frameHeight;
	}

	public int getImageWidth() {
		return imageWidth;
	}

	public void setImageWidth(int imageWidth) {
		this.imageWidth = imageWidth;
	}

	public int getImageHeight() {
		return imageHeight;
	}

	public void setImageHeight(int imageHeight) {
		this.imageHeight = imageHeight;
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