package io.github.some_example_name;

public class ZorroStrategy implements Interactuable{
	@Override
	public void interactuar(Ufo ufo) {
		if(!ufo.getGatoVida() && !ufo.getPowerUpAlien()) {
			ufo.setPowerUpDash(true);
		}
	}
}
