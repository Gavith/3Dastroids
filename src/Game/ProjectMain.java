package Game;

import engine.*;

import java.awt.*;
import java.util.ArrayList;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.glLineWidth;
import static org.lwjgl.opengl.GL11.glPointSize;

public class ProjectMain {
	
	//project variables
	private int resFrames = 60;

	private int frame = 0;
	
	public static ProjectMain main;
	
/*															start														*/
	
	public Program asteroidShader;
	public Program shotShader;
	
	public Player player = new Player();
	public ArrayList<Dastroid> asteroids = new ArrayList<>();
	public Texture tex;
	public ArrayList<Shot> shots = new ArrayList<>();
	
	public boolean isleft = false;
	public int stimer = 0;
	
	public void setup() {
		//set variables here
		for (int i = 0; i < 10; ++i) {
			asteroids.add(new Dastroid());
		}
		asteroidShader = new Program("res/shaders/textureShaders/vert.gls", "res/shaders/textureShaders/frag.gls");
		shotShader = new Program("res/shaders/shotShaders/vert.gls", "res/shaders/shotShaders/frag.gls");
	}
	
	public void gameLoop() {
		//game here
		player.updatePosition(UserControls.forward(), UserControls.left(), UserControls.right(), UserControls.up(), UserControls.down(), UserControls.rLeft(), UserControls.rRight());
		for (Dastroid d : asteroids) {
			d.updatePosition(-player.x, -player.y, -player.z);
		}
		asteroidShader.enable();
		for (Dastroid d : asteroids) {
			d.render(player);
		}
		if (EnigWindow.mainWindow.mouseButtons[GLFW_MOUSE_BUTTON_LEFT] > 0) {
			if (stimer < 1) {
				shots.add(new Shot(player, isleft));
				isleft = !isleft;
				stimer = 10;
			}
		}
		stimer--;
		shotShader.enable();
		for (int s = 0;s < shots.size(); ++s) {
			shots.get(s).update(player);
			shots.get(s).render(player);
			for (Dastroid d:asteroids) {
				if (shots.get(s).distance(d.getPosition()) < d.getScale()) {
					Dastroid[] split = d.split();
					asteroids.remove(d);
					shots.remove(s);
					for (Dastroid thing:split) {
						asteroids.add(thing);
					}
					break;
				}
			}
		}
		if (shots.size() > 0) {
			if (shots.get(0).duration > 90) {
				shots.remove(0);
			}
		}
	}
	
/*															end															*/
	
	public static void main(String[] args) {
		new EnigWindow();
		main = new ProjectMain();
	}
	
	public ProjectMain() {
		frame = 0;
		setup();
		main = this;
		while (!glfwWindowShouldClose(EnigWindow.mainWindow.window) ) {
			EnigWindow.mainWindow.update();
			++frame;
			gameLoop();
			cleanup();
		}
		EnigWindow.mainWindow.terminate();
	}
	
	public void cleanup() {
		Program.disable();
		Texture.unbind();
		EnigWindow.mainWindow.resetOffsets();
		glfwSwapBuffers(EnigWindow.mainWindow.window);
		glfwPollEvents();
		EnigWindow.checkGLError();
	}
}