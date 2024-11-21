package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class Oleada {
	private Array<Personajes> personajesPos;
    private long lastDropTime;
    private Sound dropSound;
    private Music rainMusic;
    //--------------------------------------------------------------   
	public Oleada(Sound ss, Music mm) {
		rainMusic = mm;
		dropSound = ss;
	}
	//--------------------------------------------------------------
	public void crear() {
		personajesPos = new Array<Personajes>();
		rainMusic.setLooping(true);
	    rainMusic.play();
	}
	//--------------------------------------------------------------
	private void crearPrincipales() {
		//Acá se crearan las vacas y asteroides (principales)
		if (MathUtils.random(1, 10) < 4) {
			Asteroide asteroide = new Asteroide(MathUtils.random(0, 800 - 64), 0, 64, 64);
			personajesPos.add(asteroide);
		} else {
			Vaca vaca = new Vaca(MathUtils.random(0, 800 - 64), 0, 64, 64);
			personajesPos.add(vaca);
		}
	    lastDropTime = TimeUtils.nanoTime();
	}
	//--------------------------------------------------------------
	private void crearPersonajes() {
		double probabilidad = MathUtils.random(0f, 100f);
		if (probabilidad <= 10 && probabilidad < 15) {
			PersonajeZorro zorro = new PersonajeZorro(MathUtils.random(0, 800 - 64), 0, 64, 64);
			personajesPos.add(zorro);
		}
		if (probabilidad <= 1 && probabilidad < 5) {
			VacaDorada vacaDorada = new VacaDorada(MathUtils.random(0, 800 - 64), 0, 64, 64);
			personajesPos.add(vacaDorada);
		}
		if (probabilidad <= 3 && probabilidad < 6) {
			Yunque yunque = new Yunque(MathUtils.random(0, 800 - 64), 0, 64, 64);
			personajesPos.add(yunque);
		}
		if (probabilidad <= 7 && probabilidad < 9) {
			AlienZipZip alien = new AlienZipZip(MathUtils.random(0, 800 - 64), 0, 64, 64);
			personajesPos.add(alien);
		}
		if (probabilidad <= 10 && probabilidad < 15) {
			Doctor doctor = new Doctor(MathUtils.random(0, 800 - 64), 0, 64, 64);
			personajesPos.add(doctor);
		}
		if (probabilidad <= 2) {
			GatoMistico gatoMistico = new GatoMistico(MathUtils.random(0, 800 - 64), 0, 64, 64);
			personajesPos.add(gatoMistico);
		}
		else {
			crearPrincipales();
		}
	}
   //--------------------------------------------------------------
   public boolean actualizarMovimiento(Ufo ufo) { 
	   //Generar personajes
	   if(TimeUtils.nanoTime() - lastDropTime > 100000000) crearPersonajes();
	 
	   Personajes personajesAux;
	   for (int i=0; i < personajesPos.size; i++ ) {
		   personajesAux = personajesPos.get(i);
           personajesAux.getArea().y += 200 * Gdx.graphics.getDeltaTime();
           if (personajesAux.touch(ufo)) {
        	   if (personajesAux instanceof Interactuable) {
        		   Interactuable interactuableP = (Interactuable) personajesAux;
        		   interactuableP.interactuar(ufo);
        	   }
        	   if (ufo.getVidas() <= 0) {
                   return false;
               }
               personajesPos.removeIndex(i);
               break; // Salir del bucle tras la interacción
           }
	   }
	  return true; 
   }
   //--------------------------------------------------------------
   public void actualizarDibujo(SpriteBatch batch) { 
	   
	  for (int i=0; i < personajesPos.size; i++ ) {
		  Personajes dibujoPersonaje = personajesPos.get(i);
		  dibujoPersonaje.dibujar(batch);
	  }
   }
   //----------------------------------------------------------------
   public void destruir() {
      dropSound.dispose();
      rainMusic.dispose();
   }
   //--------------------------------------------------------------
   public void pausar() {
	  rainMusic.stop();
   }
   //--------------------------------------------------------------
   public void continuar() {
	  rainMusic.play();
   }
   //--------------------------------------------------------------
}
