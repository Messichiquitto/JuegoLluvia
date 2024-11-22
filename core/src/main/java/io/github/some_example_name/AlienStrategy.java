package io.github.some_example_name;

public class AlienStrategy implements Interactuable{
	@Override
	public void interactuar(Ufo ufo) {
		if(!ufo.getPowerUpDash() && !ufo.getGatoVida()) {
			ufo.setPowerUpAlien(true);
		}
	}
}
