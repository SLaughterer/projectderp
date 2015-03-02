import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Random;


public class EnemyManager {
	private ArrayList<Enemy> enemies;
	private Player player;
	private Random random;
	
	public EnemyManager(Player host) {
		enemies = new ArrayList<Enemy>();
		player = host;
		random = GameCanvas.random;
	}
	
	public void createEnemy() {
		Enemy newEnemy = new Enemy("res/Enemies/Soldier.png", 64, 64, 64, 64);
		newEnemy.setHitbox(new Hitbox(Hitbox.TYPE_CIRCLE, 16));
		newEnemy.setMovementSpeed(3);
		newEnemy.setX(random.nextInt(800));
		newEnemy.setY(random.nextInt(600));
		enemies.add(newEnemy);
	}
	
	public void moveEnemies() {
		double direction;
		double distance;
		
		for (int i = 0; i < enemies.size(); i++) {
			direction = Sprite.calculateDirection(
					enemies.get(i).getAnchorX(), enemies.get(i).getAnchorY(), 
					player.getAnchorX(), player.getAnchorY());
			enemies.get(i).rotation(direction);
			enemies.get(i).setMovementDirection((int) direction);
			distance = Sprite.calculateDistance(
					enemies.get(i).getAnchorX(), enemies.get(i).getAnchorY(), 
					player.getAnchorX(), player.getAnchorY());
			if (!enemies.get(i).collidesWith(player) && distance < 200) {
				enemies.get(i).move();
			} else if (enemies.get(i).collidesWith(player)) {
				player.alterHealth(-1);
			}
		}
	}
	
	public void drawEnemies(Graphics g) {
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).draw(g);
			//System.out.println("drawing");
		}
	}
	
	public void shoot() {
		for (int i = 0; i < enemies.size(); i++) {
			//if enemy sees player
			enemies.get(i).shoot();
		}
	}
	
	public void moveGun() {
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).moveGun();
		}
	}
	
	public int size() {
		return enemies.size();
	}
	
	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}
	
	public Enemy getEnemy(int index) {
		return enemies.get(index);
	}
	
	public void deleteEnemies() {
		for (int i = 0; i < enemies.size(); i++) {
			if (enemies.get(i).getHealth() <= 0) {
				enemies.remove(i);
				i--;
			}
		}
	}
}
