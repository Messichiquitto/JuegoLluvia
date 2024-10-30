package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Vaca extends Personajes {
	private Sound sonidoAbduction;
	
	public Vaca(float x, float y, float ancho, float alto) {
		super(x, y, ancho, alto, new Texture(Gdx.files.internal("vaca.png")));
		sonidoAbduction = Gdx.audio.newSound(Gdx.files.internal("sonidoAbduction.mp3"));
	}
	
	@Override
    public void dibujar(SpriteBatch batch) {
		batch.draw(getTextura(), getArea().x, getArea().y);
    }
	
	@Override
	public boolean touch(Ufo ufo) {
        // Llamamos al método touch de Personajes para verificar si se superponen
        if (super.touch(ufo)) {
            // Si hay colisión, sumamos puntos al UFO
            ufo.sumarPuntos(10);
            sonidoAbduction.play();
            return true; // Retorna true si ha tocado al UFO
        }
        return false; // Retorna false si no ha tocado al UFO
    }

}

