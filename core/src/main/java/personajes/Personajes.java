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
    
}
