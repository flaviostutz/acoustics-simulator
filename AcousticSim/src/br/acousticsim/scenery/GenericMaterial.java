package br.acousticsim.scenery;

public class GenericMaterial extends Material {

	public GenericMaterial() {
		//TODO implement absorption factor based on linear regression of given values
	}
	
	@Override
	public double getAbsorptionFactor(float frequency) {
		return 0.5;
	}

}
