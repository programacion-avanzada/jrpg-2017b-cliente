package juego;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import chat.MiChat;
import cliente.Cliente;
import cliente.EscuchaMensajes;
import dominio.Personaje;
import estados.Estado;
import estados.EstadoBatalla;
import estados.EstadoJuego;
import mensajeria.PaqueteMovimiento;
import mensajeria.PaqueteNpc;
import mensajeria.PaquetePersonaje;

/**
 * Clase Juego, contiene los estados de batalla, los paquetes del jugador,
 * cliente, personajes conectados
 */
public class Juego implements Runnable {

    /** The pantalla. */
    private Pantalla pantalla;

    /** The n OMBRE. */
    private final String nOMBRE;

    /** The a NCHO. */
    private final int aNCHO;

    /** The a LTO. */
    private final int aLTO;

    /** The Constant REFRESCO. */
    private static final int REFRESCO = 1000000000;

    /** The Constant FPS. */
    private static final int FPS = 60;

    /** The Constant TAMFUENTE. */
    private static final int TAMFUENTE = 15;

    /** The hilo. */
    private Thread hilo;

    /** The corriendo. */
    private boolean corriendo;

    // Estrategia para graficar mediante buffers (Primero se "grafica" en el/los
    /** The bs. */
    // buffer/s y finalmente en el canvas)
    private BufferStrategy bs;

    /** The g. */
    private Graphics g;

    /** The estado juego. */
    // Estados
    private Estado estadoJuego;

    /** The estado batalla. */
    private Estado estadoBatalla;

    /** The estado batalla npc. */
    private Estado estadoBatallaNpc;

    /** The handler mouse. */
    // HandlerMouse
    private HandlerMouse handlerMouse;

    /** The camara. */
    // Camara
    private Camara camara;

    /** The cliente. */
    // Conexion
    private Cliente cliente;

    /** The escucha mensajes. */
    private EscuchaMensajes escuchaMensajes;

    /** The paquete personaje. */
    // El player
    private PaquetePersonaje paquetePersonaje;

    /** The ubicacion personaje. */
    private PaqueteMovimiento ubicacionPersonaje;

    /** The personajes conectados. */
    // Personajes conectados
    private Map<Integer, PaquetePersonaje> personajesConectados;

    /** The ubicacion personajes. */
    private Map<Integer, PaqueteMovimiento> ubicacionPersonajes;

    /** The paquetes npcs. */
    // NPCs
    private Map<Integer, PaqueteNpc> paquetesNpcs;

    /** The ubicacion npcs. */
    private Map<Integer, PaqueteMovimiento> ubicacionNpcs;

    /** The npc manager. */
    private NpcManager npcManager;

    /** The chats activos. */
    private Map<String, MiChat> chatsActivos = new HashMap<>();

    /** The cargar recursos. */
    private CargarRecursos cargarRecursos;

    /**
     * Constructor parametrizado del juego
     * @param nombre nombre de la instancia del juego
     * @param ancho ancho del Frame del juego
     * @param alto alto del Frame del juego
     * @param cliente cliente que se conecta al juego
     * @param pp paquete del personaje
     */
    public Juego(final String nombre, final int ancho, final int alto, final Cliente cliente,
	    final PaquetePersonaje pp) {
	this.nOMBRE = nombre;
	this.aLTO = alto;
	this.aNCHO = ancho;
	this.cliente = cliente;
	this.paquetePersonaje = pp;

	// Inicializo la ubicacion del personaje
	ubicacionPersonaje = new PaqueteMovimiento();
	ubicacionPersonaje.setIdPersonaje(paquetePersonaje.getId());
	ubicacionPersonaje.setFrame(0);
	ubicacionPersonaje.setDireccion((1 + 1 + 1 + 1 + 1 + 1));

	// Creo el escucha de mensajes
	escuchaMensajes = new EscuchaMensajes(this);
	escuchaMensajes.start();

	handlerMouse = new HandlerMouse();

	iniciar();

	cargarRecursos = new CargarRecursos(cliente);
	cargarRecursos.start();
    }

    /**
     * Carga lo necesario para iniciar el juego
     */
    public void iniciar() {

	pantalla = new Pantalla(nOMBRE, aNCHO, aLTO, cliente);

	pantalla.getCanvas().addMouseListener(handlerMouse);

	camara = new Camara(this, 0, 0);

	Personaje.cargarTablaNivel();
    }

