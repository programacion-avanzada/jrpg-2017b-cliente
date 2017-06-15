package comandos;

import mensajeria.*;

public class Conexion extends Comando {

	
	@Override
	public void ejecutar() {
		PaqueteDePersonajes pdp = (PaqueteDePersonajes) gson.fromJson(cadenaLeida, PaqueteDePersonajes.class);
		juego.setPersonajesConectados(pdp.getPersonajes());
	}

}
