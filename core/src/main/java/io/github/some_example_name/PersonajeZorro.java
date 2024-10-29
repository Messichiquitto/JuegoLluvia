package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;

public class PersonajeZorro extends Personajes implements Interactuable {

    public PersonajeZorro(float x, float y, float ancho, float alto) {
		super(x, y, ancho, alto, new Texture(Gdx.files.internal("zorro.png")));
	}
    
    public void interactuar(Ufo ufo) {
    	ufo.interaccionZorro();
    }
}
