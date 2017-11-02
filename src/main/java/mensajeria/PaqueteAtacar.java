package mensajeria;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Clase PaqueteAtacar
 */
public class PaqueteAtacar extends Paquete implements Serializable, Cloneable {

	private int id;
	private int idEnemigo;
	private int nuevaSaludPersonaje;
	private int nuevaEnergiaPersonaje;
	private int nuevaSaludEnemigo;
	private int nuevaEnergiaEnemigo;
	private HashMap<String, Number> mapPersonaje = new HashMap<String, Number>();
	private HashMap<String, Number> mapEnemigo = new HashMap<String, Number>();

	/**
	 * Constructor parametrizado
	 * 
	 * @param id id personaje personaje
	 * @param idEnemigo id del enemigo
	 * @param nuevaSalud nueva salud personaje
	 * @param nuevaEnergia nueva energia personaje
	 * @param nuevaSaludEnemigo nueva salud del enemigo
	 * @param nuevaEnergiaEnemigo nueva energia del enemigo
	 * @param nuevaDefensa nueva defensa personaje
	 * @param nuevaDefensaEnemigo nueva defensa enemigo
	 * @param probEvitarDano probabilidad de evitar danio
	 * @param probEvitarDanoEnemgio probabilidad de evitar daño del enemigo
	 */
	public PaqueteAtacar(int id, int idEnemigo, int nuevaSalud, int nuevaEnergia, int nuevaSaludEnemigo,
			int nuevaEnergiaEnemigo, int nuevaDefensa, int nuevaDefensaEnemigo, double probEvitarDano,
			double probEvitarDanoEnemgio) {
		setComando(Comando.ATACAR);
		this.id = id;
		this.idEnemigo = idEnemigo;
		this.nuevaSaludPersonaje = nuevaSalud;
		this.nuevaEnergiaPersonaje = nuevaEnergia;
		this.nuevaSaludEnemigo = nuevaSaludEnemigo;
		this.nuevaEnergiaEnemigo = nuevaEnergiaEnemigo;
		mapPersonaje.put("salud", nuevaSalud);
		mapPersonaje.put("energia", nuevaEnergia);
		mapPersonaje.put("defensa", nuevaDefensa);
		mapPersonaje.put("probEvitarDanio", probEvitarDano);
		mapEnemigo.put("salud", nuevaSaludEnemigo);
		mapEnemigo.put("energia", nuevaEnergiaEnemigo);
		mapEnemigo.put("defensa", nuevaDefensaEnemigo);
		mapEnemigo.put("probEvitarDanio", probEvitarDanoEnemgio);
	}

	/**
	 * Retorna el id del personaje
	 * 
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setea el id
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Retorna el id del enemigo
	 * 
	 * @return id del enemigo
	 */
	public int getIdEnemigo() {
		return idEnemigo;
	}

	/**
	 * Setea el id del enemigo
	 * 
	 * @param idEnemigo id del enemigo
	 */
	public void setIdEnemigo(int idEnemigo) {
		this.idEnemigo = idEnemigo;
	}

	/**
	 * Obtiene nueva salud personaje.
	 *
	 * @return nueva salud personaje
	 */
	public int getNuevaSaludPersonaje() {
		return nuevaSaludPersonaje;
	}

	/**
	 * Asigna nueva salud personaje. <br>
	 *
	 * @param newSaludPersonaje Valor con nueva salud del personaje. <br>
	 */
	public void setNuevaSaludPersonaje(int nuevaSaludPersonaje) {
		this.nuevaSaludPersonaje = nuevaSaludPersonaje;
	}

	/**
	 * Obtiene nueva energia personaje.
	 *
	 * @return nueva energia personaje
	 */
	public int getNuevaEnergiaPersonaje() {
		return nuevaEnergiaPersonaje;
	}

	/**
	 * Asigna nueva energia personaje. <br>
	 *
	 * @param newEnergiaPersonaje Valor con nueva energia del personaje. <br>
	 */
	public void setNuevaEnergiaPersonaje(int nuevaEnergiaPersonaje) {
		this.nuevaEnergiaPersonaje = nuevaEnergiaPersonaje;
	}

	/**
	 * Obtiene nueva salud enemigo.
	 *
	 * @return nueva salud enemigo
	 */
	public int getNuevaSaludEnemigo() {
		return nuevaSaludEnemigo;
	}

	/**
	 * Asigna nueva salud enemigo. <br>
	 *
	 * @param newSaludEnemigo Valor con nueva salud del enemigo. <br>
	 */
	public void setNuevaSaludEnemigo(int nuevaSaludEnemigo) {
		this.nuevaSaludEnemigo = nuevaSaludEnemigo;
	}

	/**
	 * Obtiene nueva energia enemigo.
	 *
	 * @return nueva energia enemigo
	 */
	public int getNuevaEnergiaEnemigo() {
		return nuevaEnergiaEnemigo;
	}

	/**
	 * Asigna nueva energia enemigo. <br>
	 *
	 * @param newEnergiaEnemigo Valor con nueva energia del enemigo. <br>
	 */
	public void setNuevaEnergiaEnemigo(int nuevaEnergiaEnemigo) {
		this.nuevaEnergiaEnemigo = nuevaEnergiaEnemigo;
	}

	/**
	 * Obtiene map personaje.
	 * 
	 * @return map personaje
	 */
	public HashMap<String, Number> getMapPersonaje() {
		return mapPersonaje;
	}

	/**
	 * Obtiene map enemigo.
	 *
	 * @return map enemigo
	 */
	public HashMap<String, Number> getMapEnemigo() {
		return mapEnemigo;
	}

}
