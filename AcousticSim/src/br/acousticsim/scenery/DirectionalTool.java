package br.acousticsim.scenery;

import javax.media.j3d.PickRay;
import javax.media.j3d.Shape3D;


/**
 * This class defines a directional tool.
 * The directional tool is primary a sphere and it has an aperture size, related to
 * its solid angle (in conical shape) and a radius.
 */
public class DirectionalTool extends Shape3D {

	float radius;
	int apertureAngle;
	PickRay direction;
	AcousticScenery acousticScenery;
	
	
	/**
	 * Constructor for the directional tool
	 * @param size Shape size of tool in space
	 * @param radius Radius of the sphere
	 * @param apertureAngle Aperture size of the solid angle in sphere
	 * @param direction Direction of the tool in space
	 */
	public DirectionalTool(AcousticScenery acousticScenery, float radius, int apertureAngle, PickRay direction) {
		super();
		this.acousticScenery = acousticScenery;
		this.radius = radius;
		this.apertureAngle = apertureAngle;
		this.direction = direction;
	}
	
	public int getApertureAngle() {
		return apertureAngle;
	}
	public PickRay getDirection() {
		return direction;
	}
	public float getRadius() {
		return radius;
	}
	public AcousticScenery getAcousticScenery() {
		return acousticScenery;
	}
	
	
}
 
