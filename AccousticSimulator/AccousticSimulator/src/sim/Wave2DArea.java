package sim;


public class Wave2DArea {

	private static final int MEDIUM_MAX = 191;
	private static final double MEDIUM_MAX_INDEX = .5;
	
	private float[][] f;
	private float[][] fi;
	private float[][] medium;
	private boolean[][] walls;
	private float[][] damp;
	private int width;
	private int height;
	
	public Wave2DArea(int width, int height) {
		this.width = width;
		this.height = height;
		this.f = new float[width][height];
		this.fi = new float[width][height];
		this.medium = new float[width][height];
		this.walls = new boolean[width][height];
		this.damp = new float[width][height];

		for(int x=0; x<width; x++) {
			for(int y=0; y<height; y++) {
				damp[x][y] = 1F;
			}
		}
	}
	
	public void step() {
		float velocity = 0.25F;
		float dampcoef = 1F;

		for(int x=1; x<width-1; x++) {
			for(int y=1; y<height-1; y++) {
				
				//tadd2 = velocity in current medium
				double tadd2 = velocity * (1 - (MEDIUM_MAX_INDEX / MEDIUM_MAX) * medium[x][y]);
				float sinth = (float)(Math.sin(tadd2) * dampcoef);
				float scaleo = (float)Math.cos(tadd2);

				float avg = (f[x-1][y]+f[x+1][y]+f[x][y-1]+f[x][y+1])/4;
				float a = (f[x][y] - avg) * damp[x][y];
				float b = fi[x][y] * damp[x][y];
				f[x][y] = avg + a * scaleo - b * sinth;
				fi[x][y] = b * scaleo + a * sinth;
			}
		}
	}
	
	public int getHeight() {
		return height;
	}

	public float[][] getPos() {
		return f;
	}

	public int getWidth() {
		return width;
	}

	public void setPos(int x, int y, float value) {
		f[x][y] = value;
		fi[x][y] = value;
	}
	
}
