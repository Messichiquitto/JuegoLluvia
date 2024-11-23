package abstractFactory;

import personajes.Personajes;

public interface PersonajeFactory {
	Personajes crearAlien();
	
	Personajes crearAsteroide();
	
	Personajes crearDoctor();
	
	Personajes crearGato();
	
	Personajes crearZorro();
	
	Personajes crearVaca();
	
	Personajes crearVacaDorada();
	
	Personajes crearYunque();
}
