package personajes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import io.github.some_example_name.Ufo;

public class Asteroide extends Personajes {
	private Sound hurt;
	
	public Asteroide(float x, float y, float ancho, float alto) {
		super(x, y, ancho, alto, new Texture(Gdx.files.internal("asteroide.png")));
		hurt = Gdx.audio.newSound(Gdx.files.internal("hurt.mp3"));
	}
	
	@Override
    public void dibujar(SpriteBatch batch) {
		batch.draw(getTextura(), getArea().x, getArea().y);
    }
	
	@Override
	public void realizarAccionEspecifica(Ufo ufo) {
		ufo.damage();
	}
	
	protected Sound getSonido() {
		return hurt;
	}
	
	/*
	@Override
	public boolean touch(Ufo ufo) {
        if (super.touch(ufo)) {
            if(ufo.damage()) {
            	hurt.play(0.5f);
                return true; // Retorna true si ha tocado al UFO
            }
            return true;
        }
        return false; // Retorna false si no ha tocado al UFO
    }
    */
}
