package io.github.some_example_name;
/*
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
*/
public class EnemigoCriatura extends Enemigo implements Interactuable {
	private int contadorToques;
	private boolean destruida;
	
	public EnemigoCriatura(int vida) {
		super(vida, "Criatura");
		this.contadorToques = 0;
		this.destruida = false;
	}
	
	@Override
	public void atacar(Ufo ufo) {
		System.out.println("La criatura está atacando!");
		contadorToques++;
		if (contadorToques == 3) {
			destruir();
		}
	}
	
	@Override
	public void destruir() {
		System.out.println("La criatura ha sido destruida!");
		super.destruir();
		destruida = true;
	}
	/*
	@Override
	public void interactuar() {
		
	}
	*/
	/*
	@Override
    public void recoger() {
        throw new UnsupportedOperationException("No se puede recoger un enemigo.");
    }
    */
	
	@Override
	public boolean estaDestruida() {
		return destruida;
	}
	/*
	public void dibujar(SpriteBatch batch) {
		if (!destruida) {
			//Hacer el png de la criatura
			batch.draw(texturaCriatura, x, y);
		}
	}
	*/

}
