package personajes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import io.github.some_example_name.Ufo;

public class Doctor extends Personajes {
	private Sound doctor;
	
	public Doctor(float x, float y, float ancho, float alto) {
		super(x, y, ancho, alto, new Texture(Gdx.files.internal("doctor.png")));
		doctor = Gdx.audio.newSound(Gdx.files.internal("doctor.mp3"));
	}
	
	@Override
    public void dibujar(SpriteBatch batch) {
		batch.draw(getTextura(), getArea().x, getArea().y);
    }
	
	@Override
	public boolean touch(Ufo ufo) {
        if (super.touch(ufo)) {
        	ufo.activarLocura(150);
            doctor.play();
            return true; // Retorna true si ha tocado al UFO
        }
        return false; // Retorna false si no ha tocado al UFO
    }

}   
