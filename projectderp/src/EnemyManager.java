import java.awt.Toolkit;
import java.util.ArrayList;


public class EnemyManager {
	private ArrayList<Enemy> enemies;
	
	public EnemyManager() {
		enemies = new ArrayList<Enemy>();
	}
	
	public void createEnemy() {
		Enemy newEnemy = new Enemy(Toolkit.getDefaultToolkit().createImage("res/derpvivor.png"), 64, 64);
		enemies.add(newEnemy);
	}
	
	public void moveEnemies() {
		for (int i = 0; i < enemies.size(); i++) {
			//enemies.get(i).rotation(Sprite.calculateDirection(
			//		enemies.get(i).getAnchorX(), enemies.get(i).getAnchorY(), 
			//		player.getAnchorX(), player.getAnchorY()));
			enemies.get(i).move();
		}
	}
}
