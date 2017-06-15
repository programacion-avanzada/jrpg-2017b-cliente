package comandos;

import mensajeria.PaqueteDePersonajes;

public class Conexion extends ComandosEscucha {

	
	@Override
	public void ejecutar() {
		PaqueteDePersonajes pdp = (PaqueteDePersonajes) gson.fromJson(cadenaLeida, PaqueteDePersonajes.class);
		juego.setPersonajesConectados(pdp.getPersonajes());
	}

}
