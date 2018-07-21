package Entities;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

public class Camera {
	
	private Vector3f position = new Vector3f(0,0,0);
	private float pitch;
	private float yaw;
	private float roll;

	public void move() {

			int dWheel = Mouse.getDWheel();
			if(dWheel < 0) {
				position.z += 0.25f;
			} else if (dWheel > 0) {
				position.z -= 0.25f;
			}
	}
	
	public Camera() {}

	public Vector3f getPosition() {
		return position;
	}

	public float getPitch() {
		return pitch;
	}

	public float getYaw() {
		return yaw;
	}

	public float getRoll() {
		return roll;
	}
	
}
