package simcg.particle;

import java.util.ArrayList;
import java.util.List;

import javax.vecmath.Vector3d;

import simcg.engine.MainFrame;
import simcg.util.RandomUtil;

public class ParticleSimulation {
	private List<Particle>	particles;

	/**
	 * create particles
	 */
	public void init() {
		particles = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			int max = MainFrame.ROOM_SIZE;
			double x = RandomUtil.nextDouble(-max, max);
			double y = RandomUtil.nextDouble(-max, max);
			double z = RandomUtil.nextDouble(-max, max);
			Particle particle = new Particle(x, y, z);

			max = MainFrame.ROOM_SIZE / 2;
			x = RandomUtil.nextDouble(-max, max);
			y = RandomUtil.nextDouble(-max, max);
			z = RandomUtil.nextDouble(-max, max);
			particle.setVelocity(x, y, z);


			particles.add(particle);
		}
	}

	/**
	 * update all particles
	 * 
	 * @param delta
	 *            time passed in seconds
	 */
	public void update(double delta) {

		for (Particle particle : particles) {
			worldCollision(particle, delta);

			Vector3d pos = particle.getPosition();
			Vector3d vel = new Vector3d(particle.getVelocity());

			// Euler integration
			vel.scale(delta);
			pos.add(vel);

			// Gravity
			Vector3d acc = new Vector3d(0, -9.81, 0);
			acc.scale(delta);

			particle.getVelocity().add(acc);

			particle.update();
		}
	}

	/**
	 * calculates and resolves collisions with Room walls
	 */
	private void worldCollision(Particle particle, double delta) {
		Vector3d pos = new Vector3d(particle.getPosition());
		Vector3d vel = new Vector3d(particle.getVelocity());
		vel.scale(delta);
		pos.add(vel);

		if (pos.x < -MainFrame.ROOM_SIZE || pos.x > MainFrame.ROOM_SIZE)
			particle.getVelocity().x = -particle.getVelocity().x;
		if (pos.y < -MainFrame.ROOM_SIZE || pos.y > MainFrame.ROOM_SIZE)
			particle.getVelocity().y = -particle.getVelocity().y;
		if (pos.z < -MainFrame.ROOM_SIZE || pos.z > MainFrame.ROOM_SIZE)
			particle.getVelocity().z = -particle.getVelocity().z;

	}
}
