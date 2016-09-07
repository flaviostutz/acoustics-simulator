package br.acousticsim.math.coordinates;

import javax.vecmath.Tuple3d;
import javax.vecmath.Tuple3f;

public class Tuple3fSpherical extends Tuple3f {

	
	
	public Tuple3fSpherical() {
		super();
	}

	public Tuple3fSpherical(Tuple3d arg0) {
		super(arg0);
	}

	public Tuple3fSpherical(Tuple3f arg0) {
		super(arg0);
	}

	public Tuple3fSpherical(float r, float theta, float phi) {
		updateInternalCartezian(r, theta, phi);
	}
	
	private void updateInternalCartezian(float r, float theta, float phi) {
		super.x = (float)(r * Math.cos(Math.toRadians(phi)) * Math.sin(Math.toRadians(theta)));
		super.y = (float)(r * Math.sin(Math.toRadians(phi)) * Math.sin(Math.toRadians(theta)));
		super.z = (float)(r * Math.cos(Math.toRadians(theta)));
	}
	
	public float getR() {
		return (float)Math.sqrt(Math.pow(super.x, 2) + Math.pow(super.y, 2) + Math.pow(super.z, 2));
	}
	
	public float getPhi() {
		return (float)Math.toDegrees(Math.atan(super.y/super.x));
	}
	
	public float getTheta() {
		return (float)Math.toDegrees(Math.acos(super.z/getR()));
	}
	
	private static final long serialVersionUID = 3256721771192725557L;

}
