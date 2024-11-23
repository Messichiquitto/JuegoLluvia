package abstractFactory;

import com.badlogic.gdx.math.MathUtils;
import personajes.AlienZipZip;
import personajes.Asteroide;
import personajes.Doctor;
import personajes.GatoMistico;
import personajes.PersonajeZorro;
import personajes.Personajes;
import personajes.Vaca;
import personajes.VacaDorada;
import personajes.Yunque;

public class FabricaLvl1 implements PersonajeFactory{
	@Override
	public Personajes crearAlien() {
		return new AlienZipZip(MathUtils.random(0, 800 - 64), 0, 64, 64);
	}
	
	@Override
	public Personajes crearAsteroide(){
		return new Asteroide(MathUtils.random(0, 800 - 64), 0, 64, 64);
	}
	
	@Override
	public Personajes crearDoctor(){
		return new Doctor(MathUtils.random(0, 800 - 64), 0, 64, 64);
	}
	
	@Override
	public Personajes crearGato(){
		return new GatoMistico(MathUtils.random(0, 800 - 64), 0, 64, 64);
	}
	
	@Override
	public Personajes crearZorro(){
		return new PersonajeZorro(MathUtils.random(0, 800 - 64), 0, 64, 64);
	}
	
	@Override
	public Personajes crearVaca(){
		return new Vaca(MathUtils.random(0, 800 - 64), 0, 64, 64);
	}
	
	@Override
	public Personajes crearVacaDorada(){
		return new VacaDorada(MathUtils.random(0, 800 - 64), 0, 64, 64);
	}
	
	@Override
	public Personajes crearYunque(){
		return new Yunque(MathUtils.random(0, 800 - 64), 0, 64, 64);
	}

}
