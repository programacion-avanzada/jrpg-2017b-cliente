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
	 * @param ubicacioNpcs ubicaciónNpcs
	 */
	public PaqueteDeNpcs(Map<Integer, PaqueteNpc> paquetesNpcs, Map<Integer, PaqueteMovimiento> ubicacionNpcs) {
		this.ubicacionNpcs = ubicacionNpcs;
		this.paquetesNpcs = paquetesNpcs;
	}

	/**
	 * Devuenve ubicación del Npcs
	 * 
	 * @return ubicacionNpcs
	 */
	public Map<Integer, PaqueteMovimiento> getUbicacionNpcs() {
		return ubicacionNpcs;
	}

	/**
	 * Setea ubicación del Npcs
	 * 
	 * @param ubicacionNpcs ubicación del Npcs
	 */
	public void setUbicacionNpcs(Map<Integer, PaqueteMovimiento> ubicacionNpcs) {
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
	public void setPaquetesNpcs(Map<Integer, PaqueteNpc> paquetesNpcs) {
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
