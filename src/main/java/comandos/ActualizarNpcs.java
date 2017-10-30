package comandos;

import mensajeria.PaqueteDeNpcs;

public class ActualizarNpcs extends ComandosEscucha {
    @Override
    public void ejecutar() {
	PaqueteDeNpcs paqueteActualizarNpcs = (PaqueteDeNpcs) gson.fromJson(cadenaLeida, PaqueteDeNpcs.class);
	juego.setPaquetesNpcs(paqueteActualizarNpcs.getPaquetesNpcs());
	juego.setUbicacionNpcs(paqueteActualizarNpcs.getUbicacionNpcs());

	if (juego.getNpcManager() != null)
	    juego.getNpcManager().actualizar();
    }
}