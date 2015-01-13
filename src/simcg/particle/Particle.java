package simcg.particle;

import javax.media.j3d.Appearance;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3d;

import com.sun.j3d.utils.geometry.Sphere;

public class Particle {
	public static TransformGroup	particleRoot;

	private TransformGroup			trans;

	private double					mass	= 1;
	private Vector3d				velocity;
	private Vector3d				position;

	public Particle(double x, double y, double z) {
		trans = new TransformGroup();
		Transform3D t3d = new Transform3D();
		position = new Vector3d(x, y, z);
		velocity = new Vector3d();
		t3d.set(position);

		trans.setTransform(t3d);
		trans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		trans.addChild(getSphere());

		particleRoot.addChild(trans);
	}

	public void update() {
		Transform3D t3d = new Transform3D();

		t3d.set(position);
		trans.setTransform(t3d);
	}

	public void setMass(double mass) {
		this.mass = mass;
	}

	public void setVelocity(double x, double y, double z) {
		velocity.set(x, y, z);
	}

	public Vector3d getPosition() {
		return position;
	}

	public Vector3d getVelocity() {
		return velocity;
	}

	public double getMass() {
		return mass;
	}

	private Sphere getSphere() {
		Sphere sphere = new Sphere(.25f);
		Appearance appearance = new Appearance();

		ColoringAttributes colattribut = new ColoringAttributes(0.9f, 0.9f, 0.9f, ColoringAttributes.NICEST);
		appearance.setColoringAttributes(colattribut);
		sphere.setAppearance(appearance);
		return sphere;
	}
}
