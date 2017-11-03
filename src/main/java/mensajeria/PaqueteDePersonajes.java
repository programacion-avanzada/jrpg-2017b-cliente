package mensajeria;

import java.io.Serializable;
import java.util.Map;

/**
 * Clase PaqueteDePersonajes
 */
public class PaqueteDePersonajes extends Paquete implements Serializable, Cloneable {

	private Map<Integer, PaquetePersonaje> personajes;

	/**
	 * Constructor
	 */
	public PaqueteDePersonajes() {

	}

	/**
	 * Constructor parametrizado de Paquete de personajes
	 *
	 * @param personajes parametro personajess
	 */
	public PaqueteDePersonajes(final Map<Integer, PaquetePersonaje> personajes) {
		this.personajes = personajes;
	}

	/**
	 * Devuelve el objeto personajes
	 *
	 * @return personajes
	 */
	public Map<Integer, PaquetePersonaje> getPersonajes() {
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
