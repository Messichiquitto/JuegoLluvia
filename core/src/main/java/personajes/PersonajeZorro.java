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