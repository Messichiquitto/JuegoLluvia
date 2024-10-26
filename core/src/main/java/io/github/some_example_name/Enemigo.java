package io.github.some_example_name;

public abstract class Enemigo {
	protected int vida;
	protected String tipoEnemigo;
	protected boolean destruido;
	
	public Enemigo(int vida, String tipoEnemigo) {
		this.vida = vida;
		this.tipoEnemigo = tipoEnemigo;
		this.destruido = false;
	}
	
	public abstract void atacar(Ufo ufo);
	
	public int getVida() {
		return vida;
	}
	
	public String getTipoEnemigo() {
		return tipoEnemigo;
	}
	
	public void destruir() {
		destruido = true;
	}
	
	public boolean estaDestruido() {
		return destruido;
	}
	
	public void recibirDamage(int damage) {
		vida -= damage;
		if (vida <= 0) {
			destruir();
		}
	}

	public boolean estaDestruida() {
		return destruido;
	}

}
