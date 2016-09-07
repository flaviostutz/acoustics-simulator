package br.acousticsim.math;

public class Physics {

	public static float getAirSoundVelocity(float celsiusTemp) {
//		return (float)(331.4 + 0.6*celsiusTemp);
		return (float)(331.4 + 0.6*celsiusTemp)/10;//for testing
	}

}
