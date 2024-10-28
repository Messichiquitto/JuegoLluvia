package io.github.some_example_name;
/* Esta es la interface. En teoría está todo bien, pero si se cambia la idea de como el
 powerUp interactúa cone el jugador, entonces acá tmb. */
public interface Interactuable {
	void interactuar(Ufo ufo); //Interactua con el jugador
	void usarPoder(Ufo ufo, GameLluviaMenu game); //Activa el power up

}
