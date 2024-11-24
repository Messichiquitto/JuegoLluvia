package personajes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import io.github.some_example_name.Ufo;
import strategy.AlienStrategy;
import strategy.Interactuable;

public class AlienZipZip extends Personajes implements Interactuable {
    private Sound pickUpSound;
    private Texture texturaAlienZipZip2;
    private float stateTime;
    private Texture texturaActual;
    private Texture texturaAlienZipZip;
    private Interactuable estrategia;

    public AlienZipZip(float x, float y, float ancho, float alto) {
        super(x, y, ancho, alto, new Texture(Gdx.files.internal("alienZipZip.png")));
        texturaActual = new Texture(Gdx.files.internal("alienZipZip.png"));
        texturaAlienZipZip = new Texture(Gdx.files.internal("alienZipZip.png"));
        texturaAlienZipZip2 = new Texture(Gdx.files.internal("alienZipZip2.png"));
        pickUpSound = Gdx.audio.newSound(Gdx.files.internal("pickUpSound.mp3"));
        this.estrategia = new AlienStrategy();

    }
    //--------------------------------------------------------------
    public void interactuar(Ufo ufo) {
        estrategia.interactuar(ufo);
    }
    //--------------------------------------------------------------
    @Override
    public void dibujar(SpriteBatch batch) {
    	actualizarTextura();
    	batch.draw(texturaActual, getArea().x, getArea().y, 47, 63);
    }
    //--------------------------------------------------------------
    protected void actualizarTextura() {
	    stateTime += Gdx.graphics.getDeltaTime();

	    if (stateTime >= 0.3f) {
	        if (texturaActual == texturaAlienZipZip) {
	            texturaActual = texturaAlienZipZip2;
	        } else {
	            texturaActual = texturaAlienZipZip;
	        }
	        stateTime = 0;
	    }
	}
    //--------------------------------------------------------------
    @Override
    public void realizarAccionEspecifica(Ufo ufo) {
    	interactuar(ufo);
    }
    //--------------------------------------------------------------
    @Override
    protected Sound getSonido() {
    	return pickUpSound;
    }
    //--------------------------------------------------------------
}
