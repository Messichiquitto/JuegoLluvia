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
import java.util.ArrayList;
import java.util.List;

public class GameScreen implements Screen {
	final GameLluviaMenu game;
    private OrthographicCamera camera;
	private SpriteBatch batch;	   
	private BitmapFont font;
	private Ufo ufo;
	private Vaca lluvia;	
	private List<Interactuable> enemigos;

	public GameScreen(final GameLluviaMenu game) {
		this.game = game;
        this.batch = game.getBatch();
        this.font = game.getFont();
        
        // load the images for the droplet and the bucket, 64x64 pixels each
        Sound hurtSound = Gdx.audio.newSound(Gdx.files.internal("hurt.mp3"));		  
		ufo = new Ufo(new Texture(Gdx.files.internal("ufo.png")),hurtSound);
         
	    // load the drop sound effect and the rain background "music" 
        Texture vaca = new Texture(Gdx.files.internal("vaca.png"));
        Texture asteroide = new Texture(Gdx.files.internal("asteroide.png"));
        Sound dropSound = Gdx.audio.newSound(Gdx.files.internal("sonidoAbduction.mp3")); //Está listo
        Music rainMusic = Gdx.audio.newMusic(Gdx.files.internal("theme.mp3"));
	    rainMusic.setVolume(0.075f); //Para ajustar el volumen de la música de fondo
        lluvia = new Vaca(vaca, asteroide, dropSound, rainMusic);
	      
        //Acá se inicializan los enemigos
        enemigos = new ArrayList<>();
        enemigos.add(new EnemigoCriatura(100));
        //enemigos.add(new EnemigoAsteroide(100));
        
        
	    // camera
	    camera = new OrthographicCamera();
	    camera.setToOrtho(false, 800, 480);
	    batch = new SpriteBatch();
	    // creacion del tarro
	    ufo.crear();
	      
	    // creacion de la lluvia
	    lluvia.crear();
	}

	@Override
	public void render(float delta) {
		//limpia la pantalla con color azul obscuro.
		ScreenUtils.clear(0, 0, 0.2f, 1);
		
		//actualizar matrices de la cámara
		camera.update();
		
		//actualizar 
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		
		//dibujar textos
		font.draw(batch, "Gotas totales: " + ufo.getPuntos(), 5, 20);
		font.draw(batch, "Vidas : " + ufo.getVidas(), 670, 20);
		font.draw(batch, "HighScore : " + game.getHigherScore(), camera.viewportWidth/2-50, 20);
		
		if (!ufo.estaHerido()) {
			// movimiento del ufo desde teclado
	        ufo.actualizarMovimiento();     
			// caida de la lluvia 
	       if (!lluvia.actualizarMovimiento(ufo)) {
	    	  //actualizar HigherScore
	    	  if (game.getHigherScore()<ufo.getPuntos())
	    		  game.setHigherScore(ufo.getPuntos());  
	    	  //ir a la ventana de finde juego y destruir la actual
	    	  game.setScreen(new GameOverScreen(game));
	    	  dispose();
	       }
		}
		
		/*
		for (Interactuable enemigo : enemigos) {
			enemigo.interactuar();
		}
		*/
		
		ufo.dibujar(batch);
		lluvia.actualizarDibujoLluvia(batch);
		
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
	  // continuar con sonido de lluvia
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
      ufo.destruir();
      lluvia.destruir();

	}

}
