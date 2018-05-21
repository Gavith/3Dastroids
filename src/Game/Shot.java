package Game;

import engine.Camera;
import engine.OBJLoader;
import engine.Program;
import engine.VAO;
import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Shot extends Vector3f {
	private Vector3f velocity;
	private VAO obj;
	public int duration = 0;
	public Shot(Player player, boolean left) {
		Vector3f startVector = new Vector3f(-0.5f, -0.5f, 0f);
		if (left) {
			startVector = new Vector3f(0.5f, -0.5f, 0f);
		}
		startVector.rotateX(-player.getPitch()*2);
		startVector.rotateY(-player.getYaw()*2);
		startVector.rotateZ(-player.getRoll()*2);
		x = startVector.x - player.x;
		y = startVector.y - player.y;
		z = startVector.z - player.z;
		Vector3f velocityVector = new Vector3f(0f, 0f, -0.75f);
		velocityVector.rotateX(-player.getPitch()*2);
		velocityVector.rotateY(-player.getYaw()*2);
		velocityVector.rotateZ(-player.getRoll()*2);
		velocity = velocityVector.sub(player.getVelocity());
		obj = OBJLoader.loadVAO("res/shot.obj");
		System.out.println(this);
	}
	public void update(Vector3f ppos) {
		++duration;
		add(velocity);
		if (x > 50f - ppos.x) {
			x -= 100f;
		}else if (x < -50f - ppos.x) {
			x += 100f;
		}
		if (y > 50f - ppos.y) {
			y -= 100f;
		}else if (y < -50f - ppos.y) {
			y += 100f;
		}
		if (z > 50f - ppos.z) {
			z -= 100f;
		}else if (z < -50f - ppos.z) {
			z += 100f;
		}
	}
	public void render(Camera cam) {
		Program.currentShaderProgram.shaders[0].uniforms[0].set(new Matrix4f(cam.getCameraMatrix().translate(this).scale(5)));
		obj.render();
	}
}
