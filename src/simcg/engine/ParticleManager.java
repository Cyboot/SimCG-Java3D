package simcg.engine;

import simcg.particle.Particle;
import simcg.particle.ParticleSimulation;


public class ParticleManager extends Thread {
	private static final long		TARGET_DELTA	= 10;
	private static ParticleManager	instance		= new ParticleManager();
	private ParticleSimulation		simulation;

	@Override
	public synchronized void start() {
		Particle.particleRoot = MainFrame.getInstance().getRootTG();
		simulation = new ParticleSimulation();
		simulation.init();

		super.start();
	}

	@Override
	public void run() {
		long delta = 0;
		while (true) {
			long start = System.currentTimeMillis();

			step(delta);


			long timepassed = System.currentTimeMillis() - start;
			if (timepassed < TARGET_DELTA) {
				try {
					Thread.sleep(TARGET_DELTA - timepassed);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			delta = System.currentTimeMillis() - start;
		}
	}

	private void step(long delta) {
		simulation.update(delta);
	}

	public static ParticleManager getInstance() {
		return instance;
	}
}
