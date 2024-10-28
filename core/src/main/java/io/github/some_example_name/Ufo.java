package io.github.some_example_name;

/* Esta es la clase bucket original, pero se cambió y se adapto para tener el ufo. Esta todo bien.*/

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class Ufo {
    private Rectangle ufo;
    private Texture ufoImage;
    private Sound sonidoHerido;
    private int vidas = 3;
    private int puntos = 0;
    private int velx = 400;
    private boolean herido = false;
    private int tiempoHeridoMax = 50;
    private int tiempoHerido;

    public Ufo(Texture tex, Sound ss) {
        ufoImage = tex;
        sonidoHerido = ss;
        crear(); // Llama al método crear desde el constructor
    }

    //--------------------------------------------------------
    public int getVidas() {
        return vidas;
    }
    //--------------------------------------------------------    
    public int getPuntos() {
        return puntos;
    }
    //--------------------------------------------------------
    public Rectangle getArea() {
        return ufo;
    }
    //--------------------------------------------------------
    public void sumarPuntos(int pp) {
        puntos += pp;
    }
    //--------------------------------------------------------
    public void crear() {
        ufo = new Rectangle();
        ufo.x = 800 / 2 - 64 / 2;
        ufo.y = 465 - 64;
        ufo.width = 64;
        ufo.height = 64;
    }
    //--------------------------------------------------------
    public void damage() {
        vidas--;
        herido = true;
        tiempoHerido = tiempoHeridoMax;
        sonidoHerido.play();
    }
    //--------------------------------------------------------
    public void dibujar(SpriteBatch batch) {
        if (!herido)  
            batch.draw(ufoImage, ufo.x, ufo.y);
        else {
            batch.draw(ufoImage, ufo.x, ufo.y + MathUtils.random(-5, 5));
            tiempoHerido--;
            if (tiempoHerido <= 0) herido = false;
        }
    } 
    //--------------------------------------------------------	   	   
    public void actualizarMovimiento() { 
        // movimiento desde teclado
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) ufo.x -= velx * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) ufo.x += velx * Gdx.graphics.getDeltaTime();
        // que no se salga de los bordes izq y der
        if (ufo.x < 0) ufo.x = 0;
        if (ufo.x > 800 - 64) ufo.x = 800 - 64;
    }
    //--------------------------------------------------------
    public boolean estaHerido() {
        return herido;
    }
    //--------------------------------------------------------
    
    public Rectangle getRectangulo() {
        return ufo; // Devuelve el área de colisión del UFO
    }
}
