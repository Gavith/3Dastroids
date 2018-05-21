package Game;

import engine.*;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import java.lang.Math;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_INT;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

public class Dastroid  {
	
	public VAO model;
	
	public int index = 0;
	
	private float scale = 3f;
	
	private Vector3f position = new Vector3f();
	private Vector3f velocity = new Vector3f();
	private Vector3f rotation = new Vector3f();
	
	public Matrix4f translationMatrix() {
		return new Matrix4f().translate(position).rotateZ(rotation.z).rotateY(rotation.y).rotateX(rotation.x).scale(scale);
	}
	private static boolean textureGenerated = false;
	private static Texture tex;
	
	public Dastroid() {
		
		if (!textureGenerated) {
			textureGenerated = true;
			tex = new Texture("res/dasTexture.png");
		}
		
		velocity.x += 0.1f * Math.random() - 0.05f;
		velocity.y += 0.1f * Math.random() - 0.05f;
		velocity.z += 0.1f * Math.random() - 0.05f;
		
		position.x = 100f * (float) Math.random() - 50f;
		position.y = 100f * (float) Math.random() - 50f;
		position.z = 100f * (float) Math.random() - 50f;
		
		rotation.x = 6.283f * (float) Math.random();
		rotation.y = 6.283f * (float) Math.random();
		rotation.z = 6.283f * (float) Math.random();
		
		index = (int) (5 * Math.random());
		String post = "" + index;
		if (index < 10) {
			post = "0" + post;
		}
		model = OBJLoader.loadVAO("res/dastroids/a" + post + ".obj");
		float[] data = new float[model.getVertxCount() * 2];
		
		for (int i = 0; i < model.getVertxCount(); ++i) {
			data[2 * i] = (float) Math.random();
			data[2 * i + 1] = (float) Math.random();
		}
		
		model.vbos[1].setData(data);
	}
	
	public Dastroid[] split() {
		if (scale < 1.1) {
			return new Dastroid[0];
		}
		Dastroid[] ret = new Dastroid[] {new Dastroid(), new Dastroid()};
		for (Dastroid d: ret) {
			d.scale = scale - 1;
			d.velocity.add(velocity);
			d.position.div(50);
			d.position.add(position);
		}
		return ret;
	}
	
	public void updatePosition(float px, float py, float pz) {
		position.add(velocity);
		if (position.x - px > 50f) {
			position.x -= 100f;
		}else if (position.x - px < -50f) {
			position.x += 100f;
		}
		if (position.y - py > 50f) {
			position.y -= 100f;
		}else if (position.y - py < -50f) {
			position.y += 100f;
		}
		if (position.z - pz > 50f) {
			position.z -= 100f;
		}else if (position.z - pz < -50f) {
			position.z += 100f;
		}
	}
	
	public void render(Camera cam) {
		Uniform mat = new Uniform("kek", 2);
		for (Uniform uni:Program.currentShaderProgram.shaders[0].uniforms) {
			if (uni.getName().equals("matrix")) {
				mat = uni;
				uni.set(cam.getCameraMatrix().mul(translationMatrix()));
			}
		}
		tex.bind();
		glBindVertexArray(model.getID());
		for(int i = 0; i < model.vbos.length; ++i) {
			glEnableVertexAttribArray(i);
		}
		
		for (float x = -100f; x < 150f; x += 100f) {
			for (float y = -100f; y < 150; y += 100f) {
				for (float z = -100f; z < 150; z += 100f) {
					Matrix4f trans = new Matrix4f().translate(x + position.x, y + position.y, z + position.z);
					mat.set(cam.getCameraMatrix().mul(trans).scale(scale));
					glDrawElements(GL_TRIANGLES, model.getIndices().length, GL_UNSIGNED_INT, 0);
				}
			}
		}
		for(int i = 0; i < model.vbos.length; ++i) {
			glDisableVertexAttribArray(i);
		}
	}
	
	public Vector3f getPosition() {
		return position;
	}
	
	public float getScale() {
		return scale;
	}
}
