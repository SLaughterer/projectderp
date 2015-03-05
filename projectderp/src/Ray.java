
public class Ray extends Sprite {
	public Ray(int posX, int posY, int movementDirection) {
		super();
		setX(posX);
		setY(posY);
		setMovementDirection(movementDirection);
		setHitbox(new Hitbox(Hitbox.TYPE_CIRCLE, 1));
	}
}
