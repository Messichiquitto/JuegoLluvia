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

import abstractFactory.PersonajeFactory;
import personajes.AlienZipZip;
import personajes.Asteroide;
import personajes.Doctor;
import personajes.GatoMistico;
import personajes.PersonajeZorro;
import personajes.Personajes;
import personajes.Vaca;
import personajes.VacaDorada;
import personajes.Yunque;
import strategy.Interactuable;

public class Oleada {
    private Array<Personajes> personajesPos;
    private long lastDropTime;
    private Sound dropSound;
    private Music rainMusic;
    private PersonajeFactory personajeFactory;

    //--------------------------------------------------------------   
    public Oleada(Sound ss, Music mm, PersonajeFactory factory) {
        this.rainMusic = mm;
        this.dropSound = ss;
        this.personajeFactory = factory;
    }
    //--------------------------------------------------------------   
    public void crear() {
        personajesPos = new Array<Personajes>();
        rainMusic.setLooping(true);
        rainMusic.play();
    }
    //--------------------------------------------------------------   
    private void crearPersonajes() {
        double probabilidad = MathUtils.random(0f, 100f);
        
        if (probabilidad >= 0 && probabilidad < 5) {
            personajesPos.add(personajeFactory.crearZorro());
        } else if (probabilidad >= 5 && probabilidad < 7) {
            personajesPos.add(personajeFactory.crearVacaDorada());
        } else if (probabilidad >= 15 && probabilidad < 19) {
            personajesPos.add(personajeFactory.crearYunque());
        } else if (probabilidad >= 8 && probabilidad < 10) {
            personajesPos.add(personajeFactory.crearAlien());
        } else if (probabilidad >= 10 && probabilidad < 13) {
            personajesPos.add(personajeFactory.crearDoctor());
        } else if (probabilidad >= 13 && probabilidad < 15) {
            personajesPos.add(personajeFactory.crearGato());
        } else {
            crearPrincipales();
        }
    }
    //--------------------------------------------------------------   
    private void crearPrincipales() {
        if (MathUtils.random(1, 10) < 4) {
            personajesPos.add(personajeFactory.crearAsteroide());
        } else {
            personajesPos.add(personajeFactory.crearVaca());
        }
        lastDropTime = TimeUtils.nanoTime();
    }
    //--------------------------------------------------------------
    public boolean actualizarMovimiento(Ufo ufo) { 
        if (TimeUtils.nanoTime() - lastDropTime > 100000000) crearPersonajes();
     
        Personajes personajesAux;
        for (int i = 0; i < personajesPos.size; i++) {
            personajesAux = personajesPos.get(i);
            personajesAux.getArea().y += 200 * Gdx.graphics.getDeltaTime();
            if (personajesAux.touch(ufo)) {
                aplicarInteraccion(personajesAux, ufo);
                if (ufo.getVidas() <= 0) {
                    return false;
                }
                personajesPos.removeIndex(i);
                break;
            }
            
        }
        return true; 
    }
    //--------------------------------------------------------------   
    public void actualizarDibujo(SpriteBatch batch) { 
        for (int i = 0; i < personajesPos.size; i++) {
            Personajes dibujoPersonaje = personajesPos.get(i);
            dibujoPersonaje.dibujar(batch);
        }
    }
    //--------------------------------------------------------------   
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
    public void aplicarInteraccion(Personajes personaje, Ufo ufo) {
        if(personaje instanceof Interactuable) {
            Interactuable interactuableP = (Interactuable) personaje;
            ufo.setEstrategia(interactuableP);
            ufo.ejecutarInteraccion();
        }
        if (personaje instanceof Personajes) {
        	personaje.realizarAccionEspecifica(ufo);
        	personaje.interaccionSonido();
        }
    }
    //--------------------------------------------------------------
}

