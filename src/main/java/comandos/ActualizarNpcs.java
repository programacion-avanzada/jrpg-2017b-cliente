package comandos;

import mensajeria.PaqueteDeNpcs;

/**
 * Comando para actualizar las posiciones y los paquetes
 * de los NPCs
 */
public class ActualizarNpcs extends ComandosEscucha {
    @Override
    public void ejecutar() {
	PaqueteDeNpcs paqueteActualizarNpcs = gson.fromJson(cadenaLeida, PaqueteDeNpcs.class);
	juego.setPaquetesNpcs(paqueteActualizarNpcs.getPaquetesNpcs());
	juego.setUbicacionNpcs(paqueteActualizarNpcs.getUbicacionNpcs());

	if (juego.getNpcManager() != null) {
	    juego.getNpcManager().actualizar();
	}
    }
}
