package personajes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import io.github.some_example_name.Ufo;

public abstract class Personajes {
    private Rectangle area;
    private Texture textura;

    public Personajes(float x, float y, float ancho, float alto, Texture textura) {
        this.area = new Rectangle(x, y, ancho, alto);
        this.textura = textura;
    }

    public final void interactuarConUfo(Ufo ufo) {
    	if (this.touch(ufo)) {
    		this.interaccionSonido(); //Este sería el sonido que tienen en común todos los personajes
    		this.actualizarTextura(); //Este sería la animación de la textura que tienen ciertos personajes
    		this.realizarAccionEspecifica(ufo); //Esta sería la llamada a la acción específica del personaje
    	}
    }
    
    public void interaccionSonido() {
    	if (getSonido() != null) {
    		getSonido().play(0.5f);
    	}
    }
    
    private void actualizarTextura() {
    	
    }
    
    //Esto es la acción específica que realiza cada personaje
    public abstract void realizarAccionEspecifica(Ufo ufo);
    
    
    public Rectangle getArea() {
    	return area;
    }
    
    public abstract void dibujar(SpriteBatch batch);
    
    public Texture getTextura() {
    	return textura;
    }
   
    public boolean touch(Ufo ufo) {
    	return area.overlaps(ufo.getArea());
    }
    
    protected abstract Sound getSonido();
    
}