    /**
     * Actualiza los objetos y sus posiciones
     */
    private void actualizar() {

	if (Estado.getEstado() != null) {
	    Estado.getEstado().actualizar();
	}
    }

    /**
     * Metodo para graficar los objetos
     */
    private void graficar() {
	// Grafica los objetos y sus posiciones
	bs = pantalla.getCanvas().getBufferStrategy();
	if (bs == null) {
	    // Seteo una estrategia para el canvas en caso de que no tenga una
	    pantalla.getCanvas().createBufferStrategy((1 + 1 + 1));
	    return;
	}

	g = bs.getDrawGraphics(); // Permite graficar el buffer mediante g

	g.clearRect(0, 0, aNCHO, aLTO); // Limpiamos la pantalla

	// Graficado de imagenes
	g.setFont(new Font("Book Antiqua", 1, TAMFUENTE));

	if (Estado.getEstado() != null) {
	    Estado.getEstado().graficar(g);
	}

	// Fin de graficado de imagenes

	bs.show(); // Hace visible el próximo buffer disponible
	g.dispose();
    }

    @Override
    public void run() {
	// Hilo principal del juego

	// Cantidad de actualizaciones por segundo que se desean
	int fps = FPS;
	// Cantidad de nanosegundos en FPS deseados
	double tiempoPorActualizacion = REFRESCO / fps;
	double delta = 0;
	long ahora;
	long ultimoTiempo = System.nanoTime();
	long timer = 0;
	// Timer para mostrar fps cada un segundo
	int actualizaciones = 0;
	// Cantidad de actualizaciones que se realizan realmente

	while (corriendo) {
	    ahora = System.nanoTime();
	    // Calculo para determinar cuando realizar la actualizacion y el
	    // graficado
	    delta += (ahora - ultimoTiempo) / tiempoPorActualizacion;
	    // Sumo el tiempo transcurrido hasta que se acumule 1 segundo y
	    // mostrar los FPS
	    timer += ahora - ultimoTiempo;
	    // Para las proximas corridas del bucle
	    ultimoTiempo = ahora;

	    if (delta >= 1) {
		actualizar();
		graficar();
		actualizaciones++;
		delta--;
	    }

	    if (timer >= REFRESCO) {
		// Si paso 1 segundo muestro los FPS
		pantalla.getFrame().setTitle(nOMBRE + " | " + "FPS: " + actualizaciones);
		actualizaciones = 0;
		timer = 0;
	    }
	}

	stop();
    }

    /**
     * Metodo para iniciar el hilo del juego
     * @throws IOException exception lanzada por el hilo
     */
    public synchronized void start() throws IOException {
	// Inicia el juego
	if (corriendo) {
	    return;
	}

	estadoJuego = new EstadoJuego(this);
	Estado.setEstado(estadoJuego);
	pantalla.mostrar();
	corriendo = true;
	hilo = new Thread(this);
	hilo.start();
    }

    /**
     * Metodo para cerrar el hilo corriendo del juego
     */
    public synchronized void stop() {
	// Detiene el juego
	if (!corriendo) {
	    return;
	}
	try {
	    corriendo = false;
	    hilo.join();
	} catch (InterruptedException e) {
	    JOptionPane.showMessageDialog(null, "Fallo al intentar detener el juego.");
	}
    }

    /**
     * @return ancho del juego
     */
    public int getAncho() {
	return aNCHO;
    }

    /**
     * @return alto del juego
     */
    public int getAlto() {
	return aLTO;
    }

    /**
     * @return handlerMouse
     */
    public HandlerMouse getHandlerMouse() {
	return handlerMouse;
    }

    /**
     * @return camara
     */
    public Camara getCamara() {
	return camara;
    }

    /**
     * @return estadoJuego
     */
    public EstadoJuego getEstadoJuego() {
	return (EstadoJuego) estadoJuego;
    }

    /**
     * @return estadoBatalla
     */
    public EstadoBatalla getEstadoBatalla() {
	return (EstadoBatalla) estadoBatalla;
    }

