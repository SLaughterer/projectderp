import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 * Bullet class to store bullet image.
 *
 * @author Mikko Jokinen
 * @version 2015.0217
 * @since 1.7
 */
public class BulletData {
	private BufferedImage img;
	private int frameWidth = 7;
	private int frameHeight = 7;
	private int imageWidth = 7;
	private int imageHeight = 7;
	
	public BulletData(BufferedImage img) {
		this.img = img;
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
}
