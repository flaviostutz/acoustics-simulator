package br.acousticsim.scenery;

import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.PickRay;
import javax.media.j3d.Shape3D;

import br.acousticsim.engine.AcousticAnalisysEngine;
import br.acousticsim.engine.ReflectionEngine;
import br.acousticsim.engine.ReflectionsResult;
import br.acousticsim.math.Geometries;
import br.acousticsim.math.Physics;

public class AcousticScenery {
	SceneryAnalysis sceneryAnalysis;
	BranchGroup branchGroup;
	float airTemp = 25;//default temperature
	
	public AcousticScenery() {
		branchGroup = new BranchGroup();
		sceneryAnalysis = null;
	}
	
	public void setAirTemperature(float airTempCelsius) {
		this.airTemp = airTempCelsius;
	}
	
	public float getAirSoundVelocity() {
		return Physics.getAirSoundVelocity(airTemp);
	}

	public BranchGroup getBranchGroup() {
		return branchGroup;
	}

	public Vector<Shape3D> getAllShapes() {
		return Geometries.getAllShapes(branchGroup);
	}
	
	public void createWaveLines(float resolution) {
		Set<SoundSource> soundSources = new HashSet<SoundSource>();
		Set<SoundListener> soundListeners = new HashSet<SoundListener>();

		Vector<Shape3D> shapes = getAllShapes();

		// find sound sources and sound listeners in scenery
		for (Shape3D shape : shapes) {

			if (shape instanceof SoundSource) {
				soundSources.add((SoundSource) shape);

			} else if (shape instanceof SoundListener) {
				soundListeners.add((SoundListener) shape);

			}
		}

		// calculate sound reflections for each sound source + sound listener

		// each soundSource
		for (SoundSource ss : soundSources) {
			// looping for the rays that come out of the soundSource
			Vector<PickRay> rays = ss.getRays(resolution);
			for (PickRay ray : rays) {
				ReflectionsResult rr = ReflectionEngine.computeRayReflections(shapes, AcousticAnalisysEngine.MAX_RAY_REFLECTIONS, ray);
				ReflectionEngine.addRayReflectionLinesToGroup(rr, branchGroup);
			}
		}
	}

}
