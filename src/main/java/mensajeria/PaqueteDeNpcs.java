package mensajeria;

import java.io.Serializable;
import java.util.Map;

/**
 * Clase PaqueteDeNpcs
 */
public class PaqueteDeNpcs extends Paquete implements Serializable, Cloneable {
	private Map<Integer, PaqueteMovimiento> ubicacionNpcs;
	private Map<Integer, PaqueteNpc> paquetesNpcs;

	/**
	 * Inicializador PaqueteDeNpcs
	 */
	public PaqueteDeNpcs() {
		this.ubicacionNpcs = null;
		this.paquetesNpcs = null;
	}

	/**
	 * Constructor parametrizado
	 *
	 * @param paquetesNpcs paquete Npcs
	 * @param ubicacioNpcs ubicacion Npcs
	 */
	public PaqueteDeNpcs(final Map<Integer, PaqueteNpc> paquetesNpcs,
			final Map<Integer, PaqueteMovimiento> ubicacionNpcs) {
		this.ubicacionNpcs = ubicacionNpcs;
		this.paquetesNpcs = paquetesNpcs;
	}

	/**
	 * Devuenve ubicaci�n del Npcs
	 *
	 * @return ubicacionNpcs
	 */
	public Map<Integer, PaqueteMovimiento> getUbicacionNpcs() {
		return ubicacionNpcs;
	}

	/**
	 * Setea ubicaci�n del Npcs
	 *
	 * @param ubicacionNpcs ubicacion del Npcs
	 */
	public void setUbicacionNpcs(final Map<Integer, PaqueteMovimiento> ubicacionNpcs) {
		this.ubicacionNpcs = ubicacionNpcs;
	}

	/**Devuelve paqueteNpcs
	 * @return paquetesNpcs
	 */
	public Map<Integer, PaqueteNpc> getPaquetesNpcs() {
		return paquetesNpcs;
	}

	/**
	 * Setea paquete de Npcs
	 *
	 * @param paquetesNpcs paquete Npcs
	 */
	public void setPaquetesNpcs(final Map<Integer, PaqueteNpc> paquetesNpcs) {
		this.paquetesNpcs = paquetesNpcs;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see mensajeria.Paquete#clone()
	 */
	@Override
	public Object clone() {
		Object obj = null;
		obj = super.clone();
		return obj;
	}
}
