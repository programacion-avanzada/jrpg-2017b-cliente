package comandos;

import estados.Estado;
import estados.EstadoBatalla;
import mensajeria.Comando;
import mensajeria.PaqueteBatalla;

public class Batalla extends Comando {

	@Override
	public void ejecutar() {
		
		PaqueteBatalla paqueteBatalla = (PaqueteBatalla) gson.fromJson(cadenaLeida, PaqueteBatalla.class);
		juego.getPersonaje().setEstado(Estado.estadoBatalla);
		Estado.setEstado(null);
		juego.setEstadoBatalla(new EstadoBatalla(juego, paqueteBatalla));
		Estado.setEstado(juego.getEstadoBatalla());

	}

}
