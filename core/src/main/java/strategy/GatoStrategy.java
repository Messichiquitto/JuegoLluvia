package strategy;

import io.github.some_example_name.Ufo;

public class GatoStrategy implements Interactuable{
	@Override
	public void interactuar(Ufo ufo) {
		if(!ufo.getPowerUpDash() && !ufo.getPowerUpAlien()) {
			ufo.setGatoVida(true);
		}
	}
}