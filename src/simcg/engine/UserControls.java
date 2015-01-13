package simcg.engine;

import java.awt.event.KeyEvent;

import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3d;

public class UserControls extends Thread {
	private static final long	SLEEP		= 10;
	private static UserControls	instance	= new UserControls();
	private boolean				running		= true;

	private Controls			controls	= Controls.getInstance();
	private TransformGroup		rootTG		= MainFrame.getInstance().getRootTG();

	private Vector3d			position	= new Vector3d();
	private double				rotation	= 0;

	@Override
	public void run() {
		while (running) {
			step();

			try {
				Thread.sleep(SLEEP);
			} catch (InterruptedException e) {
			}
		}
	}

	private void step() {
		double speed_factor = 0.5;
		if (controls.isKeyPressed(KeyEvent.VK_SHIFT))
			speed_factor *= 3;

		Transform3D t3d_position = new Transform3D();
		Transform3D t3d_rotation = new Transform3D();
		// rootTG.getTransform(t3d_current);

		if (controls.isKeyPressed(KeyEvent.VK_W)) {
			position.add(new Vector3d(0, 0, SLEEP * 0.1 * speed_factor));
		}
		if (controls.isKeyPressed(KeyEvent.VK_S)) {
			position.add(new Vector3d(0, 0, -SLEEP * 0.1 * speed_factor));
		}
		if (controls.isKeyPressed(KeyEvent.VK_A)) {
			position.add(new Vector3d(SLEEP * 0.1 * speed_factor, 0, 0));
		}
		if (controls.isKeyPressed(KeyEvent.VK_D)) {
			position.add(new Vector3d(-SLEEP * 0.1 * speed_factor, 0, 0));
		}
		if (controls.isKeyPressed(KeyEvent.VK_Q)) {
			rotation += Math.toRadians(speed_factor);
		}
		if (controls.isKeyPressed(KeyEvent.VK_E)) {
			rotation -= Math.toRadians(speed_factor);
		}

		t3d_position.set(position);

		t3d_rotation.rotY(rotation);
		t3d_position.mul(t3d_rotation);
		rootTG.setTransform(t3d_position);
	}

	public static UserControls getInstance() {
		return instance;
	}
}
