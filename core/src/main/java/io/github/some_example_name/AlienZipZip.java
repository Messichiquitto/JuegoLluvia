package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class AlienZipZip extends Personajes implements Interactuable {
	
	private Sound zipzip;
	
	public AlienZipZip(float x, float y, float ancho, float alto) {
		super(x, y, ancho, alto, new Texture(Gdx.files.internal("alienZipZip.png")));
		zipzip = Gdx.audio.newSound(Gdx.files.internal("alien.mp3"));
	}
	
	@Override
	public void interactuar(Ufo ufo) {
		
	}
}
