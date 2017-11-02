package mensajeria;

import java.io.Serializable;

/**
 * Clase PaqueteMovimiento
 */
public class PaqueteMovimiento extends Paquete implements Serializable, Cloneable {

	private int id;
	private float posX;
	private float posY;
	private int direccion;
	private int frame;

	/**
	 * Constructor
	 */
	public PaqueteMovimiento() {
		setComando(Comando.MOVIMIENTO);
	}

	/**
	 * Constructor parametrizado
	 *
	 * @param idPersonaje parametro idPersonaje
	 */
	public PaqueteMovimiento(int idPersonaje) {
		id = idPersonaje;
		setComando(Comando.MOVIMIENTO);
	}

	/**
	 * Constructor parametrizado
	 *
	 * @param idPersonaje parametro idPersonaje
	 * @param posX parametro posX
	 * @param posY parametro posY
	 */
	public PaqueteMovimiento(int idPersonaje, float posX, float posY) {
		this.id = idPersonaje;
		this.posX = posX;
		this.posY = posY;
		setComando(Comando.MOVIMIENTO);
	}

	/**
	 * * Constructor parametrizado
	 *
	 * @param idPersonaje parametro idPersonaje
	 * @param posX parametro posX
	 * @param posY parametro posY
	 * @param direccion parametro dirección
	 */
	public PaqueteMovimiento(int idPersonaje, float posX, float posY, int direccion) {
		this.id = idPersonaje;
		this.posX = posX;
		this.posY = posY;
		this.direccion = direccion;
		setComando(Comando.MOVIMIENTO);
	}

	/**
	 * Retorna el id del personaje
	 *
	 * @return id
	 */
	public int getIdPersonaje() {
		return id;
	}

	/**
	 * Setea el id del personaje
	 *
	 * @param idPersonaje parametro idPersonaje
	 */
	public void setIdPersonaje(int idPersonaje) {
		this.id = idPersonaje;
	}

	/**
	 * Retorna la posicion x
	 *
	 * @return posx
	 */
	public float getPosX() {
		return posX;
	}

	/**
	 * Setea la posicion x
	 *
	 * @param posX parametro posX
	 */
	public void setPosX(float posX) {
		this.posX = posX;
	}

	/**
	 * Retorna la posicion de Y
	 *
	 * @return posY
	 */
	public float getPosY() {
		return posY;
	}

	/**
	 * Setea la posicion Y
	 *
	 * @param posY parametro posY
	 */
	public void setPosY(float posY) {
		this.posY = posY;
	}

	/**
	 * Retorna la direccion
	 *
	 * @return direccion
	 */
	public int getDireccion() {
		return direccion;
	}

	/**
	 * Setea la direccion
	 *
	 * @param direccion parametro direccion
	 */
	public void setDireccion(int direccion) {
		this.direccion = direccion;
	}

	/**
	 * Retorna el Frame
	 *
	 * @return frame
	 */
	public int getFrame() {
		return frame;
	}

	/**
	 * Setea el Frame
	 *
	 * @param frame parametro frame
	 */
	public void setFrame(int frame) {
		this.frame = frame;
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
