package io.github.some_example_name;
/*
Misma idea que la clase Enemigo. Está basada en la idea original, pero cambien nomas.
Lo bacán sería mantener la idea de que al tocar la tecla espacio, recién se active el poweUp, que no sea
automatico.
Ese método está abajo.
Cambien lo que necesiten nomas.

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class EnemigoCriatura extends Enemigo {
    private Texture texturaZorro;
    private Sound sonidoMuerte;
    private boolean powerUpActivo;
    private AlienZipZip alienZipZip;
    private Rectangle area;

    public EnemigoCriatura(int vida, int maxToques) {
        super(vida, maxToques);
        this.texturaZorro = new Texture(Gdx.files.internal("zorro.png"));
        this.sonidoMuerte = Gdx.audio.newSound(Gdx.files.internal("enemigoDeath.mp3"));
        this.powerUpActivo = false;

        this.area = new Rectangle();
        this.area.width = texturaZorro.getWidth();
        this.area.height = texturaZorro.getHeight();
    }

    @Override
    public void interactuar(Ufo ufo) {
        recibirToque();
        System.out.println("El jugador ha tocado la criatura");
    }

    @Override
    protected void soltarPowerUp() {
        alienZipZip = new AlienZipZip(7);
    }

    public void recogerPowerUp(Ufo ufo, GameLluviaMenu game) {
        if (alienZipZip != null) {
            alienZipZip.activar(ufo, game);
            powerUpActivo = true;
            alienZipZip = null;
        }
    }

    @Override
    public void usarPoder(Ufo ufo, GameLluviaMenu game) {
        if (powerUpActivo && alienZipZip != null) {
            alienZipZip.actualizar(Gdx.graphics.getDeltaTime());

            if (!alienZipZip.isActivo()) {
                alienZipZip.desactivar(ufo, game);
                powerUpActivo = false;
                alienZipZip = null;
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && alienZipZip != null) { //ESTO ES LO DE TOCAR EL ESPACIO Y SE ACTIVA EL POWERUP
            recogerPowerUp(ufo, game);
        }
    }

    @Override
    protected void morir() {
        sonidoMuerte.play();
        soltarPowerUp();
    }

    public void actualizar(float delta) {
        if (powerUpActivo && alienZipZip != null) {
            alienZipZip.actualizar(delta);
            if (!alienZipZip.isActivo()) { 
                powerUpActivo = false;
                alienZipZip.desactivar(ufo, game);
            }
        }
    }



    public void dibujar(SpriteBatch batch) {
        batch.draw(texturaZorro, area.x, area.y);
    }

    public Rectangle getArea() {
        return area;
    }

    public void setPosicion(float x, float y) {
        area.x = x;
        area.y = y;
    }

    @Override
    public void recibirToque() {
        toques++;
        if (toques >= maxToques) {
            morir();
        }
    }

    public float getWidth() {
        return texturaZorro.getWidth();
    }

    public float getHeight() {
        return texturaZorro.getHeight();
    }

    public Rectangle getHitbox() {
        return area;
    }

    public void dispose() {
        texturaZorro.dispose();
        sonidoMuerte.dispose();
    }
}
*/
