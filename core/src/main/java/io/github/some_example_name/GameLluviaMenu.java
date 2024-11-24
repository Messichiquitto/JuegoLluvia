package io.github.some_example_name;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class GameLluviaMenu extends Game {
	private static GameLluviaMenu instance; //instancia estatica de la clase
	
    private SpriteBatch batch;
    private BitmapFont font;
    private int higherScore;
    private boolean asteroidesActivos;

    //private EnemigoCriatura enemigo;
    private Ufo ufo; // Instancia del Ufo
    private boolean juegoEnCurso;
    
    private GameLluviaMenu() {}
    
    public static GameLluviaMenu getInstance() {
    	if (instance == null) {
    		instance = new GameLluviaMenu();
    	}
    	return instance;
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();

        // Inicializar el Ufo
        Texture texturaUfo = new Texture(Gdx.files.internal("ufo.png"));
        Sound sonidoUfo = Gdx.audio.newSound(Gdx.files.internal("sonidoAbduction.mp3"));
        ufo = new Ufo(texturaUfo, sonidoUfo);

        this.setScreen(new MainMenuScreen(this));
        juegoEnCurso = false;
    }

    @Override
    public void render() {
        super.render();

        batch.begin();
        if (juegoEnCurso) {
            ufo.dibujar(batch); // Dibuja el Ufo 
        }
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
        if (ufo != null) {
        	ufo = null;
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
    }
}

