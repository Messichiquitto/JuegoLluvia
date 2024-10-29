package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class Yunque extends Personajes {
	private Sound yunque;
	
	public Yunque(float x, float y, float ancho, float alto) {
		super(x, y, ancho, alto, new Texture(Gdx.files.internal("yunque.png")));
		yunque = Gdx.audio.newSound(Gdx.files.internal("yunque.mp3"));
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
