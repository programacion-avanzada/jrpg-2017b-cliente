package mensajeria;

import java.io.Serializable;

import javax.swing.JOptionPane;

/**
 * Clase Paquete
 */
public class Paquete implements Serializable, Cloneable {

	public static String msjExito = "1";
	public static String msjFracaso = "0";

	private String mensaje;
	private String ip;
	private int comando;

	/**
	 * Constructor Paquete
	 */
	public Paquete() {

	}

	/**
	 * 
	 * Constructor parametrizado
	 * 
	 * @param mensaje parametros mensaje
	 * @param nick parametros nick
	 * @param ip parametros ip
	 * @param comando parametro comando
	 */
	public Paquete(String mensaje, String nick, String ip, int comando) {
		this.mensaje = mensaje;
		this.ip = ip;
		this.comando = comando;
	}

	/**
	 * Constructor parametrizado
	 * 
	 * @param mensajeparametro mensaje
	 * @param comando parametro comando
	 */
	public Paquete(String mensaje, int comando) {
		this.mensaje = mensaje;
		this.comando = comando;
	}

	/**
	 * Constructor parametrizado
	 * 
	 * @param comando comando
	 */
	public Paquete(int comando) {
		this.comando = comando;
	}

	/**
	 * Setea el mensaje
	 * 
	 * @param mensaje mensaje
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * Setea el ip
	 * 
	 * @param ip parametro ip
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * Setea el comando
	 * 
	 * @param comando comando
	 */
	public void setComando(int comando) {
		this.comando = comando;
	}

	/**
	 * Retorna el mensaje
	 * 
	 * @return mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * Retorna el ip
	 * 
	 * @return ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * Retorna el comando
	 * 
	 * @return comando
	 */
	public int getComando() {
		return comando;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Object clone() {
		Object obj = null;
		try {
			obj = super.clone();
		} catch (CloneNotSupportedException ex) {
			JOptionPane.showMessageDialog(null, "Error al clonar");

		}
		return obj;
	}

	/**
	 * Retorna el objeto
	 *
	 * @param nombrePaquete parametro nombrePaquete
	 * @returnc
	 */
	public Comando getObjeto(String nombrePaquete) {
		try {
			Comando c;
			c = (Comando) Class.forName(nombrePaquete + "." + Comando.CLASSNAMES[comando]).newInstance();
			return c;
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			return null;
		}

	}

	/**
	 * Retorna el objeto seteado
	 *
	 * @param nombrePaquete parametro nombrePaquete
	 * @param accion parametro accion
	 * @return c
	 */
	public static Comando getObjetoSet(String nombrePaquete, int accion) {
		try {
			Comando c;
			c = (Comando) Class.forName(nombrePaquete + "." + Comando.CLASSNAMESBIS[accion]).newInstance();
			return c;
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			return null;
		}

	}

}
