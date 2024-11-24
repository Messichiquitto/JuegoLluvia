package personajes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import io.github.some_example_name.Ufo;

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
	public void realizarAccionEspecifica(Ufo ufo) {
		ufo.instaKill();
	}
	
	@Override
	protected Sound getSonido() {
		return yunque;
	}
	
}
