import java.awt.Image;

/**
 * Bullet class to store individual bullet properties.
 *
 * @author Mikko Jokinen
 * @version 2015.0217
 * @since 1.7
 */
public class Bullet extends Sprite {
	private int damage;
	private double range;
	
	public Bullet(int damage, int direction, double range, BulletData data) {
		super(data.getImg(), data.getFrameWidth(), data.getFrameHeight(),
			  data.getImageWidth(), data.getImageHeight());
		// TODO Auto-generated constructor stub
		this.damage = damage;
		super.rotation(direction);
		this.range = range;
	}	
}
