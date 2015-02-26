import java.awt.Graphics;
import java.util.LinkedList;

/**
 * All the bullets are stored and manipulated from here.
 *
 * @author Mikko Jokinen
 * @version 2015.0217
 * @since 1.7
 */
public class BulletManager {
	private static LinkedList<Bullet> bullets = new LinkedList<Bullet>();
	
	public static void addBullet(Bullet bullet) {
		bullets.add(bullet);
	}
	
	public void moveBullets() {
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).move();
		}
	}
	
	public void drawBullets(Graphics g) {
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).draw(g);
			//System.out.println("drawing");
		}
	}
}
