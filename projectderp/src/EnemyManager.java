import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Random;


public class EnemyManager {
	private ArrayList<Enemy> enemies;
	private Player player;
	private Random random;
	private Ray vision;
	public static final int MAX_VISION_RANGE = 200;
	
	public EnemyManager(Player host) {
		enemies = new ArrayList<Enemy>();
		player = host;
		random = GameCanvas.random;
		vision = new Ray();
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
		boolean visible;
		int visionRun;
		
		for (int i = 0; i < enemies.size(); i++) {
			visible = false;
			distance = Sprite.calculateDistance(
					enemies.get(i).getAnchorX(), enemies.get(i).getAnchorY(), 
					player.getAnchorX(), player.getAnchorY());
			direction = Sprite.calculateDirection(
					enemies.get(i).getAnchorX(), enemies.get(i).getAnchorY(), 
					player.getAnchorX(), player.getAnchorY());
			
			vision.setX(enemies.get(i).getAnchorX());
			vision.setY(enemies.get(i).getAnchorY());
			vision.setMovementDirection((int) direction);
			visionRun = (int) distance;
			
			while (visionRun >= 0 && visionRun <= MAX_VISION_RANGE) {
				vision.move();
				
				/*
				if (vision.collidesWith(wall)) {
					break;
				}
				*/
				
				if (vision.collidesWith(player)) {
					visible = true;
					enemies.get(i).setLastKnownPos(new Point(vision.getX(), vision.getY()));
					enemies.get(i).setMoveRandomly(false);
					break;
				}
				visionRun--;
			}
			
			if (!enemies.get(i).collidesWith(player) && distance < MAX_VISION_RANGE && visible) {
				
				enemies.get(i).rotation(direction);
				enemies.get(i).setMovementDirection((int) direction);
				
				enemies.get(i).move();
			} else if (enemies.get(i).collidesWith(player)) {
				player.alterHealth(-1);
			} else if (enemies.get(i).isMoveRandomly() == false) {
				enemies.get(i).moveTowardsLastKnownPos();
			} else {
				// move randomly
				//System.out.println("moving randomly " + i);
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
