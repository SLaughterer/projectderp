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
			bullets.get(i).drawB(g);
			//System.out.println("drawing");
		}
	}
	
	public void collisions(Player player, EnemyManager enemies) {
		for (int i = 0; i < bullets.size(); i++) {
			if (bullets.get(i).getShooter() == Bullet.PLAYER) {
				for (int n = 0; n < enemies.size(); n++) {
					if (Hitbox.collisionCheck(bullets.get(i), enemies.getEnemy(n))) {
						enemies.getEnemy(n).AlterHealth(-bullets.get(i).getDamage());
						player.addScore(1);
					}
				}
			} else if (bullets.get(i).getShooter() == Bullet.ENEMY) {
				if (Hitbox.collisionCheck(bullets.get(i), player)) {
					player.alterHealth(-bullets.get(i).getDamage());
				}
			}
		}
	}
	
	public void deleteBullets() {
		for (int i = 0; i < bullets.size(); i++) {
			if (bullets.get(i).isDead()) {
				bullets.remove(i);
				i--;
			}
		}
	}
}
