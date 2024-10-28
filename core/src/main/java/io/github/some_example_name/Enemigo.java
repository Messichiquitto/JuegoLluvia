package io.github.some_example_name;
/*
Esta clase es la abstracta que representa a los enemigos del juego. La clase implementa la interface interactuable, que es la accion
que tienen los enemigos con el jugador.
La clase está basada en la idea que les dije por wsp, así que modifiquen lo que quieran nomas esto, mientras se mantenga la interfaz
para el avance, todo bien.

public abstract class Enemigo implements Interactuable {
    protected int vida;
    protected int toques;
    protected int maxToques;
    protected float x; // Coordenada X
    protected float y; // Coordenada Y

    public Enemigo(int vida, int maxToques) {
        this.vida = vida;
        this.maxToques = maxToques;
        this.toques = 0;
    }

    public abstract void interactuar(Ufo ufo);

    public void recibirToque() {
        toques++;
        if (toques >= maxToques) {
            morir();
        }
    }

    protected void morir() {
    	//Ac
        soltarPowerUp();
    }

    protected abstract void soltarPowerUp();

    public void setPosicion(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public abstract void usarPoder(Ufo ufo, GameLluviaMenu game);
}
*/
