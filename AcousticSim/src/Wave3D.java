import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.*;

/**
 * Demonstration of the 2D wave equation.
 * 
 * @author <a href="mailto:nagydani@mast.queensu.ca">Daniel A. Nagy</a>
 */
public class Wave3D extends Applet implements ActionListener, MouseListener,
		MouseMotionListener {

	// added by Flávio Stutz
	static final float WAVE_VELOCITY = 0.7F;
	static final float MEDIUM_DUMPING = 0.002F;
	static final float WAVE_LENGTH = 0.8F;
	static final float SCREEN_SIZE = 400;
	static final float RESOLUTION = 50;
	
	private double[][][] medium = new double[SIZE][SIZE][SIZE];

	long counter = 0;
	long last = System.currentTimeMillis();
	
	public static final int SIZE = (int) RESOLUTION;

	/* periodic excitation: d^2(ex)/(dt)^2=-c*ex */
	private double ex = 0; // value
	private double dex = 300; // d/dt

	/* sample array */
	private double[][][] f = new double[SIZE][SIZE][SIZE]; // value
	private double[][][] df = new double[SIZE][SIZE][SIZE]; // d/dt

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
		
		/* f:=f+df */
		x = SIZE;
		while (--x >= 0) {
			y = SIZE;
			while (--y >= 0) {
				z = SIZE;
				while (--z >= 0) {
					f[x][y][z] += df[x][y][z];
				}
			}
		}
		
		/* df:=df+ddf */
		x = SIZE-1;
		while (--x >= 1) {
			y = SIZE-1;
			while (--y >= 1) {
				z = SIZE-1;
				while (--z >= 1) {
					//if((x>20 && x<50) && y==25 && z>20 && z<50) continue;
					avg = (f[x-1][y][z] + f[x+1][y][z] 
					     + f[x][y-1][z] + f[x][y+1][z]
					     + f[x][y][z-1] + f[x][y][z+1]
					     ) / 6;

					df[x][y][z] -= WAVE_VELOCITY * (f[x][y][z] - avg);
					df[x][y][z] *= (1 - MEDIUM_DUMPING);

					// testing
					if ((++counter) % (SIZE * SIZE * SIZE) == 0) {
						counter = 0;
						System.out.println("YEAH! 1 refresh in "
								+ (System.currentTimeMillis() - last) + "ms");
						last = System.currentTimeMillis();
					}
				}
			}
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
			df[mx][my][mz] = dex;
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
	
	@Override
	public boolean keyDown(Event evt, int key) {
		if(key==Event.ENTER) {
			
		}
		return true;
	}
}
