package Game;

import org.joml.Vector3f;

public class Alien {
	boolean small;
	//Vector3f position = new
	public Alien() {
		if (Math.random() < 0.25) {
			small = true;
		}else {
			small = false;
		}
	}
}
