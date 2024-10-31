package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class AlienZipZip extends Personajes implements Interactuable {
    private Sound pickUpSound;
    private Texture texturaAlienZipZip2;
    private float stateTime;
    private Texture texturaActual;
    private Texture texturaAlienZipZip;

    public AlienZipZip(float x, float y, float ancho, float alto) {
        super(x, y, ancho, alto, new Texture(Gdx.files.internal("alienZipZip.png")));
        texturaActual = new Texture(Gdx.files.internal("alienZipZip.png"));
        texturaAlienZipZip = new Texture(Gdx.files.internal("alienZipZip.png"));
        texturaAlienZipZip2 = new Texture(Gdx.files.internal("alienZipZip2.png"));
        pickUpSound = Gdx.audio.newSound(Gdx.files.internal("pickUpSound.mp3"));

    }

    @Override
    public void dibujar(SpriteBatch batch) {
        // Actualizar el tiempo de estado
        stateTime += Gdx.graphics.getDeltaTime();

        // Alternar entre texturas cada 0.3 segundos
        if (stateTime >= 0.3f) {
            if (texturaActual == texturaAlienZipZip) {
                texturaActual = texturaAlienZipZip2;
            } else {
                texturaActual = texturaAlienZipZip;
            }
            stateTime = 0; // Reiniciar el tiempo de estado para el próximo cambio
        }

        // Dibujar la textura actual
        batch.draw(texturaActual, getArea().x, getArea().y);
    }


    @Override
    public boolean touch(Ufo ufo) {
        if (super.touch(ufo)) {
            pickUpSound.play(0.5f);
            return true; // Retorna true si ha tocado al UFO
        }
        return false; // Retorna false si no ha tocado al UFO
    }

    public void interactuar(Ufo ufo) {
        ufo.interaccionAlien();
    }
}
