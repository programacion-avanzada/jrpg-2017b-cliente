package comandos;

import estados.Estado;
import estados.EstadoBatalla;
import mensajeria.PaqueteBatalla;

public class Batalla extends ComandosEscucha {

	@Override
	public void ejecutar() {
		
		PaqueteBatalla paqueteBatalla = (PaqueteBatalla) gson.fromJson(cadenaLeida, PaqueteBatalla.class);
		
		System.out.println("Llegó al cliente: " + paqueteBatalla.getIdEnemigo());
		if (paqueteBatalla.getIdEnemigo() > 0) // Batalló contra otro personaje
		{
			juego.getPersonaje().setEstado(Estado.estadoBatalla);
			Estado.setEstado(null);
			juego.setEstadoBatalla(new EstadoBatalla(juego, paqueteBatalla));
			Estado.setEstado(juego.getEstadoBatalla());
		}
		else // Batalló contra otro NPC
		{
			juego.getPaquetesNpcs().get(paqueteBatalla.getIdEnemigo() * -1).setEstado(Estado.estadoBatallaNpc);
		}

	}

}
