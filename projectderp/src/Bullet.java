/**
 * Bullet class to store individual bullet properties.
 *
 * @author Mikko Jokinen
 * @version 2015.0217
 * @since 1.7
 */
public class Bullet extends Sprite {
	public final static int PLAYER = 0;
	public final static int ENEMY = 1;
	private int damage;
	private int range;
	private int shooter;
	
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
	
	public void setShooter(int shooter) {
		this.shooter = shooter;
	}
	
	public int getShooter() {
		return shooter;
	}
}
