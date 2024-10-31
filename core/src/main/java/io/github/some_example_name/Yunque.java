package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Yunque extends Personajes {
	private Sound yunque;
	
	public Yunque(float x, float y, float ancho, float alto) {
		super(x, y, ancho, alto, new Texture(Gdx.files.internal("yunque.png")));
		yunque = Gdx.audio.newSound(Gdx.files.internal("yunque.mp3"));
	}
	
	@Override
    public void dibujar(SpriteBatch batch) {
		batch.draw(getTextura(), getArea().x, getArea().y);
    }
	
	@Override
	public boolean touch(Ufo ufo) {
        if (super.touch(ufo)) {
            ufo.instaKill();
            yunque.play();
            return true; // Retorna true si ha tocado al UFO
        }
        return false; // Retorna false si no ha tocado al UFO
    }
}
