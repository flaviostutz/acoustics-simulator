package sim;


public class Wave2DArea1 {

	private float[][] f;
	private float[][] df;
	private int width;
	private int height;
	
	public Wave2DArea1(int width, int height) {
		this.width = width;
		this.height = height;
		this.f = new float[width][height];
		this.df = new float[width][height];
	}
	
	public void step() {
		float velocity = 1F;

		for(int x=0; x<width; x++) {
			for(int y=0; y<height; y++) {
				f[x][y] += df[x][y];
			}
		}
		
		for(int x=1; x<width-1; x++) {
			for(int y=1; y<height-1; y++) {
				float avg = (f[x-1][y]+f[x+1][y]+f[x][y-1]+f[x][y+1])/4;
				df[x][y]-=velocity*(f[x][y]-avg);
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

	public void setPos(int x, int y, int value) {
		f[x][y] = value;
		df[x][y] = value;
	}
	
}
