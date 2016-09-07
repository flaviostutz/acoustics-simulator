package sim;

import java.util.ArrayList;
import java.util.List;

public class Wave2DArea0 {

	private float[][] pos0;
	private float[][] pos1;
	private float[][] pos2;
	private int width;
	private int height;
	
	public Wave2DArea0(int width, int height) {
		this.width = width;
		this.height = height;
		this.pos0 = new float[width][height];
		this.pos1 = null;
		this.pos2 = null;
	}
	
	public void step() {
		float dt = 0.3F;
		float h = 0.2F;
		float v = 1;

		float c = (v*dt)/h;
		float c2 = (c*c)/2;
		
		pos2 = pos1;
		pos1 = pos0;
		for(int x=1; x<width-1; x++) {
			for(int y=1; y<height-1; y++) {
				if(pos2==null) {
					pos0[x][y] = pos1[x][y] 
					                + c2*(pos1[x-1][y] - 2*pos1[x][y] + pos1[x+1][y])
					                + c2*(pos1[x][y-1] - 2*pos1[x][y] + pos1[x][y+1]);
				} else {
					pos0[x][y] = -pos2[x][y] + 2*pos1[x][y] 
				                   + c2*(pos1[x-1][y] + pos1[x+1][y] + pos1[x][y-1] + pos1[x][y+1] - 4*pos1[x][y]);
				}
			}
		}
	}
	
	public int getHeight() {
		return height;
	}

	public float[][] getPos() {
		return pos0;
	}

	public int getWidth() {
		return width;
	}

	public void setPos(int x, int y, int value) {
		pos0[x][y] = value;
	}
	
}
