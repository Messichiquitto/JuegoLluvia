package personajes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import io.github.some_example_name.Ufo;
import strategy.GatoStrategy;
import strategy.Interactuable;

public class GatoMistico extends Personajes implements Interactuable{
    private Sound gatoSound;
    private Interactuable estrategia;

    public GatoMistico(float x, float y, float ancho, float alto) {
        super(x, y, ancho, alto, new Texture(Gdx.files.internal("gatoMistico.png")));
        gatoSound = Gdx.audio.newSound(Gdx.files.internal("pickUpSound.mp3"));
        this.estrategia = new GatoStrategy();
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
        return gatoSound;
    }

	@Override
	public void interactuar(Ufo ufo) {
		estrategia.interactuar(ufo);
		
	}

}
