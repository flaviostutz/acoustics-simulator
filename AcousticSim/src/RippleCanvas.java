import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;

class RippleCanvas extends Canvas {
	RippleFrame pg;

	
	RippleCanvas(RippleFrame p) {
		pg = p;
	}

	public Dimension getPreferredSize() {
		return new Dimension(300, 400);
	}

	public void update(Graphics g) {
		pg.updateRipple(g);
	}

	public void paint(Graphics g) {
		pg.updateRipple(g);
	}
};

