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
    private int segundosMovimiento; // Duracion del powerUp
    private boolean movimiento;
    private Sound dashUfo;
    private Sound alienZip;
    private boolean powerUpDash;
    private boolean powerUpAlien;
    private boolean invulnerable;
    private int segundosInvulnerable;
    
    public Ufo(Texture tex, Sound ss) {
        ufoImage = tex;
        sonidoHerido = ss;
        crear();
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
    public boolean damage() {
    	if(!invulnerable) {
    		vidas--;
            herido = true;
            tiempoHerido = tiempoHeridoMax;
            sonidoHerido.play();
            return true;
    	}
    	return false;
    }
    //--------------------------------------------------------
    public void instaKill() {
    	vidas -= 50;
    }
    //--------------------------------------------------------
    public void dibujar(SpriteBatch batch) {
        if(invulnerable) {
        	batch.draw(new Texture(Gdx.files.internal("ufoBuff.png")), ufo.x, ufo.y);
        	segundosInvulnerable -= Gdx.graphics.getDeltaTime();
        	if(segundosInvulnerable <= 0) {
        		invulnerable = false;
        	}
        }
        else {
        	if (!herido)  {
                if (powerUpDash  || powerUpAlien) {
                	batch.draw(new Texture(Gdx.files.internal("ufoBuff.png")), ufo.x, ufo.y);
                } else {
            		batch.draw(ufoImage, ufo.x, ufo.y);
            	}
            }
            else {
            	if (powerUpDash || powerUpAlien) {
            		batch.draw(new Texture(Gdx.files.internal("ufoBuff.png")), ufo.x, ufo.y + MathUtils.random(-5, 5));
            	}
            	else {
            		batch.draw(ufoImage, ufo.x, ufo.y + MathUtils.random(-5, 5));
            	}
                tiempoHerido--;
                if (tiempoHerido <= 0) herido = false;
            }
        }
        	
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
        	if(powerUpDash) {
        		activarMovimiento(10);
            	powerUpDash = false;
        	}
        	else if(powerUpAlien) {
        		activarInvulnerabilidad(100);
        		powerUpAlien = false;
        	}
        }
        
        if (movimiento) {
        	batch.draw(new Texture(Gdx.files.internal("ufoBuff.png")), ufo.x, ufo.y);
        	actualizarMovimiento();
        }
        limitarMovimiento();
    } 
    //--------------------------------------------------------
    private void limitarMovimiento() {
        if (ufo.x < 0) ufo.x = 0;
        if (ufo.x > 800 - 64) ufo.x = 800 - 64;
        if (ufo.y < 0) ufo.y = 0;
        if (ufo.y > 480 - 64) ufo.y = 480 - 64;
    }
    //--------------------------------------------------------	   	   
    public void actualizarMovimiento() { 
    	if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) ufo.x -= velx * Gdx.graphics.getDeltaTime();
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) ufo.x += velx * Gdx.graphics.getDeltaTime();

    	if (movimiento) {
    		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) ufo.x -= velx * Gdx.graphics.getDeltaTime();
    		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) ufo.x += velx * Gdx.graphics.getDeltaTime();

    		segundosMovimiento -= Gdx.graphics.getDeltaTime();
    		if (segundosMovimiento <= 0) {
    			movimiento = false; // Termina el movimiento
    		}
    	}
    }
    //--------------------------------------------------------
    public boolean estaHerido() {
        return herido;
    }
    //--------------------------------------------------------
    public Rectangle getRectangulo() {
        return ufo; // Devuelve el área de colisión del UFO
    }
    //--------------------------------------------------------
    public void activarMovimiento(int segundos) {
    	segundosMovimiento = segundos;
    	movimiento = true;
    	dashUfo = Gdx.audio.newSound(Gdx.files.internal("dashUfo.mp3"));
    	dashUfo.play();
    }
    //--------------------------------------------------------
    public void interaccionZorro() {
    	if(!powerUpAlien) {
    		powerUpDash = true;
    	}
    }
    //--------------------------------------------------------
    public void interaccionAlien() {
    	if(!powerUpDash) {
    		powerUpAlien = true;
    	}
    }
    //--------------------------------------------------------
    private void activarInvulnerabilidad(int segundos) {
        invulnerable = true; // Activa la invulnerabilidad
        segundosInvulnerable = segundos;
        alienZip = Gdx.audio.newSound(Gdx.files.internal("sonidoZipZip.mp3"));
        alienZip.play();
    }
}