    /**
     * @param estadoBatalla estado de la batalla del Juego
     */
    public void setEstadoBatalla(final EstadoBatalla estadoBatalla) {
	this.estadoBatalla = estadoBatalla;
    }

    /**
     * @return estadoBatallaNpc estado de la batalla del NPC
     */
    public Estado getEstadoBatallaNpc() {
	return estadoBatallaNpc;
    }

    /**
     * @param estadoBatallaNpc estado de la batalla del NPC
     */
    public void setEstadoBatallaNpc(final Estado estadoBatallaNpc) {
	this.estadoBatallaNpc = estadoBatallaNpc;
    }

    /**
     * @return cliente
     */
    public Cliente getCliente() {
	return cliente;
    }

    /**
     * @return escuchaMensajes
     */
    public EscuchaMensajes getEscuchaMensajes() {
	return escuchaMensajes;
    }

    /**
     * @return paquetePersonaje
     */
    public PaquetePersonaje getPersonaje() {
	return paquetePersonaje;
    }

    /**
     * @return ubicacionPersonaje
     */
    public PaqueteMovimiento getUbicacionPersonaje() {
	return ubicacionPersonaje;
    }

    /**
     * @param paquetePersonajeParam paquete del Personaje
     */
    public void setPersonaje(final PaquetePersonaje paquetePersonajeParam) {
	this.paquetePersonaje = paquetePersonajeParam;
    }

    /**
     * Metodo para actualizar el paquete Personaje
     */
    public void actualizarPersonaje() {
	paquetePersonaje = (PaquetePersonaje) (personajesConectados.get(paquetePersonaje.getId()).clone());
    }

    /**
     * @return personajesConectados mapa con los personajes Conectados
     */
    public Map<Integer, PaquetePersonaje> getPersonajesConectados() {
	return personajesConectados;
    }

    /**
     * Metodo para setear los personajes conectados
     * @param map mapa que contiene los personajes
     */
    public void setPersonajesConectados(final Map<Integer, PaquetePersonaje> map) {
	this.personajesConectados = map;
    }

    /**
     * @return ubicacionPersonaje mapa de las ubicaciones
     */
    public Map<Integer, PaqueteMovimiento> getUbicacionPersonajes() {
	return ubicacionPersonajes;
    }

    /**
     * Metodo para setear las posiciones de los NPC
     * @param ubicacionPersonajes mapa que contiene los paqueteMovimiento de los
     *            Personajes
     */
    public void setUbicacionPersonajes(final Map<Integer, PaqueteMovimiento> ubicacionPersonajes) {
	this.ubicacionPersonajes = ubicacionPersonajes;
    }

    /**
     * Metodo para obtener el paquete de los NPC
     * @return el mapa de los NPC
     */
    public Map<Integer, PaqueteNpc> getPaquetesNpcs() {
	return paquetesNpcs;
    }

    /**
     * Metodo para setear el paquete de los NPC
     * @param paquetesNpcs mapa que contiene los datos de los NPC
     */
    public void setPaquetesNpcs(final Map<Integer, PaqueteNpc> paquetesNpcs) {
	this.paquetesNpcs = paquetesNpcs;
    }

    /**
     * Metodo para obtener la ubicación de los NPC
     * @return mapa de las ubicaciones de los NPC
     */
    public Map<Integer, PaqueteMovimiento> getUbicacionNpcs() {
	return ubicacionNpcs;
    }

    /**
     * Metodo para setear la ubicación de los NPC
     * @param ubicacionNpcs mapa de la ubicacion de los NPC
     */
    public void setUbicacionNpcs(final Map<Integer, PaqueteMovimiento> ubicacionNpcs) {
	this.ubicacionNpcs = ubicacionNpcs;
    }

    /**
     * Metodo para obtener el manager de los NPC
     * @return npcManager
     */
    public NpcManager getNpcManager() {
	return npcManager;
    }

    /**
     * Metodo para setear el manager de los NPC
     * @param npcManager setea el manager de los npc
     */
    public void setNpcManager(final NpcManager npcManager) {
	this.npcManager = npcManager;
    }

    /**
     * Metodo que retorna los chats activo
     * @return chatActivos
     */
    public Map<String, MiChat> getChatsActivos() {
	return chatsActivos;
    }

}
