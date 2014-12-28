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
	private double				rotation;

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
		double speed_factor = 1;
		if (controls.isKeyPressed(KeyEvent.VK_SHIFT))
			speed_factor = 3;


		Transform3D t3d_current = new Transform3D();
		Transform3D t3d = new Transform3D();
		rootTG.getTransform(t3d_current);

		if (controls.isKeyPressed(KeyEvent.VK_W)) {
			t3d.set(new Vector3d(0, 0, SLEEP * 0.1 * speed_factor));
		}
		if (controls.isKeyPressed(KeyEvent.VK_S)) {
			t3d.set(new Vector3d(0, 0, -SLEEP * 0.1 * speed_factor));
		}
		if (controls.isKeyPressed(KeyEvent.VK_A)) {
			t3d.set(new Vector3d(SLEEP * 0.1 * speed_factor, 0, 0));
		}
		if (controls.isKeyPressed(KeyEvent.VK_D)) {
			t3d.set(new Vector3d(-SLEEP * 0.1 * speed_factor, 0, 0));
		}
		if (controls.isKeyPressed(KeyEvent.VK_Q)) {
			rotation += Math.toRadians(0.2 * speed_factor);

			t3d.rotY(Math.toRadians(0.2 * speed_factor));
		}
		if (controls.isKeyPressed(KeyEvent.VK_E)) {
			rotation -= Math.toRadians(0.2 * speed_factor);
			t3d.rotY(-Math.toRadians(0.2 * speed_factor));
		}

		t3d_current.mul(t3d);
		rootTG.setTransform(t3d_current);
	}

	public static UserControls getInstance() {
		return instance;
	}
}
