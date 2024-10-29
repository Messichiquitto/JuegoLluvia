package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen {
    final GameLluviaMenu game;
    private OrthographicCamera camera;
    private SpriteBatch batch;	   
    private BitmapFont font;
    private Ufo ufo;
    private Oleada oleada;

    public GameScreen(final GameLluviaMenu game) {
        this.game = game;
        this.batch = game.getBatch();
        this.font = game.getFont();
        
        Sound hurtSound = Gdx.audio.newSound(Gdx.files.internal("hurt.mp3"));
        hurtSound.play(0.5f);
        ufo = new Ufo(new Texture(Gdx.files.internal("ufo.png")), hurtSound);
        
        //Texture vaca = new Texture(Gdx.files.internal("vaca.png"));
        //Texture asteroide = new Texture(Gdx.files.internal("asteroide.png"));
        Sound dropSound = Gdx.audio.newSound(Gdx.files.internal("sonidoAbduction.mp3")); 
        Music rainMusic = Gdx.audio.newMusic(Gdx.files.internal("theme.mp3"));
        rainMusic.setVolume(0.075f);
        oleada = new Oleada(dropSound, rainMusic);
        
        // Cámara
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        batch = new SpriteBatch();
        
        // Creación del UFO y la lluvia
        ufo.crear();
        oleada.crear();

    }

    @Override
    public void render(float delta) {
        // Limpiar la pantalla con color azul oscuro.
        ScreenUtils.clear(0, 0, 0.1f, 0);
        
        // Actualizar matrices de la cámara
        camera.update();
        
        // Configurar la proyección de la cámara
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        
        // Dibujar textos
        font.draw(batch, "Vacas abducidas: " + ufo.getPuntos(), 5, 30);
        font.draw(batch, "Vidas : " + ufo.getVidas(), 670, 30);
        font.draw(batch, "HighScore : " + game.getHigherScore(), camera.viewportWidth / 2 - 50, 30);
        
        if (!ufo.estaHerido()) {
            // Movimiento del UFO desde teclado
            ufo.actualizarMovimiento();     
            // Caída de la lluvia 
            if (!oleada.actualizarMovimiento(ufo)) {
                // Actualizar HigherScore
                if (game.getHigherScore() < ufo.getPuntos())
                    game.setHigherScore(ufo.getPuntos());  
                // Ir a la ventana de fin de juego y destruir la actual
                game.setScreen(new GameOverScreen(game));
                dispose();
            }
        }

        // Dibujar al UFO y la oleada
        ufo.dibujar(batch);
        oleada.actualizarDibujo(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
        oleada.continuar();
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
        oleada.pausar();
        game.setScreen(new PausaScreen(game, this)); 
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        oleada.destruir();
    }
}
