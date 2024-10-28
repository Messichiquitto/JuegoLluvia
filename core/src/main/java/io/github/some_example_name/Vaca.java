package io.github.some_example_name;
/* Esta es la clase lluvia original. Se cambió a vaca para el tema texturas y todo eso. No se toca tampoco.*/
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class Vaca {
	private Array<Rectangle> rainDropsPos;
	private Array<Integer> rainDropsType;
    private long lastDropTime;
    private Texture vacaBuena;
    private Texture asteroideMalo;
    private Sound dropSound;
    private Music rainMusic;
	   
	public Vaca(Texture vacaBuena, Texture asteroideMalo, Sound ss, Music mm) {
		rainMusic = mm;
		dropSound = ss;
		this.vacaBuena = vacaBuena;
		this.asteroideMalo = asteroideMalo;
	}
	
	public void crear() {
		rainDropsPos = new Array<Rectangle>();
		rainDropsType = new Array<Integer>();
		crearVaca();
	      // start the playback of the background music immediately
	      rainMusic.setLooping(true);
	      rainMusic.play();
	}
	
	private void crearVaca() { //ESTO SE DEBE CAMBIAR
	      Rectangle vaca = new Rectangle();
	      vaca.x = MathUtils.random(0, 800-64);
	      vaca.y = 0;
	      vaca.width = 64;
	      vaca.height = 64;
	      rainDropsPos.add(vaca);
	      // ver el tipo de vaca
	      if (MathUtils.random(1,10)<5)	    	  
	         rainDropsType.add(1);
	      else 
	    	 rainDropsType.add(2);
	      lastDropTime = TimeUtils.nanoTime();
	   }
	
   public boolean actualizarMovimiento(Ufo ufo) { 
	   // generar vaca
	   if(TimeUtils.nanoTime() - lastDropTime > 100000000) crearVaca();
	  
	   // revisar si las gotas cayeron al suelo o chocaron con el tarro
	   for (int i=0; i < rainDropsPos.size; i++ ) {
		  Rectangle vaca = rainDropsPos.get(i);
		  vaca.y += 300 * Gdx.graphics.getDeltaTime();
	      
	      //cae al suelo y se elimina
	      if(vaca.y > 480) {
	    	  rainDropsPos.removeIndex(i); 
	    	  rainDropsType.removeIndex(i);
	      }
	      if(vaca.overlaps(ufo.getArea())) { //la gota choca con el tarro
	    	if(rainDropsType.get(i)==1) { // gota dañina
	    		ufo.damage();
	    	  if (ufo.getVidas()<=0)
	    		 return false; // si se queda sin vidas retorna falso /game over
	    	  rainDropsPos.removeIndex(i);
	          rainDropsType.removeIndex(i);
	      	}else { // gota a recolectar
	      		ufo.sumarPuntos(10);
	          dropSound.play();
	          rainDropsPos.removeIndex(i);
	          rainDropsType.removeIndex(i);
	      	}
	      }
	   } 
	  return true; 
   }
   
   public void actualizarDibujoLluvia(SpriteBatch batch) { 
	   
	  for (int i=0; i < rainDropsPos.size; i++ ) {
		  Rectangle vaca = rainDropsPos.get(i);
		  if(rainDropsType.get(i)==1) // gota dañina
	         batch.draw(asteroideMalo, vaca.x, vaca.y); 
		  else
			 batch.draw(vacaBuena, vaca.x, vaca.y); 
	   }
   }
   
   public void destruir() {
      dropSound.dispose();
      rainMusic.dispose();
   }
   public void pausar() {
	  rainMusic.stop();
   }
   public void continuar() {
	  rainMusic.play();
   }
   
}
