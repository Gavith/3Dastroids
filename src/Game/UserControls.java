package Game;

import engine.EnigWindow;

import static org.lwjgl.glfw.GLFW.*;

public class UserControls {
	public static int forward = GLFW_KEY_W;
	public static boolean forward() {
		return EnigWindow.mainWindow.keys[forward] > 0;
	}
	public static int backward = GLFW_KEY_S;
	public static boolean backward() {
		return EnigWindow.mainWindow.keys[backward] > 0;
	}
	public static int left = GLFW_KEY_A;
	public static boolean left() {
		return EnigWindow.mainWindow.keys[left] > 0;
	}
	public static int right = GLFW_KEY_D;
	public static boolean right() {
		return EnigWindow.mainWindow.keys[right] > 0;
	}
	public static int down = GLFW_KEY_LEFT_SHIFT;
	public static boolean down() {
		return EnigWindow.mainWindow.keys[down] > 0;
	}
	public static int up = GLFW_KEY_SPACE;
	public static boolean up() {
		return EnigWindow.mainWindow.keys[up] > 0;
	}
	public static int rLeft = GLFW_KEY_Q;
	public static boolean rLeft() {
		return EnigWindow.mainWindow.keys[rLeft] > 0;
	}
	public static int rRight = GLFW_KEY_E;
	public static boolean rRight() {
		return EnigWindow.mainWindow.keys[rRight] > 0;
	}
	public static int restart = GLFW_KEY_R;
	public static boolean restart() {
		return EnigWindow.mainWindow.keys[restart] > 0;
	}
	public static int pause = GLFW_KEY_P;
	public static boolean pause() {
		return EnigWindow.mainWindow.keys[pause] > 0;
	}
	public static float sensitivity = 1f/500f;
	
/*
	if commonCamera is true, the camera rotation for most 3d games will be used, if it is false, this alternate method is used:

	every frame the new rotation matrix is set to the rotation matrix from relative mouse position changes (from the last frame),
	will be multiplied by the old rotation matrix.
	R(xnew-xold)*R(ynew-yold)*R(znew-zold)*oldRotationMatrix.
	
	oh btw
	this variable has been moved from this file, to the Camera file, so you can have different cameras with different types of cameras
																																		 */
}
