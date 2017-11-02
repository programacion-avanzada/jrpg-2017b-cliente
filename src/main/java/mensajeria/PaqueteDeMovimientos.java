package mensajeria;

import java.io.Serializable;
import java.util.Map;

/**
 * Clase PaqueteDeMovimientos
 */
public class PaqueteDeMovimientos extends Paquete implements Serializable, Cloneable {

	private Map<Integer, PaqueteMovimiento> personajes;

	/**
	 * Constructor
	 */
	public PaqueteDeMovimientos() {

	}

	/**
	 * Asigna el objeto personajes
	 * 
	 * @param personajes mapa de personaje
	 */
	public PaqueteDeMovimientos(Map<Integer, PaqueteMovimiento> personajes) {
		this.personajes = personajes;
	}

	/**
	 * Devuelve el objeto personajes
	 *
	 * @return personajes
	 */
	public Map<Integer, PaqueteMovimiento> getPersonajes() {
		return personajes;
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