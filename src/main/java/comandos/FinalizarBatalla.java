package comandos;

import estados.Estado;
import interfaz.MenuInfoPersonaje;
import mensajeria.PaqueteFinalizarBatalla;

public class FinalizarBatalla extends ComandosEscucha {

	@Override
	public void ejecutar() {
		PaqueteFinalizarBatalla paqueteFinalizarBatalla = (PaqueteFinalizarBatalla) gson.fromJson(cadenaLeida, PaqueteFinalizarBatalla.class);
		juego.getPersonaje().setEstado(Estado.estadoJuego);
		juego.getEstadoJuego().setHaySolicitud(true, juego.getPersonaje(), MenuInfoPersonaje.menuPerderBatalla); // Informar que se perdió la batalla
		Estado.setEstado(juego.getEstadoJuego());
	}

}
