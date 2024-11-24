package personajes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import io.github.some_example_name.Ufo;

public class VacaDorada extends Personajes {
	private Sound sonidoVacaDorada;
	private Texture texturaVaca2;
	private float stateTime;
	private Texture texturaActual;
	private Texture texturaVaca;
	
	public VacaDorada(float x, float y, float ancho, float alto) {
		super(x, y, ancho, alto, new Texture(Gdx.files.internal("vacaDorada.png")));
		texturaActual = new Texture(Gdx.files.internal("vacaDorada.png"));
		texturaVaca = new Texture(Gdx.files.internal("vacaDorada.png"));
        texturaVaca2 = new Texture(Gdx.files.internal("vacaDorada2.png"));
		sonidoVacaDorada = Gdx.audio.newSound(Gdx.files.internal("sonidoVacaDorada.mp3"));
	}
	//--------------------------------------------------------------
	@Override
    public void dibujar(SpriteBatch batch) {
        actualizarTextura();
        batch.draw(texturaActual, getArea().x, getArea().y, 64, 39);
    }
	//--------------------------------------------------------------
	protected void actualizarTextura() {
	    stateTime += Gdx.graphics.getDeltaTime();

	    if (stateTime >= 0.3f) {
	        if (texturaActual == texturaVaca) {
	            texturaActual = texturaVaca2;
	        } else {
	            texturaActual = texturaVaca;
	        }
	        stateTime = 0;
	    }
	}
	//--------------------------------------------------------------
	@Override
	public void realizarAccionEspecifica(Ufo ufo) {
		ufo.sumarPuntos(500);
	}
	//--------------------------------------------------------------
	@Override
	protected Sound getSonido() {
		return sonidoVacaDorada;
	}
	//--------------------------------------------------------------
}