
public class Ray extends Sprite {
	public Ray() {
		super();
		setMovementSpeed(4);
		setHitbox(new Hitbox(Hitbox.TYPE_CIRCLE, 10));
	}
}
