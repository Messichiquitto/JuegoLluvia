package io.github.some_example_name;
/*Esta clase son las cosas que se ven en pantalla. Acá habría que modificar las cosas del enemigo, porque ahí
 está la caga. El resto no se debería tocar porque corría bien (cambiar los sonidos, el png, etc.) */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import java.util.ArrayList;
import java.util.List;

public class GameScreen implements Screen {
    final GameLluviaMenu game;
    private OrthographicCamera camera;
    private SpriteBatch batch;	   
    private BitmapFont font;
    private Ufo ufo;
    private Vaca lluvia;	
    private float tiempoParaAparicion;
    private float tiempoEntreApariciones = 5f; //

    public GameScreen(final GameLluviaMenu game) {
        this.game = game;
        this.batch = game.getBatch();
        this.font = game.getFont();
        
        Sound hurtSound = Gdx.audio.newSound(Gdx.files.internal("hurt.mp3"));		  
        ufo = new Ufo(new Texture(Gdx.files.internal("ufo.png")), hurtSound);
        
        Texture vaca = new Texture(Gdx.files.internal("vaca.png"));
        Texture asteroide = new Texture(Gdx.files.internal("asteroide.png"));
        Sound dropSound = Gdx.audio.newSound(Gdx.files.internal("sonidoAbduction.mp3")); 
        Music rainMusic = Gdx.audio.newMusic(Gdx.files.internal("theme.mp3"));
        rainMusic.setVolume(0.075f);
        lluvia = new Vaca(vaca, asteroide, dropSound, rainMusic);
        
        // Cámara
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        batch = new SpriteBatch();
        
        // Creación del UFO y la lluvia
        ufo.crear();
        lluvia.crear();

        //Aparición del zorro
        tiempoParaAparicion = 0;
    }

    @Override
    public void render(float delta) {
        // Limpiar la pantalla con color azul oscuro.
        ScreenUtils.clear(0, 0, 0.2f, 1);
        
        // Actualizar matrices de la cámara
        camera.update();
        
        // Configurar la proyección de la cámara
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        
        // Dibujar textos
        font.draw(batch, "Gotas totales: " + ufo.getPuntos(), 5, 30);
        font.draw(batch, "Vidas : " + ufo.getVidas(), 670, 30);
        font.draw(batch, "HighScore : " + game.getHigherScore(), camera.viewportWidth / 2 - 50, 30);
        
        if (!ufo.estaHerido()) {
            // Movimiento del UFO desde teclado
            ufo.actualizarMovimiento();     
            // Caída de la lluvia 
            if (!lluvia.actualizarMovimiento(ufo)) {
                // Actualizar HigherScore
                if (game.getHigherScore() < ufo.getPuntos())
                    game.setHigherScore(ufo.getPuntos());  
                // Ir a la ventana de fin de juego y destruir la actual
                game.setScreen(new GameOverScreen(game));
                dispose();
            }
        }

        tiempoParaAparicion += delta; 

        /*
        if (tiempoParaAparicion >= tiempoEntreApariciones) {
            EnemigoCriatura enemigo = new EnemigoCriatura(3, 3);
            enemigo.setPosicion(Gdx.graphics.getWidth() / 2 - enemigo.getWidth() / 2, -enemigo.getHeight());;
            tiempoParaAparicion = 0; 
        }

/*Acá está la caga, donde dice EnemigoCriatura y todo eso.
        for (Interactuable enemigo : enemigos) {
            if (enemigo instanceof EnemigoCriatura) {
                EnemigoCriatura enemigoCriatura = (EnemigoCriatura) enemigo;
                enemigoCriatura.actualizar(delta); 
                enemigoCriatura.dibujar(batch); 

                if (enemigoCriatura.getHitbox().overlaps(ufo.getRectangulo())) {
                    enemigoCriatura.interactuar(ufo);
                }
            }
        }

        */
        // Dibujar al UFO y la lluvia
        ufo.dibujar(batch);
        lluvia.actualizarDibujoLluvia(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
        // Continuar con sonido de lluvia
        lluvia.continuar();
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
        lluvia.pausar();
        game.setScreen(new PausaScreen(game, this)); 
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        lluvia.destruir();
        /*
        for (Interactuable enemigo : enemigos) {
            if (enemigo instanceof EnemigoCriatura) {
                ((EnemigoCriatura) enemigo).dispose();
            }
        }
        */
    }
}
