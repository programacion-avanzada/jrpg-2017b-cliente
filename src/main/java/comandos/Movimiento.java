package comandos;

import mensajeria.Comando;
import mensajeria.PaqueteDeMovimientos;

public class Movimiento extends Comando{

	@Override
	public void ejecutar() {
		PaqueteDeMovimientos pdm = (PaqueteDeMovimientos) gson.fromJson(cadenaLeida,PaqueteDeMovimientos.class);
		juego.setUbicacionPersonajes(pdm.getPersonajes());
		
	}

}
