package br.acousticsim.scenery;


/**
 * Material related to a vertex in AcousticGeometry.
 */
public abstract class Material {

	public abstract double getAbsorptionFactor(float frequency);
	
}
 
