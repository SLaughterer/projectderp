import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Gun class to store stock gun properties.
 *
 * @author Mikko Jokinen
 * @version 2015.0217
 * @since 1.7
 */
public class GunData {
	private String name;
	private BufferedImage img;
	private int frameWidth;
	private int frameHeight;
	private int imageWidth;
	private int imageHeight;
	private int damage;
	private int accuracy;
	private int price;
	private int range;
	private int roundsPerMinute;
	private int clipSize;
	private int reloadTime;
	private BulletData bulletData;
	
	public GunData (String[] data) throws FileNotFoundException, IOException {
		this.name = data[0];
		this.img = ImageIO.read(new FileInputStream(new File(data[1])));
		this.frameWidth = Integer.parseInt(data[2]);
		this.frameHeight = Integer.parseInt(data[3]);
		this.imageWidth = Integer.parseInt(data[4]);
		this.imageHeight = Integer.parseInt(data[5]);
		this.damage = Integer.parseInt(data[6]);
		this.accuracy = Integer.parseInt(data[7]);
		this.price = Integer.parseInt(data[8]);
		this.range = Integer.parseInt(data[9]);
		this.roundsPerMinute = Integer.parseInt(data[10]);
		this.clipSize = Integer.parseInt(data[11]);
		this.reloadTime = Integer.parseInt(data[12]);
		this.bulletData = new BulletData(
				ImageIO.read(new FileInputStream(new File(data[13]))));
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public BufferedImage getImg() {
		return img;
	}

	public void setImg(BufferedImage img) {
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