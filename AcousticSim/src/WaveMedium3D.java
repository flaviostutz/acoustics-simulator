import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.TransparencyAttributes;
import javax.swing.Timer;
import javax.vecmath.Point3d;

import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.universe.SimpleUniverse;

/**
 * Demonstration of the 2D wave equation.
 * 
 * @author <a href="mailto:nagydani@mast.queensu.ca">Daniel A. Nagy</a>
 */
public class WaveMedium3D extends Applet implements ActionListener, MouseListener,
		MouseMotionListener {

	
	// added by Flávio Stutz
	static final float WAVE_VELOCITY = 0.7F;
	static final float WAVE_LENGTH = 0.7F;
	static final float SCREEN_SIZE = 400;
	static final float RESOLUTION = 30;
	static final float OVERALL_DAMPING = 1F;

	//alternate loopings strategy
	Looping xl = new Looping(1,SIZE-2,1);
	Looping yl = new Looping(1,SIZE-2,1);
	Looping zl = new Looping(1,SIZE-2,1);
	
	static final int MEDIUM_MAX = 191;
	static final double MEDIUM_MAX_INDEX = .5;
	
	long counter = 0;
	long last = System.currentTimeMillis();
	
	public static final int SIZE = (int) RESOLUTION;

	/* periodic excitation: d^2(ex)/(dt)^2=-c*ex */
	private double ex = 0; // value
	private double dex = 300; // d/dt

	/* sample array */
	private double[][][] f = new double[SIZE][SIZE][SIZE]; // value
	private double[][][] fi = new double[SIZE][SIZE][SIZE]; // complex part

	private double[][][] medium = new double[SIZE][SIZE][SIZE]; // medium coeficients
	private double[][][] damp = new double[SIZE][SIZE][SIZE]; // damping in space
	
	/* image */
	private BufferedImage im = new BufferedImage(SIZE, SIZE,
			BufferedImage.TYPE_BYTE_GRAY);

	/* mouse */
	boolean pressed = false;

	int mx, my, mz=SIZE/2;

	/* timer */
	Timer t = new Timer(10, this);

	/* initialization */
	public void init() {
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	/* start */
	public void start() {
		t.start();
		
		//init damp and medium index
		for (int x = 0; x < SIZE; x++) {
			for (int y = 0; y < SIZE; y++) {
				for (int z = 0; z < SIZE; z++) {
/*					if(y>SIZE/2) {
						medium[x][y][z] = MEDIUM_MAX * 0.5F;
					} else {*/
						medium[x][y][z] = MEDIUM_MAX * 0F;
					//}
					damp[x][y][z] = 0.995F;
				}
			}
		}
	}

	/* stop */
	public void stop() {
		t.stop();
	}

	/* every 10 milliseconds */
	public void actionPerformed(ActionEvent e) {
		int x, y, z, h;
		double avg;
		Graphics g = getGraphics();
		
		/* excitation */
		dex -= ex * (1 - WAVE_LENGTH);
		ex += dex;
		
		xl.reset();
		yl.reset();
		zl.reset();
		
		//process in alternate directions to avoid directional bias 
		for(int i=0; i<2; i++) {
			while (xl.next()) {
				x = xl.getNumber();
				while (yl.next()) {
					y = yl.getNumber();
					while (zl.next()) {
						z = zl.getNumber();
						
						//Handle medium
						//tadd2 = velocity in current medium
						double tadd2 = WAVE_VELOCITY 
										* (1 - (MEDIUM_MAX_INDEX / MEDIUM_MAX)
										* medium[x][y][z]);
						
						double sinth = (float)(Math.sin(tadd2) * OVERALL_DAMPING);
						double scaleo = (float)Math.cos(tadd2);
						
						//if((x>20 && x<50) && y==25 && z>10 && z<30) continue;
						avg = (f[x-1][y][z] + f[x+1][y][z] 
						     + f[x][y-1][z] + f[x][y+1][z]
						     + f[x][y][z-1] + f[x][y][z+1]
						     ) / 6;
	
						//here we are multiplying complex numbers.
						//  a+bi = ((func + funci i) - basis) * damp[gi]
						// (func + funci i) = (a+bi) * (scaleo + sinth i) + basis.
						double a = (f[x][y][z] - avg) * damp[x][y][z];
						double b = fi[x][y][z] * damp[x][y][z];
						f[x][y][z] = avg + a * scaleo - b * sinth;
						fi[x][y][z] = b * scaleo + a * sinth;

						// testing
						if ((++counter) % (SIZE * SIZE * SIZE) == 0) {
							counter = 0;
							System.out.println("YEAH! 1 refresh in "
									+ (System.currentTimeMillis() - last) + "ms");
							last = System.currentTimeMillis();
						}
					}
					zl.reverse();
				}
				yl.reverse();
			}
			xl.reverse();
		}
		
		/* graphics */
		x = SIZE;
		while (--x >= 0) {
			y = SIZE;
			while (--y >= 0) {
				z = mz;
				//while (z-- >= 0) {
					h = 0x80 + (int) f[x][y][z];
					im.setRGB(x, y, h * 0x10101);
				//}
			}
		}
		if (pressed && (mx >= 0) && (my >= 0) && (mz >= 0) && (mx < SIZE) && (my < SIZE)  && (mz < SIZE)) {
			f[mx][my][mz] = ex;
		}
		g.drawImage(im, 0, 0, (int) SCREEN_SIZE, (int) SCREEN_SIZE, this);
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {

		if(e.getClickCount()==2) {
			show3DScene(f);
		}
		
		if(e.getButton()==1) {
			pressed = true;
			
		} else if(e.getButton()==2) {
			if((mz+SIZE/10)<SIZE) {
				mz += SIZE/10;
			}
			
		} else if(e.getButton()==3) {
			if((mz-SIZE/10)>=0) {
				mz -= SIZE/10;
			}
		}
	}

	public void mouseReleased(MouseEvent e) {
		if(e.getButton()==1) {
			pressed = false;
		}
	}

	public void mouseDragged(MouseEvent e) {
		mx = (int) (e.getX() * (SIZE / SCREEN_SIZE));
		my = (int) (e.getY() * (SIZE / SCREEN_SIZE));
	}

	public void mouseMoved(MouseEvent e) {
		mx = (int) (e.getX() * (SIZE / SCREEN_SIZE));
		my = (int) (e.getY() * (SIZE / SCREEN_SIZE));
	}

	private void show3DScene(double[][][] f) {
		
		TransparencyAttributes ta = new TransparencyAttributes();
		ta.setTransparencyMode(TransparencyAttributes.BLEND_SRC_ALPHA);
		ta.setTransparency(.5F);
		
		Appearance a = new Appearance();
		a.setTransparencyAttributes(ta);

		BranchGroup bg = new BranchGroup();

		for(int x=0; x<f.length; x++) {
			for(int y=0; y<f[x].length; y++) {
				for(int z=0; z<f[x][y].length; z++) {
					ColorCube cube = new ColorCube(0.1);
					cube.setAppearance(a);
					bg.addChild(cube);
				}
			}
		}
		
		setLayout(new BorderLayout());
		add(BorderLayout.CENTER, getCanvas3D(bg));
	}
	
	public boolean keyDown(Event evt, int key) {
		System.out.println("aha!");
		for(int a=0; a<SIZE; a++) {
			for(int b=0; b<SIZE; b++) {
				for(int c=0; c<SIZE; c++) {
					f[a][b][c] = 0;
					fi[a][b][c] = 0;
				}
			}
		}
		return true;
	}
	
	public static void addBranchGroupToContainer(BranchGroup branchGroup, Container container) { 
		container.setLayout(new BorderLayout());
		container.add("Center", getCanvas3D(branchGroup));
	}
	
	public static Canvas3D getCanvas3D(BranchGroup scene) {
		GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();

		Canvas3D c = new Canvas3D(config);

		// Create a simple scene and attach it to the virtual universe
		SimpleUniverse u = new SimpleUniverse(c);

		// This will move the ViewPlatform back a bit so the
		// objects in the scene can be viewed.
		u.getViewingPlatform().setNominalViewingTransform();
		
		//set mouse orbit
	    OrbitBehavior orbit = new OrbitBehavior(c);
	    orbit.setReverseRotate(true);
	    orbit.setReverseTranslate(true);
	    orbit.setReverseZoom(true);
	    orbit.setSchedulingBounds(new BoundingSphere(new Point3d(0.0, 0.0, 0.0), Double.POSITIVE_INFINITY));
	    u.getViewingPlatform().setViewPlatformBehavior(orbit);
		
		u.addBranchGraph(scene);
		
		return c;
	}

	
}
