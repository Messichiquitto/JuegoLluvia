package strategy;

import io.github.some_example_name.Ufo;

public class AlienStrategy implements Interactuable {
	
	@Override
	public void interactuar(Ufo ufo) {
		if(!ufo.getPowerUpDash() && !ufo.getGatoVida()) {
			ufo.setPowerUpAlien(true);
		}
	}
}
