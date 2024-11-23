package strategy;

import io.github.some_example_name.Ufo;

public class NeutralStrategy implements Interactuable{
	public void interactuar(Ufo ufo) {
		ufo.setGatoVida(false);
		ufo.setPowerUpAlien(false);
		ufo.setPowerUpDash(false);
	}
}
