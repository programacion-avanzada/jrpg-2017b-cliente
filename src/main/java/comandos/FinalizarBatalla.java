package comandos;

import estados.Estado;
import mensajeria.PaqueteFinalizarBatalla;

public class FinalizarBatalla extends ComandosEscucha{

	@Override
	public void ejecutar() {
		PaqueteFinalizarBatalla paqueteFinalizarBatalla = (PaqueteFinalizarBatalla) gson.fromJson(cadenaLeida, PaqueteFinalizarBatalla.class);
		
		juego.getPersonaje().setEstado(Estado.estadoJuego);
		Estado.setEstado(juego.getEstadoJuego());
		
		if (paqueteFinalizarBatalla.getIdEnemigo() < 0) // Batalló contra un npc
			juego.getNpcManager().despawnNpc(paqueteFinalizarBatalla.getIdEnemigo() * -1);
			
	}
	
}
