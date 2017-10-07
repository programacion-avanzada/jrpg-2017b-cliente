package comandos;

import estados.Estado;
import mensajeria.PaqueteFinalizarBatalla;

public class FinalizarBatalla extends ComandosEscucha{

	@Override
	public void ejecutar() {
		PaqueteFinalizarBatalla paqueteFinalizarBatalla = (PaqueteFinalizarBatalla) gson.fromJson(cadenaLeida, PaqueteFinalizarBatalla.class);
		
		// Batalló contra un npc
		System.out.println("Recibio finalizar: " + paqueteFinalizarBatalla.getIdEnemigo());
		if (paqueteFinalizarBatalla.getIdEnemigo() < 0)
		{
			// si ganó el humano
			if (paqueteFinalizarBatalla.getGanadorBatalla() != paqueteFinalizarBatalla.getIdEnemigo() * -1)
				juego.getNpcManager().despawnNpc(paqueteFinalizarBatalla.getIdEnemigo() * -1);
			else
			{
				System.out.println("entre a setear estado");
				juego.getPaquetesNpcs().get(paqueteFinalizarBatalla.getIdEnemigo() * -1).setEstado(Estado.estadoJuego);
			}
			
		}
		else // Batalló contra un humano
		{
			juego.getPersonaje().setEstado(Estado.estadoJuego);
			Estado.setEstado(juego.getEstadoJuego());
		}
			
			
	}
	
}
