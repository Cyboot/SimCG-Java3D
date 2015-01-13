package simcg.util;

import javax.media.j3d.QuadArray;
import javax.media.j3d.Shape3D;

public class Cube extends Shape3D {
	private static final float[]	verts	= {
											// front face
			1.0f, -1.0f, 1.0f, 1.0f, 1.0f, 1.0f, -1.0f, 1.0f, 1.0f, -1.0f, -1.0f, 1.0f,
			// back face
			-1.0f, -1.0f, -1.0f, -1.0f, 1.0f, -1.0f, 1.0f, 1.0f, -1.0f, 1.0f, -1.0f, -1.0f,
			// right face
			1.0f, -1.0f, -1.0f, 1.0f, 1.0f, -1.0f, 1.0f, 1.0f, 1.0f, 1.0f, -1.0f, 1.0f,
			// left face
			-1.0f, -1.0f, 1.0f, -1.0f, 1.0f, 1.0f, -1.0f, 1.0f, -1.0f, -1.0f, -1.0f, -1.0f,
			// top face
			1.0f, 1.0f, 1.0f, 1.0f, 1.0f, -1.0f, -1.0f, 1.0f, -1.0f, -1.0f, 1.0f, 1.0f,
			// bottom face
			-1.0f, -1.0f, 1.0f, -1.0f, -1.0f, -1.0f, 1.0f, -1.0f, -1.0f, 1.0f, -1.0f, 1.0f, };

	private static final float[]	colors	= {

											// front face (red)
			0.3f, 0.0f, 0.0f, 0.3f, 0.0f, 0.0f, 0.3f, 0.0f, 0.0f, 0.3f, 0.0f, 0.0f,
			// back face (green)
			0.0f, 0.3f, 0.0f, 0.0f, 0.3f, 0.0f, 0.0f, 0.3f, 0.0f, 0.0f, 0.3f, 0.0f,
			// right face (blue)
			0.0f, 0.0f, 0.3f, 0.0f, 0.0f, 0.3f, 0.0f, 0.0f, 0.3f, 0.0f, 0.0f, 0.3f,
			// left face (yellow)
			0.3f, 0.3f, 0.0f, 0.3f, 0.3f, 0.0f, 0.3f, 0.3f, 0.0f, 0.3f, 0.3f, 0.0f,
			// top face (magenta)
			0.3f, 0.0f, 0.3f, 0.3f, 0.0f, 0.3f, 0.3f, 0.0f, 0.3f, 0.3f, 0.0f, 0.3f,
			// bottom face (cyan)
			0.0f, 0.3f, 0.3f, 0.0f, 0.3f, 0.3f, 0.0f, 0.3f, 0.3f, 0.0f, 0.3f, 0.3f, };

	double							scale;

	/**
	 * Constructs a color cube with unit scale. The corners of the color cube
	 * are [-1,-1,-1] and [1,1,1].
	 */
	public Cube() {
		QuadArray cube = new QuadArray(24, QuadArray.COORDINATES | QuadArray.COLOR_3);

		cube.setCoordinates(0, verts);
		cube.setColors(0, colors);

		this.setGeometry(cube);

		scale = 1.0;
	}


	/**
	 * Constructs a color cube with the specified scale. The corners of the
	 * color cube are [-scale,-scale,-scale] and [scale,scale,scale].
	 * 
	 * @param scale
	 *            the scale of the cube
	 */
	public Cube(double scale) {
		QuadArray cube = new QuadArray(24, QuadArray.COORDINATES | QuadArray.COLOR_3);

		float scaledVerts[] = new float[verts.length];
		for (int i = 0; i < verts.length; i++)
			scaledVerts[i] = verts[i] * (float) scale;

		cube.setCoordinates(0, scaledVerts);
		cube.setColors(0, colors);

		this.setGeometry(cube);

		this.scale = scale;
	}

	/**
	 * @deprecated ColorCube now extends shape so it is no longer necessary to
	 *             call this method.
	 */
	@Deprecated
	public Shape3D getShape() {
		return this;
	}

	/**
	 * Returns the scale of the Cube
	 *
	 * @since Java 3D 1.2.1
	 */
	public double getScale() {
		return scale;
	}
}