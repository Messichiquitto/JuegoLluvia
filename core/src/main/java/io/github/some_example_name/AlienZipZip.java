package io.github.some_example_name;
/*
La idea de esta clase era que al "matar" al enemigocriatura, dropeara el alien y que te diera el powerUp al tocar la tecla espacio.
como salió mal, si quieren descarten esta idea.

public class AlienZipZip {
    private float duracion;
    private float tiempo;

    public AlienZipZip(float duracion) {
        this.duracion = duracion;
        this.tiempo = 0;
    }

    public void activar(Ufo ufo, GameLluviaMenu game) {
        tiempo = duracion;
        int puntosActuales = ufo.getPuntos();
        ufo.sumarPuntos(puntosActuales); // Asigna puntos al Ufo
        game.setAsteroidesActivos(false); // Desactiva asteroides
    }

    public void actualizar(float delta) {
        if (tiempo > 0) {
            tiempo -= delta;
        }
    }

    public boolean isActivo() {
        return tiempo > 0; // Retorna si el power-up está activo
    }

    public void desactivar(Ufo ufo, GameLluviaMenu game) {            
        int puntosActuales = ufo.getPuntos();
        ufo.sumarPuntos(-puntosActuales / 2); // Reduce puntos al desactivar
    }
}
*/
