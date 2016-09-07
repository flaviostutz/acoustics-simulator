package br.acousticsim.engine;

import br.acousticsim.math.IntersectionInfo;
import br.acousticsim.scenery.SoundListener;

public class ListenedSound {

	IntersectionInfo intersectionInfo;
	double rayIntensity;
	float distanceSoFar;
	float frequency;
	SoundListener soundListener;
	
	
	public ListenedSound(SoundListener soundListener, IntersectionInfo intersectionInfo, double rayIntensity, float distanceSoFar, float frequency) {
		super();
		this.soundListener = soundListener;
		this.intersectionInfo = intersectionInfo;
		this.rayIntensity = rayIntensity;
		this.distanceSoFar = distanceSoFar;
		this.frequency = frequency;
	}
	
	public SoundListener getSoundListener() {
		return soundListener;
	}

	public float getDistanceSoFar() {
		return distanceSoFar;
	}
	public float getFrequency() {
		return frequency;
	}
	public IntersectionInfo getIntersectionInfo() {
		return intersectionInfo;
	}
	public double getRayIntensity() {
		return rayIntensity;
	}
	
	public float getTime() {
		float vel = soundListener.getAcousticScenery().getAirSoundVelocity();
		return (distanceSoFar/vel);
	}
	
	public String toString() {
		return "soundListener=" + soundListener + "; intersectionInfo=" + intersectionInfo +
				"rayIntensity=" + rayIntensity + "; distanceSoFar=" + distanceSoFar +
				"frequency=" + frequency;
	}
	
}
