import java.awt.Image;
import java.awt.Toolkit;

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
	private int range;
	private BulletData bulletData;
	
	public GunData (String[] data) {
		this.name = data[0];
		this.img = Toolkit.getDefaultToolkit().createImage(data[1]);
		this.frameWidth = Integer.parseInt(data[2]);
		this.frameHeight = Integer.parseInt(data[3]);
		this.imageWidth = Integer.parseInt(data[4]);
		this.imageHeight = Integer.parseInt(data[5]);
		this.damage = Integer.parseInt(data[6]);
		this.accuracy = Integer.parseInt(data[7]);
		this.price = Integer.parseInt(data[8]);
		this.range = Integer.parseInt(data[9]);
		this.bulletData = new BulletData(Toolkit.getDefaultToolkit().createImage(data[10]));
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

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public BulletData getBulletData() {
		return bulletData;
	}

	public void setBulletData(BulletData bulletData) {
		this.bulletData = bulletData;
	}
	
}