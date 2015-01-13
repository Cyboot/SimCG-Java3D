package simcg.engine;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;

import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.Bounds;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.View;
import javax.swing.JFrame;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

import simcg.util.Cube;

import com.sun.j3d.utils.universe.SimpleUniverse;

public class MainFrame extends JFrame {
	public static final int		ROOM_SIZE	= 25;

	private static MainFrame	instance	= new MainFrame();

	/** The canvas where the object is rendered. */
	private Canvas3D			canvas;

	/** Simplifies the configuration of the scene. */
	private SimpleUniverse		universe;

	/** The root node of the scene. */
	private BranchGroup			root		= new BranchGroup();
	private TransformGroup		rootTG;

	private TransformGroup		viewerTransform;


	private void init() {
		configureWindow();
		configureCanvas();
		conigureUniverse();
		addLightsToUniverse();

		createScene();

		KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		manager.addKeyEventDispatcher(Controls.getInstance());

		UserControls.getInstance().start();
	}

	private MainFrame() {
	}

	public TransformGroup getRootTG() {
		return rootTG;
	}

	private void createScene() {
		TransformGroup room = new TransformGroup();
		room.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);


		Appearance appear = new Appearance();
		PolygonAttributes pa = new PolygonAttributes();
		pa.setCullFace(PolygonAttributes.CULL_FRONT);
		appear.setPolygonAttributes(pa);

		Cube cube = new Cube(ROOM_SIZE);
		cube.setAppearance(appear);
		room.addChild(cube);

		rootTG = new TransformGroup();
		rootTG.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

		rootTG.addChild(room);

		// start ParticleManager
		ParticleManager.getInstance().start();

		root.addChild(rootTG);
		root.compile();
		universe.addBranchGraph(root);
	}

	/**
	 * Defines basic properties of this window.
	 */
	private void configureWindow() {
		setTitle("Basic Java3D Program");
		setSize(1200, 800);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int locationX = (screenSize.width - getWidth()) / 2;
		int locationY = (screenSize.height - getHeight()) / 2;
		setLocation(locationX, locationY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Defines basic properties of the canvas.
	 */
	private void configureCanvas() {
		canvas = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
		canvas.setDoubleBufferEnable(true);
		canvas.addFocusListener(Controls.getInstance());

		getContentPane().add(canvas, BorderLayout.CENTER);
	}

	/**
	 * Defines basic properties of the universe.
	 */
	private void conigureUniverse() {
		universe = new SimpleUniverse(canvas);
		universe.getViewingPlatform().setNominalViewingTransform();

		// move the ViewPlatform 500 Meter back (origin is visible)
		Transform3D t3d = new Transform3D();
		t3d.set(new Vector3d(0, 0, ROOM_SIZE * 4));
		viewerTransform = universe.getViewingPlatform().getViewPlatformTransform();
		viewerTransform.setTransform(t3d);
		View view = universe.getViewer().getView();
		view.setBackClipDistance(5000);
	}

	/**
	 * Adds lights
	 */
	private void addLightsToUniverse() {
		Bounds influenceRegion = new BoundingSphere();
		Color3f lightColor = new Color3f(Color.BLUE);
		Vector3f lightDirection = new Vector3f(-1F, -1F, -1F);
		DirectionalLight light = new DirectionalLight(lightColor, lightDirection);
		light.setInfluencingBounds(influenceRegion);
		root.addChild(light);
	}

	public static MainFrame getInstance() {
		return instance;
	}

	public static void main(String[] args) {
		instance.init();
		instance.setVisible(true);
	}
}
