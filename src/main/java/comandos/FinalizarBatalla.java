package comandos;

import estados.Estado;
import mensajeria.Comando;
import mensajeria.PaqueteFinalizarBatalla;

public class FinalizarBatalla extends Comando{

	@Override
	public void ejecutar() {
		PaqueteFinalizarBatalla paqueteFinalizarBatalla = (PaqueteFinalizarBatalla) gson.fromJson(cadenaLeida, PaqueteFinalizarBatalla.class);
		juego.getPersonaje().setEstado(Estado.estadoJuego);
		Estado.setEstado(juego.getEstadoJuego());
		
	}
	
}
