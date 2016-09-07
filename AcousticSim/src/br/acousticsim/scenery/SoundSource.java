package br.acousticsim.scenery;

import java.util.Vector;

import javax.media.j3d.PickRay;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3d;

import br.acousticsim.math.Vectors;
import br.acousticsim.math.coordinates.Tuple3fSpherical;

import com.sun.j3d.utils.geometry.Sphere;


/**
 *Sound source in space for performing analisys.
 */
public class SoundSource extends DirectionalTool {

	/**
	 * Number between 0 and 1 indicating a multiplier for intensity
	 */
	float intensity;

	/**
	 * 
	 * @param acousticalScenery Scenery containing this tool
	 * @param radius Radius of Sound Source (spherical format)
	 * @param apertureAngle A value from 0-180 indicating the angle of aperture from the direction to form a cone
	 * @param direction Point of origin and direction of this tool
	 * @param intensity Value from 0-1 indicating the strength of this sound source
	 */
	public SoundSource(AcousticScenery acousticalScenery, float radius, int apertureAngle, PickRay direction, float intensity) {
		super(acousticalScenery, radius, apertureAngle, direction);
		if(intensity<0 || intensity>1) {
			throw new IllegalArgumentException("Intensity must have a value between 0 and 1");
		}
		this.intensity = intensity;

	

		//tool appearance
		Point3f origin = Vectors.getRayPoint(direction);
		
		Sphere c = new Sphere(radius);

		//put the sphere at the place of this tool
		TransformGroup tg = new TransformGroup();
		Transform3D t = new Transform3D();
		t.setTranslation(new Vector3d(new Point3d(origin)));
		tg.setTransform(t);
		tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		tg.addChild(c);
		
		acousticScenery.getBranchGroup().addChild(tg);
	}

	/**
	 * Returns the rays related to the sound source in a desired resolution.
	 * @param resolution Number of rays per m2 coming out of the sound source's surface
	 * @return
	 */
	public Vector<PickRay> getRays(float resolution) {
		Vector<PickRay> v = new Vector<PickRay>();

		//work with spherical coordinates
		Tuple3fSpherical dir = new Tuple3fSpherical(Vectors.getRayVector(direction));
		Tuple3fSpherical origin = new Tuple3fSpherical(Vectors.getRayPoint(direction));
		
		//calculate the distance (in degrees) between rays based on radius and resolution
		double rayArea = 1/resolution;
		double deltaTheta = Math.sqrt(rayArea)/radius;
		
		if(deltaTheta<=0) {
			System.err.println("Infinite rays to be drawn. Quiting!");
			return v;
		}
		if(deltaTheta>180 || deltaTheta == Double.NaN) {
			System.out.println("No sound waves can be drawn according to resolution");
			return v;
		}
		
		
		//iterate over each position by incremeting theta and phi
		for(float t=0.01F; t<apertureAngle; t+=deltaTheta) {

			for(float p=0; p<360; p+=(deltaTheta/Math.sin(Math.toRadians(t)))) {
				
				//TODO Apply the direction to rays!!!
				Tuple3fSpherical d = new Tuple3fSpherical(1F, t, p);
				v.add(new PickRay(new Point3d(origin), new Vector3d(d)));
				
			}
			
		}
		
		return v;
	}
 
}
 
