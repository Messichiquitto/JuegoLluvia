package io.github.some_example_name;

/* Acá hay varios errores, pero de sintaxis, porque borre algunas cosas de esta clase pero no en otras.
En si esto es el menú donde se crean y cargan los png, los sonidos, etc.
 */
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class GameLluviaMenu extends Game {
    private SpriteBatch batch;
    private BitmapFont font;
    private int higherScore;
    private boolean asteroidesActivos;

    private EnemigoCriatura enemigo;
    private Ufo ufo; // Instancia del Ufo
    private boolean juegoEnCurso;

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();

        // Inicializar el Ufo
        Texture texturaUfo = new Texture(Gdx.files.internal("ufo.png"));
        Sound sonidoUfo = Gdx.audio.newSound(Gdx.files.internal("sonidoAbduction.mp3"));
        ufo = new Ufo(texturaUfo, sonidoUfo);

        // Inicializar el enemigo
        enemigo = new EnemigoCriatura(3, 3);
        enemigo.setPosicion(200, Gdx.graphics.getHeight());

        this.setScreen(new MainMenuScreen(this));
        juegoEnCurso = false;
    }

    @Override
    public void render() {
        super.render();

        batch.begin();
        if (juegoEnCurso) {
            ufo.dibujar(batch); // Dibuja el Ufo
            enemigo.dibujar(batch); // Dibuja el enemigo
            enemigo.actualizar(Gdx.graphics.getDeltaTime()); 
        }
        batch.end();
    }



    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
        if (enemigo != null) {
            enemigo.dispose(); 
        }
        if (ufo != null) {
            ufo.dispose(); 
        }
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public BitmapFont getFont() {
        return font;
    }

    public int getHigherScore() {
        return higherScore;
    }

    public void setHigherScore(int higherScore) {
        this.higherScore = higherScore;
    }

    public void setAsteroidesActivos(boolean activos) {
        this.asteroidesActivos = activos;
    }

    public void iniciarJuego() {
        juegoEnCurso = true;
        enemigo.setPosicion(200, Gdx.graphics.getHeight());
    }
}

