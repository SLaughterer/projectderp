import java.awt.Image;

/**
 * Bullet class to store bullet image.
 *
 * @author Mikko Jokinen
 * @version 2015.0217
 * @since 1.7
 */
public class BulletData {
	private Image img;
	private int frameWidth = 64;
	private int frameHeight = 64;
	private int imageWidth = 64;
	private int imageHeight = 64;
	
	public BulletData(Image img) {
		this.img = img;
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
}
