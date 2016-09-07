package br.acousticsim.scenery;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GraphicsConfiguration;

import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Transform3D;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.universe.SimpleUniverse;

public class SceneryUtil {

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
