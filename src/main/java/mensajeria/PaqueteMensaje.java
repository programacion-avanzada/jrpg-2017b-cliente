package mensajeria;

import java.io.Serializable;

/**
 * Clase PaqueteMensaje
 */
public class PaqueteMensaje extends Paquete implements Serializable, Cloneable {

	private String userEmisor;
	private String userReceptor;
	private String msj;

	/**
	 * Constructor de la clase
	 */
	public PaqueteMensaje() {
	}

	/**
	 * Retorna el mensaje
	 *
	 * @return msj
	 */
	@Override
	public String getMensaje() {
		return msj;
	}

	/**
	 * Asigna mensaje
	 *
	 * @param mensaje a asignar
	 */
	@Override
	public void setMensaje(final String mensaje) {
		this.msj = mensaje;
	}

	/**
	 * Retorna el usuario emisor
	 *
	 * @return userEmisor
	 */
	public String getUserEmisor() {
		return userEmisor;
	}

	/**
	 * Setea el usuario emisor con el id
	 * @param idEmisor id del emisor del mensaje
	 */
	public void setUserEmisor(final String idEmisor) {
		this.userEmisor = idEmisor;
	}

	/**
	 * Retorna el usuario receptor
	 *
	 * @return userreceptor
	 */
	public String getUserReceptor() {
		return userReceptor;
	}

	/**
	 * Setea el usuario receptor
	 *
	 * @param idReceptor id del usuario receptor
	 */
	public void setUserReceptor(final String idReceptor) {
		this.userReceptor = idReceptor;
	}

	@Override
	public Object clone() {
		Object obj = null;
		obj = super.clone();
		return obj;
	}
}
