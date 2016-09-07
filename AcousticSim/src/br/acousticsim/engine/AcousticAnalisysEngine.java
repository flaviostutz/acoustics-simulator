package br.acousticsim.engine;

import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import javax.media.j3d.GeometryArray;
import javax.media.j3d.PickRay;
import javax.media.j3d.Shape3D;

import br.acousticsim.math.Vectors;
import br.acousticsim.scenery.AcousticGeometry;
import br.acousticsim.scenery.AcousticScenery;
import br.acousticsim.scenery.SoundListener;
import br.acousticsim.scenery.SoundSource;

/**
 * Performs the necessary operations to analyse a scenery, based on its objects,
 * SoundSources and SoundListeners.
 */
public class AcousticAnalisysEngine {

	public static int MAX_RAY_REFLECTIONS = 4;

	/**
	 * Runs the acoustical analysis in scenery
	 * 
	 * @param scenery
	 *            Scenery to be analysed
	 * @param resolution
	 *            Number of rays coming out of the sound sources per cm2
	 */
	public static void runAnalysis(AcousticScenery scenery, float resolution, float frequencyStep, float minFreq, float maxFreq) {
		System.out.println("Analysing scenery");

		Set<SoundSource> soundSources = new HashSet<SoundSource>();
		Set<SoundListener> soundListeners = new HashSet<SoundListener>();

		Vector<Shape3D> shapes = scenery.getAllShapes();

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

			// looping for analysing a range of frequencies
			for(float frequency=minFreq; frequency<=maxFreq; frequency+=frequencyStep) {
			
				// looping for the rays that come out of the soundSource
				Vector<PickRay> rays = ss.getRays(resolution);
				for (PickRay ray : rays) {
	
					System.out.println("Computing reflections");
					ReflectionsResult rr = ReflectionEngine.computeRayReflections(shapes, MAX_RAY_REFLECTIONS, ray);
	
					double rayIntensity = 1; //max intensity
					float distanceSoFar = 0;
					
					System.out.println("Analysing reflection's acoustic behaviour");
					// loop in sound reflections
					for (ReflectionInfo ri : rr.getReflections()) {
	
						// add the distance from the last point to the current point of reflection
						float dist = Math.abs(Vectors.getRayPoint(ri.getIntersectionInfo().getRay()).distance(ri.getIntersectionInfo().getPoint()));
						distanceSoFar += dist;
						
						// verify if the reflecting shape is a SoundListener
						for (SoundListener sl : soundListeners) {
							
							if (ri.getIntersectionInfo().getShape() == sl) {
	
								ListenedSound listenedSound = new ListenedSound(
																	sl,
																	ri.getIntersectionInfo(),
																	rayIntensity,
																	distanceSoFar,
																	frequency);
								
								sl.addListenedSound(ss, listenedSound);

							}
							
						}
						
						// threat sound reflection loss of energy in absorptions
						GeometryArray geometry = ri.getIntersectionInfo().getGeometry();
						if(geometry instanceof AcousticGeometry) {
							AcousticGeometry ag = (AcousticGeometry)geometry;
							rayIntensity *= (1 - ag.getAbsorptionFactor(frequency));
							
						// apply default absorption factor
						} else {
							rayIntensity *= 0.7;
							
						}
						
						//TODO treat sound refraction here
						//HERE!

						//TODO treat sound diffraction here
						//HERE!
					}
				}
			}
		}
	}

}
