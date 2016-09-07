package br.acousticsim.scenery;

import java.util.HashMap;
import java.util.Set;
import java.util.Vector;

import javax.media.j3d.PickRay;
import javax.vecmath.Point3f;

import br.acousticsim.engine.ListenedSound;
import br.acousticsim.engine.SoundListenerAnalisys;
import br.acousticsim.math.Vectors;

import com.sun.j3d.utils.geometry.ColorCube;


/**
 *Input source placed in space for calculations of the acoustic characteristics in a certain point.
 */
public class SoundListener extends DirectionalTool {
 
	SoundListenerAnalisys soundListenerAnalisys;
	HashMap<SoundSource, Vector<ListenedSound>> listenedSounds;
	AcousticScenery acousticScenery;
	
	public SoundListener(AcousticScenery acousticScenery, float radius, int apertureAngle, PickRay direction) {
		super(acousticScenery, radius, apertureAngle, direction);
		this.acousticScenery = acousticScenery;
		listenedSounds = new HashMap<SoundSource, Vector<ListenedSound>>();

		//tool appearance
		Point3f origin = Vectors.getRayPoint(direction);
		//GeometryArray g = createListenerGeometry(radius, apertureAngle, direction);

		//TODO create apropriate geometry
		ColorCube c = new ColorCube(radius);
		setGeometry(c.getGeometry());
	}

	/*private GeometryArray createListenerGeometry(float radius, int apertureAngle, PickRay direction) {
		
	    final float[] verts = {
			// front face
			 1.0f, -1.0f,  1.0f,
			 1.0f,  1.0f,  1.0f,
			-1.0f,  1.0f,  1.0f,
			-1.0f, -1.0f,  1.0f,
			// back face
			-1.0f, -1.0f, -1.0f,
			-1.0f,  1.0f, -1.0f,
			 1.0f,  1.0f, -1.0f,
			 1.0f, -1.0f, -1.0f,
			// right face
			 1.0f, -1.0f, -1.0f,
			 1.0f,  1.0f, -1.0f,
			 1.0f,  1.0f,  1.0f,
			 1.0f, -1.0f,  1.0f,
			// left face
			-1.0f, -1.0f,  1.0f,
			-1.0f,  1.0f,  1.0f,
			-1.0f,  1.0f, -1.0f,
			-1.0f, -1.0f, -1.0f,
			// top face
			 1.0f,  1.0f,  1.0f,
			 1.0f,  1.0f, -1.0f,
			-1.0f,  1.0f, -1.0f,
			-1.0f,  1.0f,  1.0f,
			// bottom face
			-1.0f, -1.0f,  1.0f,
			-1.0f, -1.0f, -1.0f,
			 1.0f, -1.0f, -1.0f,
			 1.0f, -1.0f,  1.0f,
		    };

	     final float[] colors = {
			// front face (red)
			1.0f, 0.0f, 0.0f,
			1.0f, 0.0f, 0.0f,
			1.0f, 0.0f, 0.0f,
			1.0f, 0.0f, 0.0f,
			// back face (green)
			0.0f, 1.0f, 0.0f,
			0.0f, 1.0f, 0.0f,
			0.0f, 1.0f, 0.0f,
			0.0f, 1.0f, 0.0f,
			// right face (blue)
			0.0f, 0.0f, 1.0f,
			0.0f, 0.0f, 1.0f,
			0.0f, 0.0f, 1.0f,
			0.0f, 0.0f, 1.0f,
			// left face (yellow)
			1.0f, 1.0f, 0.0f,
			1.0f, 1.0f, 0.0f,
			1.0f, 1.0f, 0.0f,
			1.0f, 1.0f, 0.0f,
			// top face (magenta)
			1.0f, 0.0f, 1.0f,
			1.0f, 0.0f, 1.0f,
			1.0f, 0.0f, 1.0f,
			1.0f, 0.0f, 1.0f,
			// bottom face (cyan)
			0.0f, 1.0f, 1.0f,
			0.0f, 1.0f, 1.0f,
			0.0f, 1.0f, 1.0f,
			0.0f, 1.0f, 1.0f,
		    };

			QuadArray qa = new QuadArray(4, QuadArray.COORDINATES);
		 	qa.setCoordinates(0, verts);
			qa.setColors(0, colors);
			
			return qa;
		
	}*/

	public void addListenedSound(SoundSource soundSource, ListenedSound listenedSound) {
		Vector<ListenedSound> ls = listenedSounds.get(soundSource);
		if(ls==null) {
			ls = new Vector<ListenedSound>();
			listenedSounds.put(soundSource, ls);
		}
		ls.add(listenedSound);
	}

	/**
	 * Get the frequency spectrum for a desired sound source
	 * @param ss
	 * @return
	 */
	public HashMap<Float, Double> getFrequencySpectrum(SoundSource ss) {
		return calculateSpectrum(listenedSounds.get(ss));
	}


	/**
	 * Get the overall frequency spectrum for all sound sources
	 * @return
	 */
	public HashMap<Float, Double> getFrequencySpectrum() {
		Vector<ListenedSound> allListenedSounds = new Vector<ListenedSound>();
		
		Set<SoundSource> k = listenedSounds.keySet();
		for(SoundSource ss: k) {
			allListenedSounds.addAll(listenedSounds.get(ss));
		}
		return calculateSpectrum(allListenedSounds);
	}
	
	private HashMap<Float, Double> calculateSpectrum(Vector<ListenedSound> lss) {
		HashMap<Float, Double> frequencySpectrum = new HashMap<Float, Double>();
		
		for(ListenedSound ls: lss) {
			Double intensity = frequencySpectrum.get(ls.getFrequency());
			//first interaction over this frequency
			if(intensity==null) {
				frequencySpectrum.put(ls.getFrequency(), ls.getRayIntensity());
			
			//calculate average value	
			} else {
				frequencySpectrum.put(ls.getFrequency(), (ls.getRayIntensity()+intensity)/2);
				
			}
		}
		return frequencySpectrum;
	}
	
	public HashMap<SoundSource, Vector<ListenedSound>> getListenedSounds() {
		return listenedSounds;
	}
	
}
 
