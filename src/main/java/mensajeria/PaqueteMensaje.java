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
	public String getMensaje() {
		return msj;
	}

	/**
	 * Asigna mensaje
	 * 
	 * @param mensaje a asignar
	 */
	public void setMensaje(String mensaje) {
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

	public void setUserEmisor(String idEmisor) {
		this.userEmisor = idEmisor;
	}

	public String getUserReceptor() {
		return userReceptor;
	}

	public void setUserReceptor(String idReceptor) {
		this.userReceptor = idReceptor;
	}

	public Object clone() {
		Object obj = null;
		obj = super.clone();
		return obj;
	}
}
