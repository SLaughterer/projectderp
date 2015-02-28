/**
 * Bullet class to store individual bullet properties.
 *
 * @author Mikko Jokinen
 * @version 2015.0217
 * @since 1.7
 */
public class Bullet extends Sprite {
	private int damage;
	private int range;
	
	public Bullet(int damage, int direction, int range, BulletData data, int x, int y) {
		super(data.getImg(), data.getFrameWidth(), data.getFrameHeight(),
			  data.getImageWidth(), data.getImageHeight());
		// TODO Auto-generated constructor stub
		this.damage = damage;
		setMovementSpeed(20);
		rotation(direction);
		setMovementDirection(direction);
		this.range = range;
		this.setX(x);
		this.setY(y);
	}	
}
