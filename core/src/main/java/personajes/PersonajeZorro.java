/*
package personajes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import io.github.some_example_name.Ufo;
import strategy.Interactuable;
import strategy.ZorroStrategy;

public class PersonajeZorro extends Personajes implements Interactuable {
	private Sound zorroSound;
	private Texture texturaZorro;
	private float stateTime;
	private Texture texturaActual;
	private Texture texturaZorro2;
	private Interactuable estrategia;

    public PersonajeZorro(float x, float y, float ancho, float alto) {
		super(x, y, ancho, alto, new Texture(Gdx.files.internal("zorro.png")));
		texturaActual = new Texture(Gdx.files.internal("zorro.png"));
		texturaZorro = new Texture(Gdx.files.internal("zorro.png"));
        texturaZorro2 = new Texture(Gdx.files.internal("zorro2.png"));
		zorroSound = Gdx.audio.newSound(Gdx.files.internal("pickUpSound.mp3"));
		this.estrategia = new ZorroStrategy();
	}
    
    @Override
    public void dibujar(SpriteBatch batch) {
		stateTime += Gdx.graphics.getDeltaTime();
		
		if (stateTime >= 0.3f) {
	        if (texturaActual == texturaZorro) {
	            texturaActual = texturaZorro2;
	        } else {
	            texturaActual = texturaZorro;
	        }
	        stateTime = 0; // Reiniciar el tiempo de estado para el próximo cambio
	    }
		batch.draw(texturaActual, getArea().x, getArea().y);
    }
    
    @Override
	public boolean touch(Ufo ufo) {
        if (super.touch(ufo)) {
            zorroSound.play(0.5f);
            return true; // Retorna true si ha tocado al UFO
        }
        return false; // Retorna false si no ha tocado al UFO
    }
    
    public void interactuar(Ufo ufo) {
    	estrategia.interactuar(ufo);
    }
}
*/
package personajes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import io.github.some_example_name.Ufo;
import strategy.Interactuable;
import strategy.ZorroStrategy;

public class PersonajeZorro extends Personajes implements Interactuable{
	private Sound sonidoZorro;
	private Interactuable estrategia;
	
	public PersonajeZorro(float x, float y, float ancho, float alto) {
		super(x, y, ancho, alto, new Texture(Gdx.files.internal("zorro.png")));
        sonidoZorro = Gdx.audio.newSound(Gdx.files.internal("pickUpSound.mp3"));
        this.estrategia = new ZorroStrategy();
	}
	
	@Override
	public void dibujar(SpriteBatch batch) {
		batch.draw(getTextura(), getArea().x, getArea().y);
	}
	
	@Override
	public void realizarAccionEspecifica(Ufo ufo) {
		estrategia.interactuar(ufo);
	}
	
	@Override
	protected Sound getSonido() {
		return sonidoZorro;
	}
	
	public void setEstrategia(Interactuable nuevaEstrategia) {
		this.estrategia = nuevaEstrategia;
	}

	@Override
	public void interactuar(Ufo ufo) {
		this.estrategia.interactuar(ufo);
		
	}
}