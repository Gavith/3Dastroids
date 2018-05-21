package Game;

import org.joml.Vector3f;

public class Player extends Entity {
	public void updateRotation(float p, float y, boolean rollLeft, boolean rollRight) {
		pitch((float) -y * UserControls.sensitivity);
		yaw((float) -x * UserControls.sensitivity);
		if (rollLeft) {
			roll(-UserControls.sensitivity*10f);
		}
		if (rollRight) {
			roll(UserControls.sensitivity*10f);
		}
	}
	public void updatePosition(boolean forward, boolean left, boolean right, boolean up, boolean down, boolean rollLeft, boolean rollRight) {
		float xa = 0;
		float ya = 0;
		float za = 0;
		if (forward) {
			Vector3f mVector = new Vector3f(0f, 0f, 0.1f/10f);
			mVector.rotateX(-getPitch()*2);
			mVector.rotateY(-getYaw()*2);
			mVector.rotateZ(-getRoll()*2);
			xa = mVector.x;
			ya = mVector.y;
			za = mVector.z;
		}
		if (left) {
			yaw(-UserControls.sensitivity*10f);
		}
		if (right) {
			yaw(UserControls.sensitivity*10f);
		}
		if (down) {
			pitch(UserControls.sensitivity*10f);
		}
		if (up) {
			pitch(-UserControls.sensitivity*10f);
		}
		if (rollLeft) {
			roll(-UserControls.sensitivity*10f);
		}
		if (rollRight) {
			roll(UserControls.sensitivity*10f);
		}
		updateRotations();
		super.updatePosition(xa, ya, za);
	}
	
}
