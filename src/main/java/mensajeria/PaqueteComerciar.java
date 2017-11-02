package mensajeria;

import java.io.Serializable;
import java.util.ArrayList;

import dominio.Item;

/**
 * Clase PaqueteComerciar
 */
public class PaqueteComerciar extends Paquete implements Serializable, Cloneable {

	private int id;
	private int idEnemigo;
	private int listo = 0;
	private ArrayList<Item> itemsADar = new ArrayList<Item>();
	private ArrayList<Item> itemsAObtener = new ArrayList<Item>();
	private boolean solicitudDeComercio;

	/**
	 * Constructor
	 */
	public PaqueteComerciar() {
		setComando(Comando.COMERCIO);
		solicitudDeComercio = true;
	}

	/**
	 * Retorna si es solicitud de comercio
	 *
	 * @return solicitudDeComercio
	 */
	public boolean isSolicitudDeComercio() {
		return solicitudDeComercio;
	}

	/**
	 * Setea la solicitud de comercio
	 *
	 * @param solicitudDeComercio parametro solicitudDeComercio
	 */
	public void setSolicitudDeComercio(boolean solicitudDeComercio) {
		this.solicitudDeComercio = solicitudDeComercio;
	}

	/**
	 * Retorna los items a dar
	 *
	 * @return itemsADar
	 */
	public ArrayList<Item> getItemsADar() {
		return itemsADar;
	}

	/**
	 * Setea los items a dar
	 *
	 * @param itemsADar parametros itemsADar
	 */
	public void setItemsADar(ArrayList<Item> itemsADar) {
		this.itemsADar = itemsADar;
	}

	/**
	 * Retorna el id
	 *
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setea el id
	 *
	 * @param id parametros id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Retorna el id enemigo
	 *
	 * @return idEnemigo
	 */
	public int getIdEnemigo() {
		return idEnemigo;
	}

	/**
	 * Setea el id enemigo
	 *
	 * @param idEnemigo parametros idEnemigo
	 */
	public void setIdEnemigo(int idEnemigo) {
		this.idEnemigo = idEnemigo;
	}

	/**
	 * Retorna si esta listo
	 * 
	 * @return listo
	 */
	public int getListo() {
		return listo;
	}

	/**
	 * Aumenta la variable listo
	 */
	public void aumentarListo() {
		this.listo++;
	}

	/**
	 * Disminuye la variable listo
	 */
	public void disminuirListo() {
		this.listo--;
	}

	/**
	 * Retorna los items a obtener
	 *
	 * @return itemsAObtener
	 */
	public ArrayList<Item> getItemsAObtener() {
		return itemsAObtener;
	}

	/**
	 * Setea los items a obtener
	 *
	 * @param itemsAObtener parametros ItemsAObtener
	 */
	public void setItemsAObtener(ArrayList<Item> itemsAObtener) {
		this.itemsAObtener = itemsAObtener;
	}
}
