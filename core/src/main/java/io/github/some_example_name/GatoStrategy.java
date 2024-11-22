package io.github.some_example_name;

public class GatoStrategy implements Interactuable{
	@Override
	public void interactuar(Ufo ufo) {
		if(!ufo.getPowerUpDash() && !ufo.getPowerUpAlien()) {
			ufo.setGatoVida(true);
		}
	}
}