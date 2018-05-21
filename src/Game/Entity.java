package Game;

import engine.Camera;
import engine.Texture;
import engine.VAO;
import org.joml.Vector3f;

public class Entity extends Camera {
	
	private Vector3f velocity = new Vector3f();
	
	public Entity() {
		super(70, 0.1f, 10000f);
	}
	public void updatePosition(Vector3f acceleration) {
		updatePosition(acceleration.x, acceleration.y, acceleration.z);
	}
	public void updatePosition(float xa, float ya, float za) {
		velocity.x += xa;
		velocity.y += ya;
		velocity.z += za;
		add(velocity);
	}
	
	public Vector3f getVelocity() {
		return velocity;
	}
}
